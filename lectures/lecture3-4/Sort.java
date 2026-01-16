public class Sort {
    public static void sort(String[] input){
        //Find the smallest item
        //Move it to the front
        //Selection sort the reset 如何实现递归？
        sort(input,0);
    }
    /**递归实现 sorts x starting at position start*/
    private static void sort(String[] x, int start){
        if (start == x.length){
            return;
        }
        int smallestIndex = findSmallest(x,start);
        swap(x,start,smallestIndex);
        sort(x,start + 1);
    }

    /** Swap item a with b*/
    public static void swap(String[] x,int a,int b){
        String c = x[a];
        x[a] = x[b];
        x[b] = c;
    }

    /**find smallest*/
    private static int findSmallest(String[] x,int start){
        int smallestIndex = start;
        for (int i = start; i < x.length; i+=1){
            int cmp = x[i].compareTo(x[smallestIndex]);
            if (cmp < 0){
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }
}
