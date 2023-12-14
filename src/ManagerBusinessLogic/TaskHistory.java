package ManagerBusinessLogic;

import Tasks.Task;

import java.util.ArrayList;

public class TaskHistory {
    private int counter;
    private ArrayList<Task> listTaskHistory;

    public TaskHistory() {
        counter = 0;
        listTaskHistory = new ArrayList<>();
    }

    public ArrayList<Task> getListTaskHistory() {
        return listTaskHistory;
    }

    public void addToHistory(Task task) {
        if (counter == 10) {
            listTaskHistory.remove(0);
            listTaskHistory.add(task);
        } else {
            listTaskHistory.add(task);
            counter++;
        }
    }
}
