
public class ArrayDeque<T> {
    private static final int RFACTOR = 2;
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque(){
        items = (T []) new Object[8];
        size = 0;   //invariant
        nextFirst = 0;  //invariant
        nextLast = 0;
    }

    public ArrayDeque(ArrayDeque<T> other){
//        ArrayDeque this = new ArrayDeque();
        int size = other.items.length;
        //this or not?
        this.items = (T []) new Object[size];
        System.arraycopy(other.items, 0, this.items, 0, size);
        this.size = other.size;
        this.nextLast = other.nextLast;
        this.nextFirst = other.nextFirst;
    }

    private void checkusage(){
        if(items.length >= 16){
            float efficiency = (float)size/ (float)items.length;
            if (efficiency < 0.25){
                resize(items.length/2);
            }
        }
    }
    private void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        //when first element is in the beginning?
        if (nextFirst == size -1){
        System.arraycopy(items, 0, a, 0, size);

        }
        else {//when not?
            System.arraycopy(items, nextFirst+1,a,0, size-nextFirst-1);
            System.arraycopy(items,0, a,size-nextFirst-1, nextLast);
        }
        items = a;
        nextLast = size;
        nextFirst = capacity -1;

    }
    public void addFirst(T item){
        if(size == items.length) {
            resize(size* RFACTOR);
        }
        // now not full
        items[nextFirst] = item;
        //invariant
        if (size ==0){
            nextLast =1; // nextLast only changes in this scenario under addFirst
        }
        size += 1;
        nextFirst = (items.length + nextFirst - 1) % items.length;

    }
    public void addLast(T item){
        if(size == items.length){
            resize(size* RFACTOR);
        }
        //no not full
        items[nextLast] = item;
        //invariant
        if (size ==0){
            nextFirst = items.length -1; // nextFirst only changes in this scenario under addLast
        }
        size += 1;
        nextLast = (nextLast + 1) % items.length;
    }
    public boolean isEmpty(){
        if (size ==0){
            return true;
        }
        else {
            return false;
        }
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
        checkusage();
        if (isEmpty()){
            return null;
        }
        //non empty
        int index = (nextFirst + 1)% items.length;
        T temp = items[index];
//        items[index] = null; unnecessary for maintaining invariant
        //invariant
        size -=1;
        nextFirst = index ;
        if (size ==0){
            nextLast = 0;
        }
        return temp;

    }
    public T removeLast(){
        checkusage();
        if (isEmpty()){
            return null;
        }
        // non empty
        int index = (nextLast - 1 + items.length)% items.length;
        T temp = items[index];
//        items[index] = null;
        //invariant
        size -=1;
        nextLast = index;
        if (size ==0){
            nextFirst = 0;
        }
        return temp;
    }
    public T get(int index) {
        if (index >= size) {// out of range
            return null;
        }
        //translate this index to the real implementation
        if (index + nextFirst > size) {
            index = (index + nextFirst+1)% items.length;
            return items[index];
        } else {
            //<size
            return items[index + nextFirst +1];
        }
    }
//        ////
//        //nextLast <= nextFirst
//        if(nextLast < nextFirst){
//            if (index >=0){
//                index = (index + nextFirst +1)% items.length;
//                return items[index];
//            }
//            else{
//                return null;
//            }
//        }
//        else{ //(nextLast == nextFirst)
//            return items[index];
//        }

}


