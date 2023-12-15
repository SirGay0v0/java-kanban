package Manager;

import Tasks.Task;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Класс реализует интерфейса HistoryManager.
 * Ведет учет истории просмотра в виде ArrayList<Task>.
 */
public class InMemoryHistoryManager implements HistoryManager {
    private int counter;
    private LinkedList<Task> listTaskHistory;

    public InMemoryHistoryManager() {
        listTaskHistory = new LinkedList<>();
    }

    @Override
    public void add(Task task) {
        if (listTaskHistory.size() == 10) {
            listTaskHistory.remove(0);
            listTaskHistory.add(task);
        } else {
            listTaskHistory.add(task);
        }
    }

    @Override
    public LinkedList<Task> getHistory() {
        return listTaskHistory;
    }
}
