package Manager;

import Custom.Node;
import Tasks.Task;

import java.util.*;


/**
 * Класс реализует интерфейс HistoryManager.
 * Весь класс является CustomLinkedList и хранит историю просмотров "внутри себя"
 */
public class InMemoryHistoryManager<T> implements HistoryManager {
    private Map<Integer, Node<T>> mapNode = new HashMap<>();
    private int size = 0;
    private Node<T> head;
    private Node<T> tail;


    public InMemoryHistoryManager() {

    }

    /**
     * Теперь перенаправляет изменение Node и ее создание на метод linkLast()
     */
    @Override
    public void add(Task task) {
        if (mapNode.containsKey(task.getId())) {
            removeNode(mapNode.get(task.getId()));
        }
        mapNode.put(task.getId(), linkLast((T) task));
    }

    /**
     * Теперь удаляет запись из CustomLinkedList истории и
     * из mapNode (там хранятся существующие элементы истории)
     */
    @Override
    public void remove(int id) {
        removeNode(mapNode.get(id));
        mapNode.remove(id);
    }

    /**
     * Теперь перенаправляет создание списка актуальной истории на метод getTasks()
     */
    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    /**
     * Создает новый элемент истории в конце CustomLinkedList и записывает
     * актуальную информацию о расположении отредактированных звеньев в HashMap
     */
    public Node<T> linkLast(T element) {
        final Node<T> newNode = new Node<>(null, element, null);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            final Node<T> oldTail = tail;
            newNode.prev = oldTail;
            oldTail.next = newNode;
            tail = newNode;

            Task oldTailTask = (Task) oldTail.item;
            mapNode.put(oldTailTask.getId(), oldTail);
        }
        size++;
        return newNode;
    }

    /**
     * Пробегается по всем звеньям CustomLinkedList и добавляет их в Arraylist,
     * после чего возвразает актуальную историю просмотра
     */
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

    /**
     * Удаляет звено из CustomLinkedList редактиуя ссылки соседних звеньев друг на друга,
     * после чего обновляет информацию об этих звеньях в HashMap
     */
    public void removeNode(Node<T> node) {
        Node<T> nodeToCorrectPrev = node.prev;
        Node<T> nodeToCorrectNext = node.next;

        size--;
        if (node == head) {
            node.next = null;
            node.item = null;
            if (nodeToCorrectNext != null) {
                nodeToCorrectNext.prev = null;
                head = nodeToCorrectNext;

                Task nextTask = (Task) nodeToCorrectNext.item;
                int nextId = nextTask.getId();

                mapNode.put(nextId, head);
            }
        } else if (node == tail) {
            node.prev = null;
            node.item = null;
            nodeToCorrectPrev.next = null;
            tail = nodeToCorrectPrev;

            Task prevTask = (Task) nodeToCorrectPrev.item;
            int prevId = prevTask.getId();

            mapNode.put(prevId, tail);
        } else {
            node.prev = null;
            node.next = null;
            node.item = null;
            nodeToCorrectPrev.next = nodeToCorrectNext;
            nodeToCorrectNext.prev = nodeToCorrectPrev;

            Task prevTask = (Task) nodeToCorrectPrev.item;
            int prevId = prevTask.getId();
            Task nextTask = (Task) nodeToCorrectNext.item;
            int nextId = nextTask.getId();

            mapNode.put(prevId, nodeToCorrectPrev);
            mapNode.put(nextId, nodeToCorrectNext);
        }
    }


    @Override
    public String toString() {
        return "InMemoryHistoryManager{" + "mapNode=" + mapNode + ", size=" + size + ", head=" + head + ", tail=" + tail + '}';
    }
}

