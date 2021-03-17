package MyList;

import java.util.*;

// >> TODO MyArrayList只是为了展示如何用底层数据结构去实现一个更高层、更好用的数据结构...下面有些功能的实现并不完善。
// >> TODO 而java提供了很多完善的接口实现类,如ArrayList、LinkedList
public class MyArrayList<T> implements List<T> {
    private Object[] elements;
    //curr为计数器，表明当前元素加到什么地方了。
    private int curr;

    public MyArrayList(){
        elements = new Object[16];
        curr = 0;
    }

    @Override
    //size()：链表中有多少个元素？
    public int size() { return curr; }

    @Override
    public boolean isEmpty() { return curr == 0; }

    @Override
    //contains()：给定一个元素，判断该元素在链表中是否存在？
    public boolean contains(Object o) {
        for(Object ele : elements){
            if(Objects.equals(ele,o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        curr = 0;
    }

    @Override
    public T get(int index) {
        if(index > curr | index < 0){
            throw new IndexOutOfBoundsException("输入的索引 " + index + " 超出了边界 " + curr);
        }else{
            return (T)elements[index];
        }
    }

    @Override
    public boolean add(T o) {
        if(curr == (elements.length - 1)){
            //这里应该有拷贝操作的...但是无所谓了
            elements = new Object[elements.length * 2];
        }
        elements[curr] = o;
        curr++;
        return true;
    }

    // >> TODO 实现Iterator接口，使该类支持for...each循环。
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int pointer = 0;

            @Override
            public boolean hasNext() { return pointer < size(); }

            @Override
            public T next() { return (T)elements[pointer++]; }
        };
    }

    // >> TODO 对于接口的一些方法，如果没有合适的方式去实现它，则可以抛一个UnsupportedOperationException异常。
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
    public T remove(int index) { throw new UnsupportedOperationException(); }

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
