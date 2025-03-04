/*
 * File Name: DNARegex.java
 * Author Charles LaCasse 
 */

/*
The program represents a DNA sequence and provides methods to validate, retrieve, and analyze it. 
It stores a DNA ID and sequence, checks if the sequence is valid using a regular expression 
(ensuring it contains only the characters A, T, G, and C), and allows access to the ID and sequence through getter methods. 
It also provides methods to determine the length of the sequence and to count occurrences of a specific nucleotide. 
This class is designed to be used by the DNA_Main program.
*/
 
 //Import necessary Java features
 import java.util.regex;

//Create the public class for DNA to be used by program DNA_Main.java
public class DNARegex { 
    //Create a private class for use only in the DNA specified class 
    private String id;
    private String seq; 

    //Initialize the variables that will be used 
    public DNARegex(String dna_id, String dna_seq) {
        id = dna_id;
        seq = dna_seq;
    }

    //Determine if the DNA is valid with a regex 
    public boolean isDNAValid() {
        return seq.matches("[ATGC]*");
     
     }

    //Get the DNA id 
    public String getId() {
        return id;
    }

    //Get the DNA sequence 
    public String getSeq() {
        return seq;
    }

    //Determine the length of the user's DNA sequence 
    public int getSize() {
        return seq.length();
    }

    //Count the bases in the user's DNA sequence 
    public int baseCount(char base) {
        //Set count to 0 to initialize 
        int count = 0;
        //Use a for loop to count each base 
        for (int i=0; i<seq.length(); i++) {
            if (seq.charAt(i) == base) {
                count++;
            }
        }
        //Return the count of the bases 
        return count;
    }
}
