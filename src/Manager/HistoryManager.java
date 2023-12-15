package Manager;

import Tasks.Task;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Интерфейс для InMemoryHistoryManager.
 */
public interface HistoryManager {

    void add(Task task);

    LinkedList<Task> getHistory();
}
