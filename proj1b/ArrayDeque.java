
public class ArrayDeque<T> implements Deque<T>{
    private static final int RFACTOR = 2;
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    //constructor
    public ArrayDeque(){
        items = (T []) new Object[8];
        size = 0;   //invariant
        nextFirst = 0;  //invariant
        nextLast = 0;
    }

    public ArrayDeque(ArrayDeque other){

        int size = other.items.length;
        //this or not?
        this.items = (T []) new Object[size];
        System.arraycopy(other.items, 0, this.items, 0, size);
        this.size = other.size;
        this.nextLast = other.nextLast;
        this.nextFirst = other.nextFirst;
    }

    //helper functions
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
        //two resize cases: when low memory usage; when full.
        if(capacity /size ==2 && capacity%size==0){
            //the other case downsize case capacity/size = 1/2* efficiency > 2
            if(nextFirst== size-1 &&nextLast ==0){
                System.arraycopy(items, 0, a, 0, size);
            }
            else{
                System.arraycopy(items, nextFirst+1,a,0, items.length-nextFirst-1);
                System.arraycopy(items,0, a,items.length-nextFirst-1, nextLast);
            }
        }
        else{
            //downsize
            if(nextFirst == size-1){//boundary
                System.arraycopy(items, 0, a, 0, size);
            }
            else if(nextLast == 0){//boundary
                System.arraycopy(items, 0, a, nextFirst+1, size);
            }
            else if(nextFirst< nextLast && (nextLast-nextFirst-1== size)){
                System.arraycopy(items, nextFirst+1, a,0, size);
            }
            else {
                System.arraycopy(items, nextFirst+1,a,0, items.length-nextFirst-1);
                System.arraycopy(items,0, a,items.length-nextFirst-1, nextLast);
            }
        }
        items = a;
        nextLast = size;
        nextFirst = capacity -1;
    }

    //Deque API
    @Override
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

    @Override
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

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        for (int i =0; i< size; i++){

            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        checkusage();
        if (isEmpty()){
            return null;
        }
        //non empty
        int index = (nextFirst + 1)% items.length;
        T temp = items[index];
        size -=1;
        nextFirst = index ;
        if (size ==0){ //cases when no element after removal. reset
            nextLast = 0;
            nextFirst = 0;
        }
        return temp;

    }

    @Override
    public T removeLast(){
        checkusage();
        if (isEmpty()){
            return null;
        }
        // non empty
        int index = (nextLast - 1 + items.length )% items.length;
        T temp = items[index];
//        items[index] = null;
        //invariant
        size -=1;
        nextLast = index;
        if (size ==0){//cases when no element after removal. reset
            nextFirst = 0;
            nextLast = 0;
        }
        return temp;
    }

    @Override
    public T get(int index) {
        if (index >= size ||index <0) {// out of range
            return null;
        }
        if (isEmpty() && index >=0){
            return null;
        }
        index = (index + nextFirst+1)% items.length;
        return items[index];
        }
}


