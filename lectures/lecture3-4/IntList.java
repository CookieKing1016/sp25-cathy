public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r){
        first = f;
        rest = r;
    }

    public int get(int pos){
        if(pos == 0){
            return this.first;
        }
        return this.rest.get(pos-1);
    }
    /** 用递归方法，返回链表的长度*/
    public int size(){
        if (rest == null){
            return 1;
        }
        return 1 + this.rest.size();
    }


    public static void main(String[] args){
        IntList L = new IntList(15, null);
        L = new IntList(10,L);
        L = new IntList(5,L);

        System.out.println(L.size());
        System.out.println(L.get(2));
    }
}
