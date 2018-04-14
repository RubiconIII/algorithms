import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import edu.princeton.cs.algs4.*;

public class HW7MinimumSpanningTrees {

    public static void main(String args[]) throws FileNotFoundException {
            int edgesKruskal = 0, edgesPrim = 0;

            Scanner scanner = new Scanner(new File("graphdata.txt"));
            EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(101);

            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineVector = line.split(",");
                int vert1 = Integer.parseInt(lineVector[0]);
                int vert2 = Integer.parseInt(lineVector[1]);
                double weight = Integer.parseInt(lineVector[2]);
                Edge e = new Edge(vert1, vert2, weight);
                edgeWeightedGraph.addEdge(e);
            }

            Stopwatch sw = new Stopwatch();
            double lastTime, thisTime;

            lastTime = sw.elapsedTime();
            KruskalMST kruskalMST = new KruskalMST(edgeWeightedGraph);
            thisTime = sw.elapsedTime();

            for (Edge e : kruskalMST.edges())
                edgesKruskal++;

            printer("Kruskal", edgesKruskal, (thisTime - lastTime), kruskalMST.weight());

            lastTime = sw.elapsedTime();
            PrimMST primMST = new PrimMST(edgeWeightedGraph);
            thisTime = sw.elapsedTime();

            for (Edge e : primMST.edges())
                edgesPrim++;

            printer("Prim", edgesPrim, (thisTime - lastTime), primMST.weight());
        }
        static void printer(String algorithm, int totalEdges, double time, double weight){
            System.out.println("" + algorithm + "'s Algorithm found an MST of " + totalEdges + ", with a total weight of " + weight + " in " + time + " seconds." );
        }
    }
