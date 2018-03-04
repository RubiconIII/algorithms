/***
 * Given the text file “VWMchronological.txt" this program accomplishes some basic data collection.
 * Author: Curtis P. Hohl
 * 1/19/18
 *
 ***/
import edu.princeton.cs.algs4.In; //this was not needed, but included due to project instructions. I used a Scanner instead.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VWMReader {

        public static void main(String args[]) throws FileNotFoundException{
            Scanner VWN = new Scanner(new File("VWMchronological.txt"));
            int count = 0;
            String lastName = "";
            String currentName;
            String date = "";
            boolean gwaltney = false;

            while (VWN.hasNextLine()) {
                String line = VWN.nextLine();
                count++;
                if (VWN.hasNext()) {
                    currentName = VWN.next();
                    if (currentName.equals("GWALTNEY")){
                        gwaltney = true;
                        for (int i = 0; i<3; i++) {
                            date = VWN.next();
                        }
                    }

                    if (currentName.length() > lastName.length()) {
                        lastName = currentName;
                    }

                }
            }
            System.out.println("Number of names: " + count);
            System.out.println("Longest name: " + lastName);
            System.out.println("Existence of Gwaltney: " + gwaltney);
            if (gwaltney == true){
                System.out.println("Day Gwaltney Died: " + date);
            }

        }
}

