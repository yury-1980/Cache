package ru.clevertec.cache;

public class Node {

    long key;
    long value;
    Node prev;
    Node next;

    public Node(long key, long value){
        this.key   = key;
        this.value = value;
    }
}
