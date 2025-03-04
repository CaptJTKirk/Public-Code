/*
 * File Name: DNA_Main.java
 * Author Charles LaCasse 
 */

/*
This program prompts the user to enter a DNA ID and sequence, 
validates whether the sequence contains only the nucleotides A, T, C, and G using the DNARegex class, and checks if it is valid. 
The program then allows the user to select a nucleotide to count within the sequence and calculates the sequence length, 
the total number of guanine (G) and cytosine (C) bases, and the GC content percentage. 
The program prints the DNA ID, the DNA sequence, the count of the selected nucleotide, 
the GC content, and the GC percentage, and finally terminates after displaying the results.
*/

//Import necessary Java items 
import java.util.Scanner; 

//Call the main class 
public class DNA_Main { 
    public static void main(String[] args) {
        //Create the scanner 
        Scanner user_input = new Scanner(System.in);

        //Ask the user for the DNA ID 
        System.out.println("Please enter the DNA ID you wish to use: ");
        String userID = user_input.nextLine();

        //Ask the user for the DNA sequence 
        System.out.println("Please enter the DNA sequence you wish to use: ");
        String userDNA = user_input.nextLine().toUpperCase();

        //Use the DNARegex class to execute 
        DNARegex dna = new DNARegex(userID, userDNA);

        //Check if the user's DNA sequence is valid with a regex 
        if (dna.isDNAValid()) {
            System.out.println("");
            System.out.println("This is a VALID sequence.");
        } else {
            System.out.println("");
            System.out.println("This is NOT a valid DNA sequence");
        }

        //Get the count of a selected base 
        System.out.println("Enter the nucleotide you want to count in the sequence: ");
        char userBase = user_input.nextLine().toUpperCase().charAt(0);

        //Establish a way to count the user's selected base 
        int baseCount = 0;
        if (userBase == 'A' || userBase == 'T' || userBase == 'G' || userBase == 'C') {
            baseCount = dna.baseCount(userBase);
        }

        //Use the base count information from the DNA class for more information of user's DNA
        int seqLength = dna.getSize();
        int gcTotal = dna.baseCount('G') + dna.baseCount('C');
        double gcPercent = ((double) gcTotal / seqLength) *100;

        //Print the information to the console 
        System.out.println("");
        System.out.println("Your DNA ID is: " + dna.getId());
        System.out.println("Your DNA sequence is: " +dna.getSeq());
        System.out.println("");
        System.out.println("The total count for " + userBase + " is: " + baseCount);
        System.out.println("");
        System.out.println("Your GC total content is: " + gcTotal);
        System.out.println("");
        System.out.printf("Your GC %% of your sequence is: %.2f%%\n", gcPercent);
        System.out.println("");
        System.out.println("Ending program...");

        //Close the open scanner
        user_input.close();
    }
} 




