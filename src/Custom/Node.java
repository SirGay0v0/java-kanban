package Custom;

/**
 * Класс реализует концепцию звена для CustomLinkedList.
 * Представляет собой звено из 3-ех частей:
 * 1. Это сам элемент/объект и т.д.
 * 2. Это ссылка на следующее звено
 * 3. Это ссылка на предыдущее звено
 */
public class Node<E> {
    public E item;
    public Node<E> next;
    public Node<E> prev;

    public Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;

    }
}

