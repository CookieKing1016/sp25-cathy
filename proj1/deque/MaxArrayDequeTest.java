package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }
    }

    private static class ReverseIntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return b.compareTo(a);
        }
    }

    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return Integer.compare(a.length(), b.length());
        }
    }

    @Test
    public void testMaxWithDefaultComparator() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntComparator());
        mad.addLast(3);
        mad.addLast(7);
        mad.addLast(2);
        mad.addLast(9);
        mad.addLast(5);

        assertEquals(Integer.valueOf(9), mad.max());
    }

    @Test
    public void testMaxWithGivenComparator() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntComparator());
        mad.addLast(3);
        mad.addLast(7);
        mad.addLast(2);
        mad.addLast(9);
        mad.addLast(5);

        assertEquals(Integer.valueOf(2), mad.max(new ReverseIntComparator()));
    }

    @Test
    public void testMaxEmptyDeque() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntComparator());

        assertNull(mad.max());
        assertNull(mad.max(new ReverseIntComparator()));
    }

    @Test
    public void testMaxStringByLength() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new StringLengthComparator());
        mad.addLast("a");
        mad.addLast("abcd");
        mad.addLast("abc");
        mad.addLast("abcdef");

        assertEquals("abcdef", mad.max());
    }

    @Test
    public void testMaxSingleItem() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntComparator());
        mad.addLast(42);

        assertEquals(Integer.valueOf(42), mad.max());
    }
}
