public class drawTriangle {
    public int N;

    public drawTriangle(int n){
        N = n;
    }

    public void draw(){
        int turns = 1;
        while (turns < N+1){
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
