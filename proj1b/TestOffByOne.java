import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne(){

        assertTrue(offByOne.equalChars('x','y'));
        assertTrue(offByOne.equalChars('x','w'));
        assertTrue(offByOne.equalChars('%','&'));
        assertTrue(offByOne.equalChars('&','%'));
        assertFalse(offByOne.equalChars('a','B'));
        assertFalse(offByOne.equalChars('A','b'));

    }

//    offByOne.equalChars('','');
//    offByOne.
//    Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/
}
