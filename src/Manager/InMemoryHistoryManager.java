package Manager;

import Custom.Node;
import Tasks.Task;

import java.util.*;


/**
 * Класс реализует интерфейс HistoryManager.
 * Весь класс является CustomLinkedList и хранит историю просмотров "внутри себя"
 */
public class InMemoryHistoryManager implements HistoryManager {
    private final Map<Integer, Node<Task>> mapNode = new HashMap<>();
    private int size = 0;
    private Node<Task> head;
    private Node<Task> tail;


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
        mapNode.put(task.getId(), linkLast(task));
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
    public Node<Task> linkLast(Task task) {
        final Node<Task> newNode = new Node<>(null, task, null);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            final Node<Task> oldTail = tail;
            newNode.prev = oldTail;
            oldTail.next = newNode;
            tail = newNode;

            Task oldTailTask = oldTail.item;
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
        Node<Task> current = head;

        while (current != tail) {
            historyList.add(current.item);
            current = current.next;
        }
        if (head != null && current.item!=null) {
                historyList.add(current.item);
            return historyList;
        } else return null;
    }

    /**
     * Удаляет звено из CustomLinkedList редактиуя ссылки соседних звеньев друг на друга,
     * после чего обновляет информацию об этих звеньях в HashMap
     */
    public void removeNode(Node<Task> node) {
        Node<Task> nodeToCorrectPrev = node.prev;
        Node<Task> nodeToCorrectNext = node.next;

        size--;
        if (node == head) {
            node.next = null;
            node.item = null;
            if (nodeToCorrectNext != null) {
                nodeToCorrectNext.prev = null;
                head = nodeToCorrectNext;

                Task nextTask = nodeToCorrectNext.item;
                int nextId = nextTask.getId();

                mapNode.put(nextId, head);
            }
        } else if (node == tail) {
            node.prev = null;
            node.item = null;
            nodeToCorrectPrev.next = null;
            tail = nodeToCorrectPrev;

            Task prevTask = nodeToCorrectPrev.item;
            int prevId = prevTask.getId();

            mapNode.put(prevId, tail);
        } else {
            node.prev = null;
            node.next = null;
            node.item = null;
            nodeToCorrectPrev.next = nodeToCorrectNext;
            nodeToCorrectNext.prev = nodeToCorrectPrev;

            Task prevTask = nodeToCorrectPrev.item;
            int prevId = prevTask.getId();
            Task nextTask = nodeToCorrectNext.item;
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

