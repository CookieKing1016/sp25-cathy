public class drawingTriangle {
    public static void main (String[] args){
        int turns = 1;
        while (turns < 6){
            int count = 0;
            while (count < turns){
                System.out.print("*");
                count = count + 1;
            }
            System.out.println();
            turns = turns + 1;
        }
    }
}

