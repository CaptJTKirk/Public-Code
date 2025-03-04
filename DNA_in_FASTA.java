/*
 * File: DNA_in_FASTA.java
 * Author: Charles LaCasse
 */

/*The program collects a clone name and a DNA sequence from the user through console input and outputs the information in FASTA format. 
It uses two Scanner objects to capture the user inputs, prints the clone name preceded by a ">" symbol (standard for FASTA headers), and displays the DNA sequence. 
*/

import java.util.Scanner;
public class DNA_In_FASTA {
    public static void main(String[] args) {
        //Create the scanners
        Scanner cloneName = new Scanner(System.in);
        Scanner seq = new Scanner(System.in);

        //Store the variables as strings
        String cN;
        String s;
    
        //Print out the prompts to the console
        System.out.println("Enter a clone name: ");
        cN = cloneName.next();
        System.out.println("Enter your sequence: ");
        s = seq.next();

        //Print the user's input to the console
        System.out.println("");
        System.out.println("");
        System.out.println("The information you entered in FASTA format is: ");
        System.out.println(">"+ cN);
        System.out.println(s);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("Ending program...");

    //Close the scanners 
    cloneName.close();
    seq.close();
    }
}
