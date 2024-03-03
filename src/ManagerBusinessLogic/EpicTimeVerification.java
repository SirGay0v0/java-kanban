package ManagerBusinessLogic;

import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

public class EpicTimeVerification {
    public void verifyTime(Epic epic, Map<Integer, Subtask> subtaskHashMap) {

        LocalDateTime epicStartTime = Objects.requireNonNull(epic.getIdSubTasks().stream()
                .map(subtaskHashMap::get)
                .min(Comparator.comparing(Task::getStartTime, Comparator.nullsFirst(Comparator.reverseOrder())))
                .stream().findFirst().orElse(null)).getStartTime();

        if (epicStartTime != null) {
            LocalDateTime epicEndTime = Objects.requireNonNull(epic.getIdSubTasks().stream()
                    .map(subtaskHashMap::get)
                    .max(Comparator.comparing(Task::getEndTime))
                    .stream().findFirst().orElse(null)).getEndTime();

            epic.setStartTime(epicStartTime);
            epic.setDuration(Duration.between(epicStartTime, epicEndTime));
        } else {
            epic.setStartTime(null);
            epic.setDuration(null);
        }


    }
}
