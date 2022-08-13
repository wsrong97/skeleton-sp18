import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class.

    @Test
    public void TestPalindrome(){
        assertTrue(palindrome.isPalindrome("afa"));
        assertTrue(palindrome.isPalindrome("Affa"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        In in = new In("../library-sp18/data/panlindrome.txt");
        while (!in.isEmpty()){
            String s = in.readString();
            assertTrue(palindrome.isPalindrome(s));
        }
        assertFalse(palindrome.isPalindrome("sfa"));
        assertFalse(palindrome.isPalindrome("asfas"));
        //
        CharacterComparator obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake",obo));
        assertFalse(palindrome.isPalindrome("acB",obo));

        CharacterComparator ob5 = new OffByN(5);
        assertTrue(palindrome.isPalindrome("flaqa",ob5));
        CharacterComparator ob31 = new OffByN(31);
        assertTrue(palindrome.isPalindrome("acB",ob31));
    }


    }

