package ManagerBusinessLogic;


import Manager.HistoryManager;
import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.List;
import java.util.Map;

/**
 * Класс содержит методы по удалению всех экземпляров запрашиваемого класса, а также отдельные
 * методы для удаления по запрашиваемому id каждого класса.
 */
public class TaskDeleter {
    public void deleteAll(Map<Integer, ? extends Task> hashMap, HistoryManager historyManager) {
        for (Map.Entry<Integer, ?> entry : hashMap.entrySet()) {
            if (historyManager.getHistory()!=null && historyManager.getHistory().contains(entry.getValue())) {
                historyManager.remove(entry.getKey());
            }
        }
        hashMap.clear();
    }

    public void deleteTaskById(Map taskHashMap, int id) {
        if(taskHashMap.containsKey(id)) {
            taskHashMap.remove(id);
        }
    }

    /**
     * Теперь у метода новый аргумент, который позволяет стирать информацию из истории по мере удаления
     * SubTask принадлежащих этому Epic
     */
    public void deleteEpicById(Map epicHashMap, int epicId, Map subtaskHashMap, HistoryManager inMemoryHistoryManager) {
        if(epicHashMap.containsKey(epicId)) {
            Epic epic = (Epic) epicHashMap.get(epicId);

            for (Integer subtaskId : epic.getIdSubTasks()) {
                inMemoryHistoryManager.remove(subtaskId);
                subtaskHashMap.remove(subtaskId);
            }
            epicHashMap.remove(epicId);
        }
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
