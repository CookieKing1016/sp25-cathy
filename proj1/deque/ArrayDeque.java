package deque;

public class ArrayDeque<T>  {
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

    public int size(){
        return this.length;
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
    }


}
