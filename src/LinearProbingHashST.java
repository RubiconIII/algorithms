import edu.princeton.cs.algs4.Queue;

import java.util.Random;

/**
 * LinearProbingHashST
 *
 * @author Curtis P. Hohl
 * @version 1.0
 * Created March 09, 2018
 */

public class LinearProbingHashST<Key, Value> {
    private static final int DATASIZES[] = {10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000, 200000, 500000, 1000000};
    private int N; // number of key-value pairs in the table
    private int M = 16; // size of linear-probing table
    private Key[] keys; // the keys
    private Value[] vals; // the values

    public LinearProbingHashST() {
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public LinearProbingHashST(int cap) {
        M = cap;
        keys = (Key[]) new Object[M];
        vals = (Value[]) new Object[M];
    }

    public static void main(String[] args) {
        for (int dataSize : DATASIZES) {    // get results for each data set size and print it out
            System.out.println(evaluate(dataSize));
        }
    }

    public static String evaluate(int dataSize) {
        LinearProbingHashST<Integer, Integer> hashTable = new LinearProbingHashST<>();
        Random r = new Random();
        int start = 0, end = 0;
        double avgDisplacement = 0, displacementTotal = 0;

        for (int i = 0; i < dataSize; i++) {
            int value = r.nextInt(100000);
            int key = r.nextInt(100000);
            start = hashTable.hash(key);
            end = hashTable.put(key, value);
            int displacement;
                if (start == end){
                    displacement = 0;
                }
                else if (start < end) {
                    displacement = end - start;
                }
                else {
                    displacement = Math.abs((start - end) - dataSize);
                }
            displacementTotal += displacement;
        }
        avgDisplacement = displacementTotal / dataSize;
        return ("Data size: " + dataSize + "\nAverage Displacement: " + avgDisplacement + "\n");
    }


    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<Key, Value>(cap);
        for (int i = 0; i < M; i++)
            if (keys[i] != null)
                t.put(keys[i], vals[i]);
        keys = t.keys;
        vals = t.vals;
        M = t.M;
    }

    public int put(Key key, Value val) {
        if (N >= M / 2) resize(2 * M); // double M (see text)
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return i;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
        return i;
    }

    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % M;
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M / 8) resize(M / 2);
    }

    public boolean contains(Key key) {
        if (key == null) {
            try {
                throw new IllegalAccessException("The key cannot be null");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return get(key) != null;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }
}