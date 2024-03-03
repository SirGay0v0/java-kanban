package ManagerBusinessLogic;

import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;

import java.util.Collection;
import java.util.Map;

/**
 * Класс отвечает за корректный статус отображения Epic.
 * Вызывается каждый раз, когда происходят манипуляции с Subtask.
 * Проходит по всем Subtask, если есть хоть один subtask со статусом IN_PROGRESS,
 * то статус меняется на соответствующий. Если таковых нет, и нет ни одного NEW, то статус
 * меняется на DONE.
 */
public class EpicStatusVerification {

    public void verifyStatus(Epic epic, Map<Integer,Subtask> subtaskHashMap) {

        Collection<Integer> listOfSubTasks = epic.getIdSubTasks();
        int counterSameStatus = 0;
        Status firstSubtaskStatus = null;

        for (Integer idSubtask : listOfSubTasks) {
            Subtask subtask = subtaskHashMap.get(idSubtask);
            firstSubtaskStatus = subtask.getStatus();

            for (Integer idSecondSubtask : listOfSubTasks) {
                subtask = subtaskHashMap.get(idSecondSubtask);

                if (firstSubtaskStatus != subtask.getStatus()) {
                    epic.setStatus(Status.IN_PROGRESS);
                    break;
                } else {
                    counterSameStatus++;
                }
            }
            break;
        }
        if (counterSameStatus == listOfSubTasks.size()) {
            epic.setStatus(firstSubtaskStatus);
        }
    }
}
