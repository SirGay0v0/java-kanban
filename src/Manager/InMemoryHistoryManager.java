package Manager;

import Custom.Node;
import Tasks.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс реализует интерфейса HistoryManager.
 * Ведет учет истории просмотра в виде ArrayList<Task>.
 */
public class InMemoryHistoryManager implements HistoryManager {
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
    public void remove(int id) {
        List<Task> deleteList = new LinkedList<>();
        for (Task task : listTaskHistory) {
            if (task.getId() == id) {
                deleteList.add(task);
            }
        }
        listTaskHistory.removeAll(deleteList);
    }

    @Override
    public LinkedList<Task> getHistory() {
        return listTaskHistory;
    }

    @Override
    public String toString() {
        return "InMemoryHistoryManager{" + "listTaskHistory=" + listTaskHistory + '}';
    }


    public static class CustomLinkedList<T> {

        private int size = 0;
        private Node<T> head;
        private Node<T> tail;

        public CustomLinkedList() {
        }

        public void addLast(T element) {
            final Node<T> oldTail = tail;
            final Node<T> newNode = new Node<>(oldTail, element, null);
            tail = newNode;
            if (oldTail == null) head = newNode;
            else oldTail.next = newNode;
            size++;
        }

        public List<Task> getTasks() {
            List<Task> historyList = new ArrayList<>();
            Node<T> current = head;

            while (current != tail) {
                historyList.add((Task) current.item);
                current = current.next;
            }
            historyList.add((Task) current.item);
            return historyList;
        }
    }
}
