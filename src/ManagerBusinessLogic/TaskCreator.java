package ManagerBusinessLogic;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс отвечает зав создание Task, Epic и отдельно для Subtask, так как после создания
 * вносятся изменения в список subtaskList экземпляра класса Epic и вызывается метод verifyStatus
 * для присвоения корректного статуса экземпляру класса Epic.
 */
public class TaskCreator {
    public void createTask(Map taskHashMap, Task task, int id) {
        if (!taskHashMap.containsValue(task)) {
            task.setId(id);
            taskHashMap.put(id, task);
        }
    }

    public void createSubtask(ArrayList<HashMap> listOfTasks, Subtask subtask, int id) {
        EpicStatusVerification epicStatusVerification = new EpicStatusVerification();
        HashMap subtaskHashMap = listOfTasks.get(2);
        HashMap epicHashMap = listOfTasks.get(1);
        Epic epic = (Epic) epicHashMap.get(subtask.getEpicOwnerId());

        subtask.setId(id);
        subtaskHashMap.put(id, subtask);

        epic.getIdSubTasks().add(subtask.getId());
        epicHashMap.put(epic.getId(), epic);

        epicStatusVerification.verifyStatus(epic, subtaskHashMap);

    }
}
