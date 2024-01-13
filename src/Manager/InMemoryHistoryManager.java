package Manager;

import Custom.Node;
import Tasks.Task;

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


    class CustomLinkedList<T> {

        private int size = 0;
        private Node<T> head;
        private Node<T> tail;

        public CustomLinkedList() {
        }

        public void addLast(T element) {
            // Реализуйте метод
            final Node<T> oldTail = tail;
            final Node<T> newNode = new Node<>(null, element, oldTail);
            tail = newNode;
            if (oldTail == null)
                head = newNode;
            else oldTail.prev = newNode;
            size++;
        }

        public void 
//        Node<E> node(int index) {
//            // assert isElementIndex(index);
//
//            if (index < (size >> 1)) {
//                Node<E> x = head;
//                for (int i = 0; i < index; i++)
//                    x = x.next;
//                return x;
//            } else {
//                Node<E> x = tail;
//                for (int i = size - 1; i > index; i--)
//                    x = x.prev;
//                return x;
//            }
//        }
//
//        public E get(int index) {
//            return node(index).data;
//        }
//
//        public void linkLast(E element) {
//            final var oldTail = tail;
//            final var newNode = new Node<E>(null, element, oldTail);
//            tail = newNode;
//            if (oldTail == null)
//                head = newNode;
//            else oldTail.prev = newNode;
//            size++;
//        }
//
//        public List<Task> getTasks(){
//            List<Task> listTasks = new ArrayList<>();
//            for(int i=0; i<size; i++){
//             listTasks.add((Task) node(i).data);
//            }
//            return listTasks;
//        }
    }
}
