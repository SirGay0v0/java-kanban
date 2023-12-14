package Manager;

import ManagerBusinessLogic.*;
import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

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
    private ArrayList<HashMap> listOfTasks;
    private HashMap<Integer, Task> taskHashMap;
    private HashMap<Integer, Epic> epicHashMap;
    private HashMap<Integer, Subtask> subtaskHashMap;
    private HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();
    private TaskGetter taskGetter = new TaskGetter();
    private TaskCreator taskCreator = new TaskCreator();
    private TaskDeleter taskDeleter = new TaskDeleter();
    private TaskUpdater taskUpdater = new TaskUpdater();
    private int id = 0;


    public InMemoryTaskManager() {
        taskHashMap = new HashMap<>();
        epicHashMap = new HashMap<>();
        subtaskHashMap = new HashMap<>();
        listOfTasks = new ArrayList<>();
        listOfTasks.add(0, taskHashMap);
        listOfTasks.add(1, epicHashMap);
        listOfTasks.add(2, subtaskHashMap);
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
        taskCreator.createSubtask(listOfTasks, subtask, id);
        id++;
    }

    @Override
    public ArrayList getTasks() {
        return taskGetter.getAllTasks(listOfTasks, 0);
    }

    @Override
    public ArrayList getEpics() {
        return taskGetter.getAllTasks(listOfTasks, 1);
    }

    @Override
    public ArrayList getSubtasks() {
        return taskGetter.getAllTasks(listOfTasks, 2);
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
        Task task = (Task) taskGetter.getById(listOfTasks, id, 0);
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
        taskUpdater.updateById(task, id, listOfTasks, 0);
    }

    @Override
    public void updateEpic(Epic epic, int id) {
        taskUpdater.updateById(epic, id, listOfTasks, 1);
    }

    @Override
    public void updateSubtask(Subtask subtask, int id) {
        taskUpdater.updateById(subtask, id, listOfTasks, 2);
    }

    @Override
    public void deleteTaskById(int id) {
        taskDeleter.deleteTaskById(taskHashMap, id);
    }

    @Override
    public void deleteEpicById(int id) {
        taskDeleter.deleteEpicById(epicHashMap, id, subtaskHashMap);
    }

    @Override
    public void deleteSubtaskById(int id) {
        taskDeleter.deleteSubtaskById(epicHashMap, id, subtaskHashMap);
    }

    @Override
    public ArrayList getAllSubtasksFromEpic(int id) {
        ArrayList listOfSubtasks = taskGetter.getListOfSubtasks(epicHashMap, id);
        return listOfSubtasks;
    }
}
