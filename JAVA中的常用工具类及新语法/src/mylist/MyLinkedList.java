package mylist;

import java.util.*;

public class MyLinkedList implements List {
    static class ListNode{
        ListNode prev;
        ListNode next;
        Object value;

        public ListNode(ListNode prev, ListNode next, Object value){
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }

    private ListNode start;
    private ListNode tail;
    private int size;

    public MyLinkedList(){
        this.start = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    //size()：链表中有多少个元素？
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    //contains()：给定一个元素，判断该元素在链表中是否存在？
    public boolean contains(Object o) {
        ListNode curr = start;
        while(curr != null){
            if(Objects.equals(curr.value, o)){
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    @Override
    public void clear() {
        start = null;
        tail = null;
        size = 0;
    }

    @Override
    public Object get(int index) {
        if(index > size | index < 0){
            throw new IndexOutOfBoundsException("输入的索引 " + index + " 超出了边界 " + size);
        }
        ListNode curr = start;
        for(int i = 0; i < index; i++){
            curr = curr.next;
        }
        return curr.value;
    }

    @Override
    public boolean add(Object o) {
        ListNode newNode = new ListNode(tail,null,o);
        //要考虑链表本身为空的情况
        if(start == null) { start = newNode;}
        if(tail != null){tail.next = newNode;}
        tail = newNode;
        size++;
        return true;
    }

    // >> TODO 对于一些接口的方法，如果没有合适的方式去实现，可以抛UnsupportedOperationException异常。
    @Override
    public Iterator iterator() { throw new UnsupportedOperationException(); }

    @Override
    public Object[] toArray() { throw new UnsupportedOperationException(); }

    @Override
    public boolean remove(Object o) { throw new UnsupportedOperationException(); }

    @Override
    public boolean addAll(Collection c) { throw new UnsupportedOperationException(); }

    @Override
    public boolean addAll(int index, Collection c) { throw new UnsupportedOperationException(); }

    @Override
    public Object set(int index, Object element) { throw new UnsupportedOperationException(); }

    @Override
    public Object remove(int index) { throw new UnsupportedOperationException(); }

    @Override
    public int indexOf(Object o) { throw new UnsupportedOperationException(); }

    @Override
    public int lastIndexOf(Object o) { throw new UnsupportedOperationException(); }

    @Override
    public Object[] toArray(Object[] a) { throw new UnsupportedOperationException(); }

    @Override
    public void add(int index, Object element) { throw new UnsupportedOperationException(); }

    @Override
    public boolean containsAll(Collection c) { throw new UnsupportedOperationException(); }

    @Override
    public boolean removeAll(Collection c) { throw new UnsupportedOperationException(); }

    @Override
    public boolean retainAll(Collection c) { throw new UnsupportedOperationException(); }

    @Override
    public ListIterator listIterator() { throw new UnsupportedOperationException(); }

    @Override
    public ListIterator listIterator(int index) { throw new UnsupportedOperationException(); }

    @Override
    public List subList(int fromIndex, int toIndex) { throw new UnsupportedOperationException(); }
}
