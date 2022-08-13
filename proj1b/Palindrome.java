public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        int str_length = word.length();
        Deque<Character> d = new ArrayDeque<Character>();
        for (int i =0 ; i <str_length; i++){
            d.addLast(word.charAt(i));
        }
        return d;
    }
    public boolean isPalindrome(String word){
        Deque<Character> d = wordToDeque(word);
        return isP_helper(d);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> d = wordToDeque(word);
        return isP_helper(d, cc);
    }

    private boolean isP_helper(Deque<Character> deq, CharacterComparator c){

        if (deq.size() >=2 ){
            Character tmp1 = deq.removeFirst();
            Character tmp2 = deq.removeLast();
//            boolean tmp = (tmp1 == tmp2);
            boolean tmp = c.equalChars(tmp1,tmp2);
            return isP_helper(deq, c) && tmp;
        }
        else {
            return true;
        }
    }

    private boolean isP_helper(Deque<Character> deq){
        if (deq.size() >=2 ){
            Character tmp1 = Character.toLowerCase(deq.removeFirst());
            Character tmp2 = Character.toLowerCase(deq.removeLast());
            boolean tmp = (tmp1 == tmp2);
            return isP_helper(deq) && tmp;
        }
        else {
            return true;
        }
        }
}
