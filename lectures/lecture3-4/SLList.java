/** An SLList is a list of integers, which hides the terrible truth
 * of the nakedness within. */
public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }
    /**第一个item（如果存在）是sentinel.next */
    private IntNode sentinel;
    private int size;

    public SLList(){
        sentinel = new IntNode(63,null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x,null);
        size = 1;
    }
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }
    public int getFirst() {
        return sentinel.next.item;
    }
    public void addLast(int x){
//        if (first == null){
//            first = new IntNode(x,null); /** 空链表 special case*/
//            size += 1;
//            return;
//        }
        IntNode p = sentinel;
        while (p.next != null){
            p = p.next;
        }
        p.next = new IntNode(x,null);
        size += 1;
    }
    private static int size(IntNode p){
        if (p.next == null){
            return 1;
        }
        return 1 + size(p.next);
    }
    public int size(){
        return size(sentinel.next);
    }

    public static void main(String[] args) {
        SLList L = new SLList();
        L.addLast(18);
        L.addFirst(22);
        int x = L.getFirst();
        System.out.println(L.size());
        System.out.println(L.size);
    }
}


