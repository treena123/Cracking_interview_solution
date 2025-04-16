package third.LRU;

import java.util.Map;

/**
 * @author trinapal
 */

class Node{
    String key;
    String value;
    Node prev;
    Node next;
}
public class LRUCache {
    int capacity;
    Map<String, Node> map;
    Node head, tail;

    public static void main(String[] args) {
        LRUCache cache = new LRUCache();
        /*cache.put("A", "Apple");
        cache.put("B", "Banana");
        cache.get("A"); // A becomes most recently used
        cache.put("C", "Cat"); */
    }
}
