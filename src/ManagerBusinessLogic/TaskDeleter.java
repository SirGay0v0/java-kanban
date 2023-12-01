package ManagerBusinessLogic;

import Tasks.Epic;
import Tasks.Subtask;

import java.util.HashMap;

/**
 * Класс содержит методы по удалению всех экземпляров запрашиваемого класса, а также отдельные
 * методы для удаления по запрашиваемому id каждого класса.
 */
public class TaskDeleter {
    public void deleteAll(HashMap hashMap) {
        hashMap.clear();
    }

    public void deleteTaskById(HashMap taskHashMap, int id) {
        taskHashMap.remove(id);
    }

    public void deleteEpicById(HashMap epicHashMap, int epicId, HashMap subtaskHashMap) {
        Epic epic = (Epic) epicHashMap.get(epicId);

        for (Integer subtaskId : epic.getSubtasksList()) {
            subtaskHashMap.remove(subtaskId);
        }
        epicHashMap.remove(epicId);
    }

    public void deleteSubtaskById(HashMap epicHashMap, Integer subtaskId, HashMap subtaskHashMap) {
        Subtask subtask = (Subtask) subtaskHashMap.get(subtaskId);
        Epic epic = (Epic) epicHashMap.get(subtask.getEpicOwnerId());

        epic.getSubtasksList().remove(subtaskId);
        subtaskHashMap.remove(subtaskId);

        EpicStatusVerification epicStatusVerification = new EpicStatusVerification();
        epicStatusVerification.verifyStatus(epic, subtaskHashMap);
    }
}
