package ManagerBusinessLogic;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс отвечает зав создание Task, Epic и отдельно для Subtask, так как после создания
 * вносятся изменения в список subtaskList экземпляра класса Epic и вызывается метод verifyStatus
 * для присвоения корректного статуса экземпляру класса Epic.
 */
public class TaskCreator {
    public void createTask(Map<Integer, Task> taskMap, Task task, int id) {
        if (!taskMap.containsValue(task)) {
            task.setId(id);
            taskMap.put(id, task);
        }
    }

    public void createEpic(Map<Integer, Epic> epicMap, Epic epic, int id) {
        epic.setId(id);
        epicMap.put(id, epic);
    }

    public void createSubtask(List<HashMap<Integer, ? extends Task>> listOfTasks, Subtask subtask, int id) {
        EpicStatusVerification epicStatusVerification = new EpicStatusVerification();
        EpicTimeVerification epicTimeVerification = new EpicTimeVerification();
        HashMap<Integer, Subtask> subtaskHashMap = (HashMap<Integer, Subtask>) listOfTasks.get(2);
        HashMap<Integer, Epic> epicHashMap = (HashMap<Integer, Epic>) listOfTasks.get(1);
        Epic epic = epicHashMap.get(subtask.getEpicOwnerId());

        subtask.setId(id);
        subtaskHashMap.put(id, subtask);

        epic.getIdSubTasks().add(subtask.getId());
        epicHashMap.put(epic.getId(), epic);

        epicStatusVerification.verifyStatus(epic, subtaskHashMap);
        epicTimeVerification.verifyTime(epic, subtaskHashMap);

    }
}
