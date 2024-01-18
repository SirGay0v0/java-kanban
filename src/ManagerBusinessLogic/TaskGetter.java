package ManagerBusinessLogic;

import Tasks.Epic;
import Tasks.Task;

import java.util.*;

/**
 * Класс возвращает список всех объектов запрашиваемого класса.
 * Также содержит метод, который возвращает список из Subtask, принадлежащие одному Epic.
 * Третий метод возвращает объект по его id;
 */
public class TaskGetter {
    public Collection<Task> getAllTasks(List<HashMap<String, ? extends Task>> listOfTasks, int option) {
        Collection<Task> listOfFoundTasks = new ArrayList<>();

        listOfFoundTasks.addAll(listOfTasks.get(option).values());
        return listOfFoundTasks;
    }

    public Collection getListOfSubtasks(Map subtaskHashMap, int id) {
        Epic epic = (Epic) subtaskHashMap.get(id);
        Collection getList = epic.getIdSubTasks();
        return getList;
    }

    public Object getById(ArrayList<HashMap> listOfTasks, int id, int option) {
        return listOfTasks.get(option).get(id);
    }
}
