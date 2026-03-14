package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    public static class ItemNode<T>{
        private T item;
        private ItemNode<T> next;
        private ItemNode<T> prev;

        public ItemNode(ItemNode<T> prev,T _item, ItemNode<T> next){
            item = _item;
            this.next = next;
            this.prev = prev;
        }
    }
    private ItemNode<T> sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new ItemNode<>(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Adds an item to the front of the deque.
     *
     * @param item the item to add
     */
    @Override
    public void addFirst(T item){
        ItemNode<T> oldFirst = sentinel.next;
        ItemNode<T> newNode = new ItemNode<>(sentinel,item,oldFirst);
        oldFirst.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }
    @Override
    public void addLast(T item){
        ItemNode<T> oldLast = sentinel.prev;
        ItemNode<T> newNode = new ItemNode<>(oldLast,item,sentinel);
        oldLast.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }
    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        if (size == 0){
            System.out.println("Deque is empty");
            return;
        }
        ItemNode<T> current = sentinel.next;
        for(int loc = 0;loc < size; loc++){
            System.out.print(current.item);
            if (loc < size - 1){
                System.out.print(" ");
            }
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public T removeLast(){
        if (size == 0){
            return null;
        }
        ItemNode<T> oldLast = sentinel.prev;
        oldLast.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        oldLast.prev = null;
        oldLast.next = null;
        size -= 1;
        return oldLast.item;
    }

    @Override
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        ItemNode<T> oldFirst = sentinel.next;
        oldFirst.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        oldFirst.prev = null;
        oldFirst.next = null;
        size -= 1;
        return oldFirst.item;
    }

    @Override
    public T get(int index){
        if (index < 0 || index >= size){
            return null;
        }
        ItemNode<T> current = sentinel;
        for (int loc = 0; loc <= index;loc++){
            current = current.next;
        }
        return current.item;
    }

    public T getRecursive(int index){
        if (index < 0 || index >= size){
            return null;
        }
        return getRecursiveHelper(sentinel.next,index);
    }
    private T getRecursiveHelper(ItemNode<T> p,int i){
        if (i == 0) {
            return p.item;
        }
        return getRecursiveHelper(p.next,i-1);
    }

    @Override
    public Iterator<T> iterator(){
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T>{
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T item = get(index);
            index += 1;
            return item;
        }
    }

    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque<?>)) {
            return false;
        }

        Deque<?> other = (Deque<?>) o;
        if (this.size() != other.size()) {
            return false;
        }

        for (int i = 0; i < size; i += 1) {
            T a = this.get(i);
            Object b = other.get(i);

            if (a == null) {
                if (b != null) {
                    return false;
                }
            } else {
                if (!a.equals(b)) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args){
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        L.addFirst(1);
        L.addFirst(3);
        L.get(1);
        L.getRecursive(2);
        L.printDeque();
        System.out.println(L.get(1));
        System.out.println(L.isEmpty());
        System.out.println(L.size());
    }
}

