/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomWord {
    public static void main(String args[]){
        double i = 0;
        String champ="";
        //String champ = StdIn.readString();
        while (!StdIn.isEmpty()){
            i++;
            String test = StdIn.readString();
            if (StdRandom.bernoulli(1/ i)){
                champ = test;
            }
        }
        StdOut.println(champ);
    }
}
