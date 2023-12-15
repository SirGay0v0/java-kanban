package ManagerBusinessLogic;

import Tasks.Epic;
import Tasks.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс возвращает список всех объектов запрашиваемого класса.
 * Также содержит метод, который возвращает список из Subtask, принадлежащие одному Epic.
 * Третий метод возвращает объект по его id;
 */
public class TaskGetter {
    public Collection<Task> getAllTasks(ArrayList<HashMap> listOfTasks, int option) {
        Collection<Task> listOfFoundTasks = new ArrayList<>();

        for (Object task : listOfTasks.get(option).values()) {
            listOfFoundTasks.add((Task) task);
        }
        return listOfFoundTasks;
    }

    public Collection getListOfSubtasks(Map subtaskHashMap, int id) {
        Epic epic = (Epic) subtaskHashMap.get(id);
        Collection getList = epic.getIdSubTasks();
        return getList;
    }

    public Object getById(ArrayList<HashMap> listOfTasks, int id, int option){
        return listOfTasks.get(option).get(id);
    }
}
