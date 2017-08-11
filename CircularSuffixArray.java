/******************************************************************************
  *  Name:    Ryan Gao
  *  NetID:   rmgao
  *  Precept: P03
  *
  *  Partner Name:   
  *  Partner NetID:   
  *  Partner Precept: 
  * 
  *  Description: Creates a sorted array of the N circular suffixes of an input
  *  String of length N.
  ******************************************************************************/
import java.util.Arrays; 
// print out index where original ended up and the last characters
public class CircularSuffixArray {
    private int len; // length of String s 
    private CircularSuffix[] suffix; // array of sorted CircularSuffixes, which
    // represent circular suffixes of String s
    
    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new 
            java.lang.NullPointerException("argument to constructor is null");
        len = s.length();
        suffix = new CircularSuffix[len];
        // create all CircularSuffix objects then sort them
        for (int i = 0; i < len; i++) {
            suffix[i] = new CircularSuffix(s, i);
        }
        Arrays.sort(suffix);
    }
    
    private class CircularSuffix implements Comparable<CircularSuffix> {
        private String s; // reference to the String being used
        private int pointer; // index of the first character of the 
                             // circular suffix
        public CircularSuffix(String s, int pointer) {
            this.s = s;
            this.pointer = pointer;
        }
        // implement a compareTo so that CircularSuffixes can be sorted using
        // the built-in sort
        public int compareTo(CircularSuffix other) {
            int pointer1 = this.pointer;
            char char1 = this.s.charAt(pointer1);
            int length1 = this.s.length();
            int pointer2 = other.pointer;
            char char2 = other.s.charAt(pointer2);
            int length2 = other.s.length();
            int counter = 0;
            // cycle through each String, comparing each character until there 
            // is a difference, starting from pointer indexes
            while (char1 == char2) {
                pointer1++;
                pointer2++;
                counter++;
                if (counter == length1) return 0;
                if (counter == length2) return 0;
                if (pointer1 == length1) pointer1 = 0;
                if (pointer2 == length2) pointer2 = 0;
                char1 = this.s.charAt(pointer1);
                char2 = other.s.charAt(pointer2);
            }
            if (char1 > char2) return 1;
            else return -1;     
        }
    }
    
    // length of s         
    public int length() {
        return len;
    }
    
    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= len)
            throw new IndexOutOfBoundsException("index is out of bounds");
        return suffix[i].pointer;
    }
    
    // unit testing (not graded)
    public static void main(String[] args) {
        CircularSuffixArray tester = new CircularSuffixArray("weekendmnnkk");
        System.out.println(tester.length());
        System.out.println(tester.index(5));
    }
}