package ManagerUtilities;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс обновляет существующей экземпляр по его id.
 * Если обновляемый элемент Subtask, то производится корректирока статуса Epic,
 * к которому он относится.
 */
public class TaskUpdater {

    public void updateById(Task task, int idTask, ArrayList<HashMap> listOfTasks, int option) {
        task.setId(idTask);
        listOfTasks.get(option).put(idTask, task);

        if (option == 2) {
            EpicStatusVerification epicStatusVerification = new EpicStatusVerification();
            Epic epic = (Epic) listOfTasks.get(1).get(((Subtask) task).getEpicOwnerId());

            epicStatusVerification.verifyStatus(epic, listOfTasks.get(2));
        }
    }
}
