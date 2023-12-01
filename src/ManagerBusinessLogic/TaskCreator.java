package ManagerBusinessLogic;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.HashMap;

/**
 * Класс отвечает зав создание Task, Epic и отдельно для Subtask, так как после создания
 * вносятся изменения в список subtaskList экземпляра класса Epic и вызывается метод verifyStatus
 * для присвоения корректного статуса экземпляру класса Epic.
 */
public class TaskCreator {
    public void createTask(HashMap taskHashMap, Task task, int id) {
        task.setId(id);
        taskHashMap.put(id, task);
    }

    public void createSubtask(HashMap subtaskHashMap, HashMap epicHashMap, Subtask subtask, int id) {

        EpicStatusVerification epicStatusVerification = new EpicStatusVerification();
        subtask.setId(id);
        subtaskHashMap.put(id, subtask);

        Epic epic = (Epic) epicHashMap.get(subtask.getEpicOwnerId());
        epic.getSubtasksList().add(subtask.getId());
        epicHashMap.put(epic.getId(), epic);

        epicStatusVerification.verifyStatus(epic, subtaskHashMap);
    }
}
