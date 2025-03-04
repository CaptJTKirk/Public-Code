/*
 * File Name: NewZincFinger.java
 * Author: Charles LaCasse
 */

/*Reads a FASTA-formatted file containing protein sequences, 
identifies potential zinc finger motifs using a regular expression, 
and outputs the matches along with their positions and visual highlights.
*/

 //Import necessary java featujres 
 import java.io.File; 
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 import java.util.regex.*;
 import java.util.ArrayList; 

 //Create the class file that builds on the previous ZincFinger class
 public class NewZincFinger {
    //Establish what will be used for the regex finder 
    private static String ZincRegex = "C..C.{17}C..C";

    public static void main(String[] args) {
        //Ask the user for the file they wish to use
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter the file you wish to use:");
        String file = userInput.nextLine();   

        //Initalize array to store the protein sequences 
        ArrayList<Protein> proteins = new ArrayList<>(); 

        //Read the user's file
        try (Scanner fileScanner = new Scanner(new File(file))) {
            String ZincTitle = null;
            StringBuilder seqBuilder = new StringBuilder();
            
            //Create a while loop to read through the file 
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                //Cheack for the header line in the file which will start with ">"
                if (line.startsWith(">")) {
                    if (ZincTitle != null) {
                        proteins.add(new Protein(ZincTitle, seqBuilder.toString()));
                    }
                    //Set up for a new header line 
                    ZincTitle = line;
                    //Reset the sequence builder for a new protein sequence
                    seqBuilder.setLength(0);
                } else {
                    //Appened the line to to the sequence builder 
                    seqBuilder.append(line);
                }
            }
            if (ZincTitle !=null) {
                proteins.add(new Protein(ZincTitle, seqBuilder.toString()));
            }

        } 
        //Create file exception error if the selected file is not found 
        catch (FileNotFoundException e) {
            System.out.println("Error! File not found!");
            return;
        } 

        //Use the regex pattern to check for matches in the file 
        Pattern ZincPattern = Pattern.compile(ZincRegex);
        //Use a for loop to start finding matches in the file 
        for (Protein protein : proteins) {
            Matcher matcher = ZincPattern.matcher(protein.getSequence());
            //Create array for positions of matches 
            ArrayList<Integer> positions = new ArrayList<>();
            //Create array for the sequence that is matched 
            ArrayList<String> matchedSeq = new ArrayList<>();

            //Find matches in the sequences from the file
            while (matcher.find()) {
                positions.add(matcher.start());
                matchedSeq.add(matcher.group());
            }
            //Look for matches and if found print the title and matching sequence 
            if (!positions.isEmpty()) {
                System.out.println(protein.getZincTitle());
                for (String matchSeq : matchedSeq) {
                    System.out.println("Contains zinc finger at site(s): " + matchSeq);
                }
                //Print the starting location(s) that the matches occur 
                System.out.print("at locations: ");
                for (int i = 0; i < positions.size(); i++) {
                    System.out.print(positions.get(i));
                    if (i < positions.size() -1) {
                        System.out.print(" "); 
                    }
                } 
                System.out.println("");

                String sequence = protein.getSequence();
                //Use a line length of 50 bases 
                int lineLength = 50;
                //Determine length of the sequences discovered  
                int sequenceLength = sequence.length();
                
                //Use an array to house the information on which spaces will NOT have a *
                char[] highlight = new char[sequence.length()];
                for (int i = 0; i < highlight.length; i++) {
                    highlight[i] = ' ';
                }
                //Use a for loop to print the * to the match in the sequence 
                for (int i=0; i < matchedSeq.size(); i++) {
                    int startPos = positions.get(i);
                    for (int j = 0; j < matchedSeq.get(i).length(); j++) {
                        highlight[startPos + j] = '*';
                    }
                }
                //Print the sequence with the correct highlight information (either ' ' or *)
                for (int i = 0; i < sequenceLength; i += lineLength) {
                    String sequenceChunk = sequence.substring(i, Math.min(sequenceLength, i + lineLength));
                    String highlightChunk = new String(highlight).substring(i, Math.min(sequenceLength, i + lineLength));

                    System.out.println(sequenceChunk);
                    System.out.println(highlightChunk);
                }
            }
        }
        
        //Close the open scanner
        userInput.close(); 
    }
     //Create classes for variables used in the main arguement 
    static class Protein {
        private String ZincTitle;
        private String sequence;

        public Protein(String ZincTitle, String sequence) {
            this.ZincTitle = ZincTitle;
            this.sequence = sequence;
        }

        public String getZincTitle() {
            return ZincTitle;
        }

        public String getSequence() {
            return sequence;
        }
    }
}
