package ManagerBusinessLogic;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.*;

/**
 * Класс возвращает список всех объектов запрашиваемого класса.
 * Также содержит метод, который возвращает список из Subtask, принадлежащие одному Epic.
 * Третий метод возвращает объект по его id;
 */
public class TaskGetter {
    public Collection<Task> getAllTasks(Map<Integer, Task> taskHashMap) {
        return new ArrayList<>(taskHashMap.values());
    }
    public Collection<Epic> getAllEpics(Map<Integer, Epic> epicHashMap) {
        return new ArrayList<>(epicHashMap.values());
    }
    public Collection<Subtask> getAllSubtasks(Map<Integer, Subtask> subtaskHashMap) {
        return new ArrayList<>(subtaskHashMap.values());
    }

    public Collection<Integer> getListOfSubtasks(Map<Integer, Epic> epicHashMap, int id) {
        Epic epic = epicHashMap.get(id);
        return epic.getIdSubTasks();
    }

    public Task getById(List<HashMap<Integer, ? extends Task>> listOfTasks, int id, int option) {
        return listOfTasks.get(option).get(id);
    }
}
