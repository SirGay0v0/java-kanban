package ManagerBusinessLogic;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;

public class EpicTimeVerification {
    public void verifyTime(Epic epic, Map subtaskHashMap) {

        LocalDateTime epicStartTime = epic.getIdSubTasks().stream()
                .map(idSubtask -> (Subtask) subtaskHashMap.get(idSubtask))
                .min(Comparator.comparing(Task::getStartTime, Comparator.nullsFirst(Comparator.reverseOrder())))
                .stream().findFirst().get().getStartTime();
        if (epicStartTime != null) {
            LocalDateTime epicEndTime = epic.getIdSubTasks().stream()
                    .map(idSubtask -> (Subtask) subtaskHashMap.get(idSubtask))
                    .max(Comparator.comparing(Task::getEndTime))
                    .stream().findFirst().get().getEndTime();

            epic.setStartTime(epicStartTime);
            epic.setDuration(Duration.between(epicStartTime, epicEndTime));
        } else {
            epic.setStartTime((LocalDateTime) null);
            epic.setDuration((Duration) null);
        }


    }
}
