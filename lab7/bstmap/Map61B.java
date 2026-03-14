package bstmap;

import java.util.Set;

/* Your implementation BSTMap should implement this interface. To do so,
 * append "implements Map61B<K,V>" to the end of your "public class..."
 * declaration, though you can use other formal type parameters if you'd like.
 */
public interface Map61B<K, V> extends Iterable<K> {
//任何实现 Map61B 接口的类（比如你的 BSTMap），都必须能够提供一个 java.util.Iterator<K> 类型的迭代器。
    // 这个迭代器将用于遍历 Map 中所有的键（Key），因为泛型参数是 <K>。
    /** Removes all of the mappings from this map. */
    void clear();

    /* Returns true if this map contains a mapping for the specified key. */
    boolean containsKey(K key);

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    V get(K key);

    /* Returns the number of key-value mappings in this map. */
    int size();

    /* Associates the specified value with the specified key in this map. */
    void put(K key, V value);

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    Set<K> keySet();

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    V remove(K key);

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    V remove(K key, V value);

}
