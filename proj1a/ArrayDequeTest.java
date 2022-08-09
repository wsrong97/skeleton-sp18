/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {


        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);

    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {


        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

//        lld1.addFirst(10);
//        lld1.printDeque();
//        lld1.addFirst(10);
//        lld1.printDeque();
//        lld1.addLast(3);
//        lld1.printDeque();
//        lld1.addLast(7);
//        lld1.printDeque();

//        lld1.addLast(0);
//        lld1.printDeque();
//        lld1.addFirst(1);
//        lld1.printDeque();
//        lld1.isEmpty();
////        lld1.printDeque();
//        lld1.size();
////        lld1.printDeque();
//        lld1.removeLast();
//        lld1.printDeque();
//        lld1.removeLast();
//        lld1.printDeque();
//        lld1.addLast(6);
//        lld1.printDeque();
//        lld1.size();
//        lld1.removeFirst();
//        lld1.printDeque();


        lld1.addFirst(0);
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.get(0);
        lld1.addFirst(4);
        lld1.addLast(5);
        lld1.addFirst(6);
        lld1.get(5);
        lld1.addLast(8);
        lld1.addFirst(9);
        lld1.removeFirst();
        System.out.print(lld1.get(2) + " ");

        passed = checkSize(7, lld1.size()) && passed;
        lld1.removeLast();
        passed = checkSize(6, lld1.size()) && passed;
        lld1.removeFirst();
        lld1.printDeque();
        passed = checkSize(5, lld1.size()) && passed;
        printTestStatus(passed);
        ArrayDeque lld2 = new ArrayDeque<>(lld1);
        lld2.printDeque();
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
    }
}