public class Lists1Exercises {
    /** Returns an IntList identical to L, but with
     * each element incremented by x. L is not allowed
     * to change. */
    public static IntList incrList(IntList L, int x) {
        if (L == null) {
            return null;
        }
        IntList res = new IntList(L.first + x, incrList(L.rest, x));
        return res;
    }

    /** Returns an IntList identical to L, but with
     * each element incremented by x. Not allowed to use
     * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        if (L == null) {
            return null;
        }
        L.first = L.first + x;
        dincrList(L.rest,x);
        return L;
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);

        System.out.println("Original L size: " + L.size());
        System.out.println("Original L value at index 1: " + L.get(1)); // 应该是 7

        // --- 测试 incrList (非破坏性) ---
        System.out.println("--- Testing incrList (x=3) ---");
        IntList L_incr = incrList(L, 3);

        System.out.print("New list (should be 8 -> 10 -> 12): ");
        printList(L_incr);

        System.out.print("Original list (should still be 5 -> 7 -> 9): ");
        printList(L);

        // --- 测试 dincrList (破坏性) ---
        System.out.println("--- Testing dincrList (x=3) ---");
        IntList L_dincr = dincrList(L, 3);

        System.out.print("List after dincr (should be 8 -> 10 -> 12): ");
        printList(L_dincr);

        System.out.print("Original list (should NOW be 8 -> 10 -> 12): ");
        printList(L);
    }

    /** 辅助方法：用来打印 IntList 的所有内容 */
    public static void printList(IntList L) {
        IntList p = L;
        while (p != null) {
            System.out.print(p.first + (p.rest != null ? " -> " : ""));
            p = p.rest;
        }
        System.out.println();
    }
}
