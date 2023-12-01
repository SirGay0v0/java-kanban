package ManagerUtilities;

import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс отвечает за корректный статус отображения Epic.
 * Вызывается каждый раз, когда происходят манипуляции с Subtask.
 * Проходит по всем Subtask, если есть хоть один subtask со статусом IN_PROGRESS,
 * то статус меняется на соответствующий. Если таковых нет, и нет ни одного NEW, то статус
 * меняется на DONE.
 */
public class EpicStatusVerification {

    public void verifyStatus(Epic epic, HashMap subtaskHashMap) {

        ArrayList<Integer> listOfSubTasks = epic.getSubtasksList();
        int counterNewSubtasks = 0;

        for (Integer idSubtask : listOfSubTasks) {
            Subtask subtask = (Subtask) subtaskHashMap.get(idSubtask);

            if (subtask.getSubtaskStatus() == Status.IN_PROGRESS) {
                epic.setEpicStatus(Status.IN_PROGRESS);
                counterNewSubtasks = -1;
                break;
            } else if (subtask.getSubtaskStatus() == Status.NEW) {
                counterNewSubtasks++;
            }
        }

        if (counterNewSubtasks == epic.subtasksList.size()) {
            epic.setEpicStatus(Status.NEW);
        } else if (counterNewSubtasks == -1) {
        } else {
            epic.setEpicStatus(Status.DONE);
        }
    }
}
