package deque;

public class LinkedListDeque<T> implements Deque<T> {
    public static class itemNode<T>{
        public T item;
        public itemNode<T> next;
        public itemNode<T> prev;

        public itemNode(itemNode<T> prev,T i, itemNode<T> next){
            item = i;
            this.next = next;
            this.prev = prev;
        }
    }
    public itemNode<T> sentinel;
    public int size;

    public LinkedListDeque() {
        sentinel = new itemNode(null,63,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    @Override
    public void addFirst(T item){
        itemNode oldFirst = sentinel.next;
        itemNode newNode = new itemNode(sentinel,item,oldFirst);
        oldFirst.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }
    @Override
    public void addLast(T item){
        itemNode oldLast = sentinel.prev;
        itemNode newNode = new itemNode(oldLast,item,sentinel);
        oldLast.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }
    @Override
    public boolean isEmpty(){
        if (size != 0){
            return false;
        }
        return true;
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
        itemNode<T> current = sentinel.next;
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
        itemNode oldLast = sentinel.prev;
        oldLast.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        oldLast.prev = null;
        oldLast.next = null;
        size -= 1;
        return (T) oldLast.item;
    }

    @Override
    public T removeFirst(){
        if (size == 0){
            return null;
        }
        itemNode oldFirst = sentinel.next;
        oldFirst.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        oldFirst.prev = null;
        oldFirst.prev = null;
        size -= 1;
        return (T) oldFirst.item;
    }

    @Override
    public T get(int index){
        if (index < 0 || index >= size){
            return null;
        }
        itemNode<T> current = sentinel;
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
    private T getRecursiveHelper(itemNode<T> p,int i){
        if (i== 0){
            return p.item;
        }
        return getRecursiveHelper(p.next,i-1);
    }




    public static void main(String[] args){
        LinkedListDeque<Integer> L = new LinkedListDeque();
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

