public class HashCollision {
    public static void main(String[] args) {
        String s1 = "BA";
        String s2 = "A`"; // A 后面是反引号 `

        System.out.println("字符串1: \"" + s1 + "\"");
        System.out.println("字符串2: \"" + s2 + "\"");
        System.out.println();

        System.out.println("s1 的哈希码: " + s1.hashCode());
        System.out.println("s2 的哈希码: " + s2.hashCode());
        System.out.println();

        System.out.println("两个字符串是否相等? " + s1.equals(s2));
        System.out.println("它们的哈希码是否相等? " + (s1.hashCode() == s2.hashCode()));

        // 手动验证计算过程
        // hashCode("BA") = 'B' * 31 + 'A' = 66 * 31 + 65 = 2046 + 65 = 2111
        // hashCode("A`") = 'A' * 31 + '`' = 65 * 31 + 96 = 2015 + 96 = 2111
    }
}