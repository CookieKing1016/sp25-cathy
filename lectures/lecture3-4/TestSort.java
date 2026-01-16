import org.junit.Test;  // 导入JUnit注解

public class TestSort {

    @Test  // 添加@Test注解
    public void testSort() {  // 去掉static
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};

        Sort.sort(input);

        org.junit.Assert.assertArrayEquals(expected, input);
    }

    @Test  // 添加@Test注解
    public void testSwap() {  // 去掉static
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "have", "i", "egg"};
        int a = 0;
        int b = 2;

        Sort.swap(input, a, b);

        org.junit.Assert.assertArrayEquals(expected, input);
    }

}