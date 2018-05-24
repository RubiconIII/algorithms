/*
 * CSC312 SP16 – Arbitrage starting code CF Jones CBU
 * this program reads some currency exchange information from a
 * comma-separated file and checks to see if there is an opportunity
 * to make some money via arbitrage
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import edu.princeton.cs.algs4.*;

import java.io.IOException;
import java.lang.Math;
/**
 *
 * @author crjones
 */
public class HW8Arbitrage {

    private static int currToIdx(String S) {
        if (S.equals("AUD"))
            return 0;
        else if (S.equals("CAD"))
            return 1;
        else if (S.equals("CHF"))
            return 2;
        else if (S.equals("CNY"))
            return 3;
        else if (S.equals("GBP"))
            return 4;
        else if (S.equals("ILS"))
            return 5;
        else if (S.equals("INR"))
            return 6;
        else if (S.equals("JPY"))
            return 7;
        else if (S.equals("USD"))
            return 8;
        else
            return -1;
    }

    private static void readRates(EdgeWeightedDigraph G, String fname) {
        int curr1 = 0, curr2 = 0;
        double currWt = 0;
        int i, j;
        // read file line by line
        File Fin = new File(fname);
        In in = new In(Fin);
        String[] edgeStrings = in.readAllLines();
        for (String s : edgeStrings) {
            String[] strPieces = s.split(",");
            curr1 = currToIdx(strPieces[0]);
            curr2 = currToIdx(strPieces[1]);
            currWt = Double.parseDouble(strPieces[2]);
            double EdgeWeight = -java.lang.Math.log(currWt);
            DirectedEdge DE = new DirectedEdge(curr1, curr2, EdgeWeight);
            G.addEdge(DE);
        }
    }
    public static void main(String[] args) {
        String[] name = new String[9];
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(9);
        readRates(G, "ExchangeRates.csv");
       // System.out.print(G.toString());
        BellmanFordSP spt = new BellmanFordSP(G, 8);
        if (spt.hasNegativeCycle()) {
            double opportunity = 1;
            double investment = 1000000;
            System.out.println("This graph has a negative cycle - arbitrage opportunity!");

            for (DirectedEdge DE : spt.negativeCycle()){
                System.out.println(DE);
                opportunity += -DE.weight();

            }
           System.out.println(opportunity * investment);
        }
        else{
            System.out.println("This graph has no negative cycle - no arbitrage opportunity.");
        }
    }
}