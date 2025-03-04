/*
 * File Name: DNARead.java
 * Author: Charles LaCasse
 */

/*Utilizes FileRead class to read a DNA sequence from a file, 
*display the sequence, 
*and output the corresponding codons and amino acid translations in all three reading frames.
*/

 //Define necessary imports 
 import java.util.Scanner;
 import java.io.File;
 import java.util.ArrayList;
 import java.util.HashMap;

class DNARead {
    public static void main(String[] args) {
        //Ask the user for the file they wish to use
        Scanner userFile = new Scanner(System.in);
        System.out.println("Please enter the file you wish to use:");
        String file = userFile.nextLine();

        //Set DNA file sequence to a string
        FileRead d = new FileRead();
        d.openFile(file);
        d.readFile();
        d.closeFile();

        //
        String Dna = d.getDnaSeq();

        //Print out the information to the console
        System.out.println("");
        System.out.println("The DNA sequence is: ");
        System.out.println(Dna);
        System.out.println("");
        
        //Print the reading frames using thr codon() and codon2aa() methods from the FileRead.java file
        for (int frame = 1; frame <= 3; frame++) {
            ArrayList<String> codons = d.codon(frame);
            ArrayList<String> DnaAA = d.codon2aa(frame);
            System.out.println("");
            System.out.println("Reading frame #" + frame + " codons are:");
            //Print the ArrayLiost<String> with correct delimiter for output formatting
            System.out.println(String.join(" ", codons));
            System.out.println(String.join("", DnaAA));
            System.out.println("");
        }
        //Close the user's file
        userFile.close();
    }
 }
 

 
