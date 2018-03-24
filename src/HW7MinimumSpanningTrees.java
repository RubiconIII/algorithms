import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import edu.princeton.cs.algs4.EdgeWeightedGraph;


public class HW7MinimumSpanningTrees {

    public static void main(String args[]) throws FileNotFoundException {
        Scanner text = new Scanner(new File("graphdata.txt"));

       // EdgeWeightedGraph graph = new EdgeWeightedGraph();


        while (text.hasNextInt()) {
            System.out.println(text.nextInt());
        }
    }
}
