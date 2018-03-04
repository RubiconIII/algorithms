import edu.princeton.cs.algs4.*;

import java.util.Arrays;
import java.util.Random;

public class TimeTest {
    // an array to hold the various data set sizes to be tested
    private static final int DATASIZES[] = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000, 1000000};

    public TimeTest() {
    }

    // this method takes one size, generates a random set of ints, times all of the search algorithms and
    //  returns a String of the results
    public String run(int size) {
        Integer data[], masterData[];
        Stopwatch sw = new Stopwatch();
        Random r = new Random();
        double thistime, lasttime, time;
        String resultStr = "Size: " + size;

        // fill master array so all algorithms get the same data
        masterData = new Integer[size];
        data = new Integer[size];
        for (int i = 0; i <  size; i++) {
            masterData[i] = r.nextInt();
        }


        // run the selection sort and add time to results string
        data = Arrays.copyOf(masterData, size);
        lasttime = sw.elapsedTime();
        Selection.sort(data);
        thistime = sw.elapsedTime();
        time = thistime - lasttime;
        resultStr += " Selection: " + time;

        // run the Insertion sort and add time to results string
        data = Arrays.copyOf(masterData, size);
        lasttime = sw.elapsedTime();
        Insertion.sort(data);
        thistime = sw.elapsedTime();
        time = thistime - lasttime;
        resultStr += " Insertion: " + time;


        // run the Merge sort and add time to results string
        data = Arrays.copyOf(masterData, size);
        lasttime = sw.elapsedTime();
        Merge.sort(data);
        thistime = sw.elapsedTime();
        time = thistime - lasttime;
        resultStr += " Merge: " + time;

        // run the quick sort and add time to results string
        data = Arrays.copyOf(masterData, size);
        lasttime = sw.elapsedTime();
        Quick.sort(data);
        thistime = sw.elapsedTime();
        time = thistime - lasttime;
        resultStr += " Quick: " + time;

        return resultStr;
    }

    public static void main(String[] args) {
        TimeTest trial = new TimeTest();          // create a new TimeTest object
        for (int size : DATASIZES) {        // get results for each data set size and print it out
            System.out.println(trial.run(size));
        }

    }

}

