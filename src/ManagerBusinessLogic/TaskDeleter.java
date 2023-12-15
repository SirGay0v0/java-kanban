package ManagerBusinessLogic;

import Tasks.Epic;
import Tasks.Subtask;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс содержит методы по удалению всех экземпляров запрашиваемого класса, а также отдельные
 * методы для удаления по запрашиваемому id каждого класса.
 */
public class TaskDeleter {
    public void deleteAll(Map hashMap) {
        hashMap.clear();
    }

    public void deleteTaskById(Map taskHashMap, int id) {
        taskHashMap.remove(id);
    }

    public void deleteEpicById(Map epicHashMap, int epicId, Map subtaskHashMap) {
        Epic epic = (Epic) epicHashMap.get(epicId);

        for (Integer subtaskId : epic.getIdSubTasks()) {
            subtaskHashMap.remove(subtaskId);
        }
        epicHashMap.remove(epicId);
    }

    public void deleteSubtaskById(Map epicHashMap, Integer subtaskId, Map subtaskHashMap) {
        Subtask subtask = (Subtask) subtaskHashMap.get(subtaskId);
        Epic epic = (Epic) epicHashMap.get(subtask.getEpicOwnerId());

        epic.getIdSubTasks().remove(subtaskId);
        subtaskHashMap.remove(subtaskId);

        EpicStatusVerification epicStatusVerification = new EpicStatusVerification();
        epicStatusVerification.verifyStatus(epic, subtaskHashMap);
    }
}
