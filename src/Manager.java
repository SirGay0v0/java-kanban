import ManagerUtilities.*;
import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс управляет всем взаимодействием с Task, Epic и Subtask.
 * При инициализации создает один Список и три HashMap.
 * Каждая HashMap хранит в себе объекты соответствующего типа.
 * Список содержит в себе все HashMap под определенными индексами.
 * Индексы используются в качестве параметров передаваемыми в методы по типу getTasks -
 * метод получает индекс нужной HashMap и достает объекты конкретно из нее, не затрагивая
 * остальные HashMap.
 */

public class Manager {
    ArrayList<HashMap> listOfTasks;
    HashMap<Integer, Task> taskHashMap;
    HashMap<Integer, Epic> epicHashMap;
    HashMap<Integer, Subtask> subtaskHashMap;
    TaskGetter taskGetter = new TaskGetter();
    TaskCreator taskCreator = new TaskCreator();
    TaskDeleter taskDeleter = new TaskDeleter();
    TaskUpdater taskUpdater = new TaskUpdater();
    int id = 0;


    public Manager() {
        taskHashMap = new HashMap<>();
        epicHashMap = new HashMap<>();
        subtaskHashMap = new HashMap<>();
        listOfTasks = new ArrayList<>();
        listOfTasks.add(0, taskHashMap);
        listOfTasks.add(1, epicHashMap);
        listOfTasks.add(2, subtaskHashMap);
    }

    public ArrayList getTasks() {
        return taskGetter.getAllTasks(listOfTasks, 0);
    }

    public ArrayList getEpics() {
        return taskGetter.getAllTasks(listOfTasks, 1);
    }

    public ArrayList getSubtasks() {
        return taskGetter.getAllTasks(listOfTasks, 2);
    }

    public void createTask(Task task) {
        taskCreator.createTask(taskHashMap, task, id);
        id++;
    }

    public void createEpic(Epic epic) {
        taskCreator.createTask(epicHashMap, epic, id);
        id++;
    }

    public void createSubtask(Subtask subtask) {
        taskCreator.createSubtask(subtaskHashMap, epicHashMap, subtask, id);
        id++;
    }

    public void deleteTasks() {
        taskDeleter.deleteAll(taskHashMap);
    }

    public void deleteEpics() {
        taskDeleter.deleteAll(epicHashMap);
    }

    public void deleteSubtasks() {
        taskDeleter.deleteAll(subtaskHashMap);
    }

    public Task getTaskById(int id) {
        Task task = (Task) taskGetter.getById(listOfTasks, id, 0);
        return task;
    }

    public Epic getEpicById(int id) {
        Epic epic = (Epic) taskGetter.getById(listOfTasks, id, 1);
        return epic;
    }

    public Subtask getSubtaskById(int id) {
        Subtask subtask = (Subtask) taskGetter.getById(listOfTasks, id, 2);
        return subtask;
    }

    public void updateTask(Task task, int id) {
        taskUpdater.updateById(task, id, listOfTasks, 0);
    }

    public void updateEpic(Epic epic, int id) {
        taskUpdater.updateById(epic, id, listOfTasks, 1);
    }

    public void updateSubtask(Subtask subtask, int id) {
        taskUpdater.updateById(subtask, id, listOfTasks, 2);
    }

    public void deleteTaskById(int id) {
        taskDeleter.deleteTaskById(taskHashMap, id);
    }

    public void deleteEpicById(int id) {
        taskDeleter.deleteEpicById(epicHashMap, id, subtaskHashMap);
    }

    public void deleteSubtaskById(int id) {
        taskDeleter.deleteSubtaskById(epicHashMap, id, subtaskHashMap);
    }

    public ArrayList getAllSubtasksFromEpic(int id) {
        ArrayList listOfSubtasks = taskGetter.getListOfSubtasks(epicHashMap, id);

        return listOfSubtasks;
    }
}
