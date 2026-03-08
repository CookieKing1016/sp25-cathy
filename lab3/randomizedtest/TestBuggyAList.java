package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        // 1. 初始化两个列表
        AListNoResizing<Integer> correctAList = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();

        // 2. 向两个列表添加相同的值：4, 5, 6
        correctAList.addLast(4);
        buggyAList.addLast(4);

        correctAList.addLast(5);
        buggyAList.addLast(5);

        correctAList.addLast(6);
        buggyAList.addLast(6);

        // 3. 验证第一次 removeLast (预期结果应该是 6)
        assertEquals(correctAList.removeLast(), buggyAList.removeLast());

        // 4. 验证第二次 removeLast (预期结果应该是 5)
        assertEquals(correctAList.removeLast(), buggyAList.removeLast());

        // 5. 验证第三次 removeLast (预期结果应该是 4)
        assertEquals(correctAList.removeLast(), buggyAList.removeLast());
    }
}

