public class LinkedListDeque<T> {

    private class ListNode{
        public T item;
        public ListNode next;
        public ListNode prev;
        public ListNode(T i, ListNode p, ListNode n){
            item = i;
            prev = p;
            next = n;
        }
    }

    private int size;
    private ListNode sentinel;

    //constructor and copy method
    public LinkedListDeque(){
        sentinel = new ListNode(null,null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public LinkedListDeque(LinkedListDeque<T> other){
        sentinel = new ListNode(null,null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        for (int i =0; i < other.size;i++){
           addLast(other.get(i));
        }


    }
    //Deque API
    public void addFirst(T item){
        // no element in the deque
        if(isEmpty()){
            sentinel.next = new ListNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        }
        else {
            sentinel.next.prev = new ListNode(item, sentinel, sentinel.next);
            sentinel.next= sentinel.next.prev;
        }
        size += 1;
    }
    public void addLast(T item){
        // no elment in the deque
        if(isEmpty()){
            sentinel.next = new ListNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        }
        else {
            sentinel.prev.next = new ListNode(item, sentinel.prev, sentinel);
            sentinel.prev= sentinel.prev.next;
        }
        size += 1;
    }
    public boolean isEmpty(){
        return sentinel.next == sentinel;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for (int i =0; i< size; i++){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
    public T removeFirst(){
        if(size ==0){
            return null;
        }
        else {
            size -= 1;
            ListNode temp = sentinel.next;
            sentinel.next = sentinel.next.next;
            if (sentinel.next != sentinel) {
                sentinel = sentinel.next.prev;
            }
            else{
                sentinel.prev = sentinel;
            }
            return temp.item;
        }
    }
    public T removeLast(){
        if(size ==0){
            return null;
        }
        else {
            size -= 1;
            ListNode temp = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            sentinel = sentinel.prev.next;
            return temp.item;
        }
    }

    public T get(int index){
        if (index > size || index < 0){
            return null;
        }
        ListNode temp = sentinel;
        if (index > size/2){
            for (int i = size - index; i>0; i-=1){
                temp = temp.prev;
            }
        }
        else {
            for (int i = index + 1; i>0; i-=1){
                temp = temp.next;
            }
        }
        return temp.item;
    }
//additional method to implement
    public T getRecursive(int index){
        if (index > size || index < 0){
            return null;
        }
        ListNode temp;
        if (index > size/2){
            temp = recurHelper(index, true);
        }
        else{
            temp = recurHelper(size - index, false);
        }
        return temp.item;
    }

    public ListNode recurHelper(int i, boolean reverse){
        if(i == 0){
            return sentinel;
        }
        else{
            if (reverse){
                return recurHelper(i-1, true).prev;
            }
            else {
                return recurHelper(i-1, false).next;
            }
        }
    }
}
