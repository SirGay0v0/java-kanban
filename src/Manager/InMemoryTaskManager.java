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
    private List<HashMap> listOfTasks;
    private Map<Integer, Task> taskHashMap;
    private Map<Integer, Epic> epicHashMap;
    private Map<Integer, Subtask> subtaskHashMap;
    private final HistoryManager inMemoryHistoryManager;
    private TaskGetter taskGetter = new TaskGetter();
    private TaskCreator taskCreator = new TaskCreator();
    private TaskDeleter taskDeleter = new TaskDeleter();
    private TaskUpdater taskUpdater = new TaskUpdater();
    private int id = 0;


    public InMemoryTaskManager(HistoryManager inMemoryHistoryManager) {
        taskHashMap = new HashMap<>();
        epicHashMap = new HashMap<>();
        subtaskHashMap = new HashMap<>();
        listOfTasks = new ArrayList<>();
        listOfTasks.add(0, (HashMap) taskHashMap);
        listOfTasks.add(1, (HashMap) epicHashMap);
        listOfTasks.add(2, (HashMap) subtaskHashMap);
        this.inMemoryHistoryManager = inMemoryHistoryManager;
    }

    @Override
    public void createTask(Task task) {
        taskCreator.createTask(taskHashMap, task, id);
        id++;
    }

    @Override
    public void createEpic(Epic epic) {
        taskCreator.createTask(epicHashMap, epic, id);
        id++;
    }

    @Override
    public void createSubtask(Subtask subtask) {
        taskCreator.createSubtask((ArrayList<HashMap>) listOfTasks, subtask, id);
        id++;
    }

    @Override
    public Collection getTasks() {
        return taskGetter.getAllTasks((ArrayList) listOfTasks, 0);
    }

    @Override
    public Collection getEpics() {
        return taskGetter.getAllTasks((ArrayList) listOfTasks, 1);
    }

    @Override
    public Collection getSubtasks() {
        return taskGetter.getAllTasks((ArrayList) listOfTasks, 2);
    }


    @Override
    public void deleteTasks() {
        taskDeleter.deleteAll(taskHashMap);
    }

    @Override
    public void deleteEpics() {
        taskDeleter.deleteAll(epicHashMap);
    }

    @Override
    public void deleteSubtasks() {
        taskDeleter.deleteAll(subtaskHashMap);
    }

    @Override
    public Task getTaskById(int id) {
        Task task = (Task) taskGetter.getById((ArrayList<HashMap>) listOfTasks, id, 0);
        inMemoryHistoryManager.add(task);
        return task;
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = (Epic) taskGetter.getById((ArrayList<HashMap>) listOfTasks, id, 1);
        inMemoryHistoryManager.add(epic);
        return epic;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = (Subtask) taskGetter.getById((ArrayList<HashMap>) listOfTasks, id, 2);
        inMemoryHistoryManager.add(subtask);
        return subtask;
    }

    @Override
    public void updateTask(Task task, int id) {
        taskUpdater.updateById(task, id, (ArrayList<HashMap>) listOfTasks, 0);
    }

    @Override
    public void updateEpic(Epic epic, int id) {
        taskUpdater.updateById(epic, id, (ArrayList<HashMap>) listOfTasks, 1);
    }

    @Override
    public void updateSubtask(Subtask subtask, int id) {
        taskUpdater.updateById(subtask, id, (ArrayList<HashMap>) listOfTasks, 2);
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
        Collection listOfSubtasks = taskGetter.getListOfSubtasks(epicHashMap, id);
        return listOfSubtasks;
    }

    @Override
    public Collection<Task> getHistory() {
        return inMemoryHistoryManager.getHistory();
    }


}