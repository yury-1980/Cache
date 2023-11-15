package ru.clevertec.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    Node head;
    Node tail;
    Map<Long, Node> map = null;
    int capacity = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    public long get(long key) {

        if(map.get(key) == null){
            return -1;
        }

        Node item = map.get(key);
        //move to tail
        removeNode(item);
        addToTail(item);

        return item.value;
    }

    public void put(Long key, int value) {

        if(map.containsKey(key)){
            Node item = map.get(key);
            item.value = value;

            //move to tail
            removeNode(item);
            addToTail(item);
        }else{
            if(map.size() >= capacity){
                //delete head
                map.remove(head.key);
                removeNode(head);
            }

            //add to tail
            Node node = new Node(key, value);
            addToTail(node);
            map.put(key, node);
        }
    }

    private void removeNode(Node node){

        if(node.prev != null){
            node.prev.next = node.next;
        }else{
            head = node.next;
        }

        if(node.next != null){
            node.next.prev = node.prev;
        }else{
            tail = node.prev;
        }
    }

    private void addToTail(Node node){

        if(tail != null){
            tail.next = node;
        }

        node.prev = tail;
        node.next = null;
        tail = node;

        if(head == null){
            head = tail;
        }
    }
}
