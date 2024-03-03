package ManagerBusinessLogic;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

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

    public void createSubtask1(Map<Integer, Epic> epicMap, Map<Integer, Subtask> subtaskMap, Subtask subtask, int id) {
        EpicStatusVerification epicStatusVerification = new EpicStatusVerification();
        EpicTimeVerification epicTimeVerification = new EpicTimeVerification();
        Epic epic = epicMap.get(subtask.getEpicOwnerId());

        subtask.setId(id);
        subtaskMap.put(id, subtask);

        epic.getIdSubTasks().add(subtask.getId());
        epicMap.put(epic.getId(), epic);

        epicStatusVerification.verifyStatus(epic, subtaskMap);
        epicTimeVerification.verifyTime(epic, subtaskMap);

    }
}
