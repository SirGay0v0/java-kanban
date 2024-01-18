package Manager;

import Tasks.Task;

import java.util.List;

/**
 * Интерфейс для InMemoryHistoryManager.
 */
public interface HistoryManager {
    void add(Task task);

    void remove(int id);

    List<Task> getHistory();
}
