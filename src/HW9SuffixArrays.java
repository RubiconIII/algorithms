    import edu.princeton.cs.algs4.In;
    import edu.princeton.cs.algs4.LongestRepeatedSubstring;
    import edu.princeton.cs.algs4.Stopwatch;
    import java.io.FileNotFoundException;


    public class HW9SuffixArrays {
        private static final String[] TEXTS = new String[]{"Bible_KJV.txt", "HoundOfTheBaskervilles.txt", "TheThreeMusketeers.txt", "TwentyThousandLeaguesUnderTheSea.txt"};

        public static void main(String args[]) throws FileNotFoundException {
            for (String text : TEXTS) {        // get results for each file
                run(text);
            }
        }

        public static void run(String file) throws FileNotFoundException {
            Stopwatch sw = new Stopwatch();
            double lastTime, thisTime;

            In text = new In(file);
            In copyText = new In(file);
            int charTotal = text.readAll().length();

            lastTime = sw.elapsedTime();
            String lrs =  LongestRepeatedSubstring.lrs(copyText.readAll());
            thisTime = sw.elapsedTime();

            double timeTaken = thisTime - lastTime;

            int lrsLength = lrs.length();

            printer(file, charTotal, lrs, lrsLength, timeTaken);
        }

        static void printer(String file, int charTotal, String lrs,  int lrsLength, double timeTaken){
            System.out.println("File: " + file + ", Character Total: " + charTotal + ", LRS Length: " + lrsLength + ", Elapsed Time: " + timeTaken);
            System.out.println("Actual LRS: " + lrs);
            System.out.println();
        }


    }
