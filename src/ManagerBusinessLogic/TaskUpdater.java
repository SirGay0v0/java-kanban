package ManagerBusinessLogic;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.Map;

/**
 * Класс обновляет существующей экземпляр по его id.
 * Если обновляемый элемент Subtask, то производится корректирока статуса Epic,
 * к которому он относится.
 */
public class TaskUpdater {

    public void updateTaskById(Task task, int idTask, Map<Integer, Task> taskMap) {
        if (taskMap.containsKey(idTask)) {
            task.setId(idTask);
            taskMap.put(idTask, task);
        }
    }

    public void updateEpicById(Epic epic, int idEpic, Map<Integer, Epic> epicMap) {
        if (epicMap.containsKey(idEpic)) {
            epic.setId(idEpic);
            epicMap.put(idEpic, epic);
        }
    }

    public void updateSubtaskById(Subtask subtask, int idSubtask, Map<Integer, Subtask> subtaskMap,
                                  Map<Integer, Epic> epicMap) {
        if (subtaskMap.containsKey(idSubtask)) {
            EpicStatusVerification epicStatusVerification = new EpicStatusVerification();

            subtask.setId(idSubtask);
            subtaskMap.put(idSubtask, subtask);

            Epic epic = epicMap.get(subtaskMap.get(idSubtask).getEpicOwnerId());
            epicStatusVerification.verifyStatus(epic, subtaskMap);

        }
    }

}
