package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        // 2. Define the sizes of N we want to test
        // Starting at 1000 and doubling up to 128000
        for (int N = 1000; N <= 128000; N = N * 2) {

            // 3. Create a fresh AList to test
            AList<Integer> testList = new AList<>();

            // 4. Start the timer
            edu.princeton.cs.algs4.Stopwatch sw = new edu.princeton.cs.algs4.Stopwatch();

            // 5. Perform the operations
            for (int i = 0; i < N; i += 1) {
                testList.addLast(i);
            }

            // 6. Stop the timer (get elapsed time)
            double timeInSeconds = sw.elapsedTime();

            // 7. Store the results in our tracking lists
            Ns.addLast(N);
            times.addLast(timeInSeconds);
            opCounts.addLast(N);
        }
        // 8. Print the table
        printTimingTable(Ns, times, opCounts);
    }
}
