package Manager;

import ManagerBusinessLogic.*;
import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.*;

/**
 * Класс реализует интерфейс TaskManager.
 * Содержит основные команды для взаимодействия с программой.
 * <p>
 * Управляет всем взаимодействием с Task, Epic и Subtask.
 * При инициализации создает один Список и три HashMap.
 * Каждая HashMap хранит в себе объекты соответствующего типа.
 * Список содержит в себе все HashMap под определенными индексами.
 * Индексы используются в качестве параметров передаваемыми в методы по типу getTasks -
 * метод получает индекс нужной HashMap и достает объекты конкретно из нее, не затрагивая
 * остальные HashMap.
 */
public class InMemoryTaskManager implements TaskManager {
    protected List<HashMap<Integer, ? extends Task>> listOfTasks = new ArrayList<>();
    protected Map<Integer, Task> taskHashMap = new HashMap<>();
    protected Map<Integer, Epic> epicHashMap = new HashMap<>();
    protected Map<Integer, Subtask> subtaskHashMap = new HashMap<>();
    protected final HistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();
    private final TaskGetter taskGetter = new TaskGetter();
    private final TaskCreator taskCreator = new TaskCreator();
    private final TaskDeleter taskDeleter = new TaskDeleter();
    private final TaskUpdater taskUpdater = new TaskUpdater();
    private final TaskTimeValidation timeValidation = new TaskTimeValidation();
    protected int id = 0;
    protected TreeSet<Task> priorityTree;


    public InMemoryTaskManager() {
        listOfTasks.add(0, (HashMap<Integer, Task>) taskHashMap);
        listOfTasks.add(1, (HashMap<Integer, Epic>) epicHashMap);
        listOfTasks.add(2, (HashMap<Integer, Subtask>) subtaskHashMap);
        priorityTree = new TreeSet<>((Task o1, Task o2) -> {
            if (o1.getStartTime() != null && o2.getStartTime() != null) {
                if (o1.getStartTime().isAfter(o2.getStartTime())) {
                    return 1;
                } else if (o1.getStartTime() == (o2.getStartTime())) {
                    return -1;
                }
            } else if (o1.getStartTime() == null && o2.getStartTime() != null) {
                return 1;
            } else if (o1.getStartTime() != null && o2.getStartTime() == null) {
                return -1;
            }
            return -1;
        });
    }

    @Override
    public void createTask(Task task) {
        Collection<? extends Task> set = getPrioritizedTasks();
        if (timeValidation.validate(task, set) != null) {
            taskCreator.createTask(taskHashMap, task, id);
            id++;
            priorityTree.add(task);
        } else System.out.println("Задача не была создана, ввиду пересечения по времени" +
                " с уже заплпнированными задачами");
    }

    @Override
    public void createEpic(Epic epic) {
        taskCreator.createEpic(epicHashMap, epic, id);
        id++;
    }

    @Override
    public void createSubtask(Subtask subtask) {
        Collection<? extends Task> set = getPrioritizedTasks();
        if (timeValidation.validate(subtask, set) != null) {
            taskCreator.createSubtask1(epicHashMap, subtaskHashMap,subtask,id);
            id++;
            priorityTree.add(subtask);
        } else System.out.println("Задача не была создана, ввиду пересечения по времени" +
                " с уже заплпнированными задачами");
    }

    @Override
    public Collection<Task> getTasks() {
        return taskGetter.getAllTasks(taskHashMap);
    }

    @Override
    public Collection<Epic> getEpics() {
        return taskGetter.getAllEpics(epicHashMap);
    }

    @Override
    public Collection<Subtask> getSubtasks() {
        return taskGetter.getAllSubtasks(subtaskHashMap);
    }


    @Override
    public void deleteTasks() {
        taskDeleter.deleteAll(taskHashMap, inMemoryHistoryManager);
    }

    @Override
    public void deleteEpics() {
        taskDeleter.deleteAll(epicHashMap, inMemoryHistoryManager);
    }

    @Override
    public void deleteSubtasks() {
        taskDeleter.deleteAll(subtaskHashMap, inMemoryHistoryManager);
    }

    @Override
    public Task getTaskById(int id) {
        Task task = taskGetter.getById(listOfTasks, id, 0);
        inMemoryHistoryManager.add(task);
        return task;
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = (Epic) taskGetter.getById(listOfTasks, id, 1);
        inMemoryHistoryManager.add(epic);
        return epic;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = (Subtask) taskGetter.getById(listOfTasks, id, 2);
        inMemoryHistoryManager.add(subtask);
        return subtask;
    }

    @Override
    public void updateTask(Task task, int id) {
        taskUpdater.updateTaskById(task, id, taskHashMap);
    }

    @Override
    public void updateEpic(Epic epic, int id) {
        taskUpdater.updateEpicById(epic, id, epicHashMap);
    }

    @Override
    public void updateSubtask(Subtask subtask, int id) {
        taskUpdater.updateSubtaskById(subtask, id, subtaskHashMap, epicHashMap);
    }

    @Override
    public void deleteTaskById(int id) {
        taskDeleter.deleteTaskById(taskHashMap, id);
        inMemoryHistoryManager.remove(id);
    }

    /**
     * Пришлось добавить новый аргумент к методу deleteEpicById
     */
    @Override
    public void deleteEpicById(int id) {
        inMemoryHistoryManager.remove(id);
        taskDeleter.deleteEpicById(epicHashMap, id, subtaskHashMap, inMemoryHistoryManager);
    }

    @Override
    public void deleteSubtaskById(int id) {
        inMemoryHistoryManager.remove(id);
        taskDeleter.deleteSubtaskById(epicHashMap, id, subtaskHashMap);
    }


    @Override
    public Collection<Integer> getAllSubtasksFromEpic(int id) {
        return taskGetter.getListOfSubtasks(epicHashMap, id);
    }

    @Override
    public Collection<Task> getHistory() {
        return inMemoryHistoryManager.getHistory();
    }

    @Override
    public Collection<? extends Task> getPrioritizedTasks() {
        return priorityTree;
    }
}