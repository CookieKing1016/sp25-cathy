package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        // M 是固定的操作次数，设为 10000
        int M = 10000;

        for (int N = 1000; N <= 128000; N = N * 2) {
            // 1. 创建 SLList 并添加 N 个元素
            SLList<Integer> testList = new SLList<>();
            for (int i = 0; i < N; i++) {
                testList.addLast(i);
            }

            // 2. 只有在执行 getLast 操作时才开始计时
            edu.princeton.cs.algs4.Stopwatch sw = new edu.princeton.cs.algs4.Stopwatch();

            // 3. 执行 M 次 getLast 操作
            for (int j = 0; j < M; j++) {
                testList.getLast();
            }

            // 4. 记录时间
            double timeInSeconds = sw.elapsedTime();

            // 5. 存储数据
            Ns.addLast(N);
            times.addLast(timeInSeconds);
            opCounts.addLast(M); // 注意这里存的是 M，因为我们执行了 M 次操作
        }

        printTimingTable(Ns, times, opCounts);
    }

}
