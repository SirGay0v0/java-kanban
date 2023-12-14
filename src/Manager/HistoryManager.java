package Manager;

import Tasks.Task;

import java.util.ArrayList;

/**
 * Интерфейс для InMemoryHistoryManager.
 */
public interface HistoryManager {

    void add(Task task);

    ArrayList<Task> getHistory();
}
