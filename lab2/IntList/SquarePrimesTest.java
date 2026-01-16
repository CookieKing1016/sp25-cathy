package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst1 = IntList.of(14, 15, 16, 17, 18);
        IntList lst2 = IntList.of(3,5,7,14,17);
        IntListExercises.squarePrimes(lst1);
        IntListExercises.squarePrimes(lst2);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst1.toString());
        assertEquals("9 -> 25 -> 49 -> 14 -> 289", lst2.toString());
    }
}
