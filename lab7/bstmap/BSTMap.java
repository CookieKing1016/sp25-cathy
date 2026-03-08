package bstmap;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {
    private class BstNode{
        private K key;
        private V value;
        private BstNode left;
        private BstNode right;
        private int size;

        private BstNode(K key,V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.size = 1;
        }
    }
    private BstNode root;
    public BSTMap(){
        this.root = null;
    }
    @Override
    public void clear(){
        this.root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        if (this.root == null){
            return false;
        }
        return nodecontainsKey(this.root,key);
    }

    private boolean nodecontainsKey(BstNode node,K key){
        if(node == null){
            return false;
        } else if(node.key.compareTo(key) < 0){
            node = node.right;
            return nodecontainsKey(node,key);
        }else if(node.key.compareTo(key) > 0){
            node = node.left;
            return nodecontainsKey(node,key);
        }else {
            return true;
        }
    }


    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        return getHelper(root, key);
    }

    public V getHelper(BstNode node, K key){
        if(node == null){
            return null;
        }else if(node.key.compareTo(key) < 0){
            node = node.right;
            return getHelper(node,key);
        }else if(node.key.compareTo(key) > 0){
            node = node.left;
            return getHelper(node,key);
        }else{
            return node.value;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size(){
        return size(this.root);
    }

    private int size(BstNode node) {
        if (node == null){
            return 0;
        }
        return node.size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        // 调用辅助方法，并更新 root
        root = putHelper(root, key, value);
    };
    private BstNode putHelper(BstNode node, K key, V value){
        if (node == null){
            return new BstNode(key,value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0){
            node.left = putHelper(node.left, key, value);
        } else if(cmp > 0){
            node.right = putHelper(node.right, key, value);
        } else{
            node.value = value;
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }
    public void printInOrder(){
        printInOrderHelper(this.root);
    }
    private void printInOrderHelper(BstNode node){
        if (node == null){
            return;
        }
        printInOrderHelper(node.left);
        System.out.println(node.key);
        printInOrderHelper(node.right);
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet(){
        throw new UnsupportedOperationException("No such Operation");
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */

    public V remove(K key){
        throw new UnsupportedOperationException("No such Operation");
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("No such Operation");
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    // 这是一个私有内部类，用来具体实现迭代器
    private class BSTMapIterator implements Iterator<K> {
        private Queue<K> keys;

        // 构造函数：进行中序遍历，将所有 key 存入队列
        public BSTMapIterator() {
            keys = new LinkedList<>();
            inOrderTraversal(root);
        }

        // 中序遍历的辅助方法
        private void inOrderTraversal(BstNode node) {
            if (node == null) {
                return;
            }
            inOrderTraversal(node.left);
            keys.add(node.key);
            inOrderTraversal(node.right);
        }

        @Override
        public boolean hasNext() {
            return !keys.isEmpty();
        }

        @Override
        public K next() {
            return keys.remove();
        }
    }


}
