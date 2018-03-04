import edu.princeton.cs.algs4.*;
import java.util.Arrays;
import java.util.Random;

public class HW5 {
    // an array to hold the various data set sizes to be tested
    private static final int DATASIZES[] = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000, 1000000};

    public HW5() {
    }

    // this method takes one size, generates a random set of ints, times all of the search algorithms and
    //  returns a String of the results
    public String evaluate(int size) {
        Integer data[], masterData[];
        Stopwatch sw = new Stopwatch();
        Random r = new Random();
        double thistime, lasttime, addTime;
        String resultStr = "Size: " + size;

        // fill master array so all algorithms get the same data
        masterData = new Integer[size];
        data = new Integer[size];
        for (int i = 0; i < size; i++) {
            masterData[i] = r.nextInt();
        }


        // evaluate the SequentialSearchST and add time to results string
        data = Arrays.copyOf(masterData, size);
        SequentialSearchST<Integer, Integer> sst = new SequentialSearchST<>();
        for (int i = 0; i < data.length; i++) {
            sst.put(i, data[i]);
        }

        double swInsertionTime = 0, swSearchTime = 0;
        for (int i = 0; i < 1000; i++) {
            lasttime = sw.elapsedTime();
            sst.put(r.nextInt(), r.nextInt()); //insertion command
            thistime = sw.elapsedTime();
            swInsertionTime=+ thistime - lasttime;
            lasttime = sw.elapsedTime();
            sst.contains(r.nextInt()); //search command
            thistime = sw.elapsedTime();
            swSearchTime =+ thistime - lasttime;
        }
        resultStr += " Total Sequential Search ST add time: " + swInsertionTime;
        resultStr += " Total Sequential Search ST search time: " + swSearchTime;

        // evaluate the RedBlackBST and add time to results string
        data = Arrays.copyOf(masterData, size);
        double rbstInsertionTime = 0, rbstSearchTime = 0;
        RedBlackBST<Integer, Integer> rbst = new RedBlackBST<>();
        for (int i = 0; i < data.length; i++) {
            rbst.put(i, data[i]);
        }

        for(int i = 0; i < 1000; i++) {
            lasttime = sw.elapsedTime();
            rbst.put(r.nextInt(), r.nextInt()); //insertion command
            thistime = sw.elapsedTime();
            rbstInsertionTime =+ thistime - lasttime;
            lasttime = sw.elapsedTime();
            rbst.contains(r.nextInt()); //search command
            thistime = sw.elapsedTime();
            rbstSearchTime =+ thistime - lasttime;
        }
        resultStr += " Red Black BST add time: " + rbstInsertionTime;
        resultStr += " Red Black BST search time: " + rbstSearchTime;

        return resultStr;
    }

    public static void main(String[] args) {
        HW5 trial = new HW5();          // create a new TimeTest object
        for (int size : DATASIZES) {        // get results for each data set size and print it out
            System.out.println(trial.evaluate(size));
        }

    }

}

