package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T>  {
    private int length;
    private int size;
    private int first;
    private int last;
    private T[] arr;

    public ArrayDeque(){
        this.arr = (T[]) new Object[8];
        this.size = 8;
        this.length = 0;
        this.first = 0;
        this.last = 0;
    }

    @Override
    public void addFirst(T item) {
        if (size - 1 == length) {
            resize(this.size * 2);
        }
        if (!isEmpty()){
            if (first == 0) {
                first = size-1;
            } else {
                first = first-1;
            }
        }
        arr[first] = item;
        length++;
    }

    @Override
    public void addLast(T item) {
        if (this.length == this.size - 1) {
            resize(this.size * 2);
        }
        if (!isEmpty()) {
            this.last = (this.last + 1) % this.size;
        }
        this.arr[this.last] = item;
        this.length++;
    }

    @Override
    public int size(){
        return this.length;
    }

    @Override
    public void printDeque() {
        int i = this.first;
        while (i != this.last){
            System.out.print(arr[i] + " ");
            i = (i + 1) % size;
        }
        System.out.println(this.arr[i]);
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        length--;
        T ret = arr[first];
        first = (first + 1) % size;
        if (this.length * 4 < this.size) {
            resize(this.size / 2);
        }
        return ret;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        length--;
        T ret = arr[last];
        last = (last - 1 + size) % size;
        if (this.length * 4 < this.size) {
            resize(this.size / 2);
        }
        return ret;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= length){
            return null;
        }
        int position = (index + this.first) % this.size;
        return this.arr[position];
    }

    private void resize(int s){
        T[] temp = (T[]) new Object[s];
        int i = this.first;
        int j = 0;
        while (j < this.length){
            temp[j] = this.arr[i];
            i = (i+1) % this.size;
            j++;
        }
        this.size = s;
        this.first = 0;
        if (isEmpty()) {
            this.last = 0;
        } else {
            this.last = j - 1;
        }
        this.arr = temp;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T>{
        private int index = 0;
        @Override
        public boolean hasNext() {
            return (index < length);
        }

        @Override
        public T next() {
            T item = get(index);
            index += 1;
            return item;
        }
    }

    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (!(o instanceof Deque<?>)){
            return false;
        }
        Deque<?> other = (Deque<?>) o;
        if (other.size() != this.size()){
            return false;
        }
        for (int i = 0; i < length; i += 1) {
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
}
