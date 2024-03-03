package ManagerBusinessLogic;


import Manager.HistoryManager;
import Tasks.Epic;
import Tasks.Subtask;
import Tasks.Task;

import java.util.Map;

/**
 * Класс содержит методы по удалению всех экземпляров запрашиваемого класса, а также отдельные
 * методы для удаления по запрашиваемому id каждого класса.
 */
public class TaskDeleter {
    public void deleteAll(Map<Integer, ? extends Task> hashMap, HistoryManager historyManager) {
        for (Map.Entry<Integer, ? extends Task> entry : hashMap.entrySet()) {
            if (historyManager.getHistory() != null && historyManager.getHistory().contains(entry.getValue())) {
                historyManager.remove(entry.getKey());
            }
        }
        hashMap.clear();
    }

    public void deleteTaskById(Map<Integer, Task> taskHashMap, int id) {
        if (taskHashMap.get(id) != null) {
            taskHashMap.remove(id);
        }
    }

    /**
     * Теперь у метода новый аргумент, который позволяет стирать информацию из истории по мере удаления
     * SubTask принадлежащих этому Epic
     */
    public void deleteEpicById(Map<Integer, Epic> epicHashMap, int epicId, Map<Integer, Subtask> subtaskHashMap, HistoryManager inMemoryHistoryManager) {
        if (epicHashMap.containsKey(epicId)) {
            Epic epic = epicHashMap.get(epicId);

            for (Integer subtaskId : epic.getIdSubTasks()) {
                inMemoryHistoryManager.remove(subtaskId);
                subtaskHashMap.remove(subtaskId);
            }
            epicHashMap.remove(epicId);
        }
    }

    public void deleteSubtaskById(Map<Integer, Epic> epicHashMap, Integer subtaskId, Map<Integer, Subtask> subtaskHashMap) {
        Subtask subtask = subtaskHashMap.get(subtaskId);
        Epic epic = epicHashMap.get(subtask.getEpicOwnerId());

        epic.getIdSubTasks().remove(subtaskId);
        subtaskHashMap.remove(subtaskId);

        EpicStatusVerification epicStatusVerification = new EpicStatusVerification();
        epicStatusVerification.verifyStatus(epic, subtaskHashMap);
    }
}
