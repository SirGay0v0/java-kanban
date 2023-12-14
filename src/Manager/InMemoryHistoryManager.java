package Manager;

import Tasks.Task;

import java.util.ArrayList;
/**
 * Класс реализует интерфейса HistoryManager.
 * Ведет учет истории просмотра в виде ArrayList<Task>.
 */
public class InMemoryHistoryManager implements HistoryManager {
    private int counter;
    private ArrayList<Task> listTaskHistory;

    public InMemoryHistoryManager() {
        counter = 0;
        listTaskHistory = new ArrayList<>();
    }

    @Override
    public void add(Task task) {
        if (counter == 10) {
            listTaskHistory.remove(0);
            listTaskHistory.add(task);
        } else {
            listTaskHistory.add(task);
            counter++;
        }
    }

    @Override
    public ArrayList getHistory() {
        return listTaskHistory;
    }
}
