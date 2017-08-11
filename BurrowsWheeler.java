/******************************************************************************
  *  Name:    Ryan Gao
  *  NetID:   rmgao
  *  Precept: P03
  *
  *  Partner Name:   
  *  Partner NetID:   
  *  Partner Precept: 
  * 
  *  Description: Transform a message into a form that makes data compression
  *  easier. Methods provided to transform a message and recover a transformed
  *  message back to the original message.
  ******************************************************************************/
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
public class BurrowsWheeler {
    private static final int R = 256; // size of alphabet
    // apply Burrows-Wheeler transform, reading from standard input and writing
    // to standard output
    public static void transform() {
        String input = BinaryStdIn.readString();
        CircularSuffixArray sorted = new CircularSuffixArray(input);
        int first = 0;
        StringBuilder ret = new StringBuilder();
        int len = sorted.length();
        // build String of the last character of each of the sorted circular
        // suffixes
        for (int i = 0; i < len; i++) {
            int index = sorted.index(i);
            if (index == 0) {
                first = i;
                ret.append(input.charAt(len - 1));
            }
            else {
                ret.append(input.charAt(index - 1));
            }
        }
        String retString = ret.toString();
        BinaryStdOut.write(first);
        BinaryStdOut.write(retString);
        BinaryStdOut.close();
    }
    
    // apply Burrows-Wheeler inverse transform, reading from standard input and
    // writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String tString = BinaryStdIn.readString();
        int len = tString.length();
        char[] t = new char[len];
        for (int i = 0; i < len; i++) {
            t[i] = tString.charAt(i);
        }
        // perform key indexed counting, modify it to create both the sorted and
        // the next array
        int[] next = new int[len];
        char[] sorted = new char[len];
        int[] count = new int[R+1];
        for (int i = 0; i < len; i++)
            count[t[i]+1]++;
        for (int r = 0; r < R; r++)
            count[r+1] += count[r];
        for (int i = 0; i < len; i++) {
            int index = count[t[i]]++;
            sorted[index] = t[i];
            next[index] = i;
        }
        // trace the characters starting at first and using the next array
        int a = first;
        int counter = 0;
        while (counter < len) {
            BinaryStdOut.write(sorted[a]);
            a = next[a];
            counter++;
        }
        BinaryStdOut.close();
    }
    
    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            transform();
        }
        else if (args[0].equals("+")) {
            inverseTransform();
        }
    }
}