package ManagerBusinessLogic;

import Tasks.Epic;
import Tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс возвращает список всех объектов запрашиваемого класса.
 * Также содержит метод, который возвращает список из Subtask, принадлежащие одному Epic.
 * Третий метод возвращает объект по его id;
 */
public class TaskGetter {
    public ArrayList<Task> getAllTasks(ArrayList<HashMap> listOfTasks, int option) {
        ArrayList<Task> listOfFoundTasks = new ArrayList<Task>();

        for (Object task : listOfTasks.get(option).values()) {
            listOfFoundTasks.add((Task) task);
        }
        return listOfFoundTasks;
    }

    public ArrayList getListOfSubtasks(HashMap subtaskHashMap, int id) {
        Epic epic = (Epic) subtaskHashMap.get(id);
        ArrayList getList = epic.getSubtasksList();
        return getList;
    }

    public Object getById(ArrayList<HashMap> listOfTasks, int id, int option){
        return listOfTasks.get(option).get(id);
    }
}
