/*
 * File Name: DNAH4.java
 * Author: Charles LaCasse
 */

/*Reads a FASTA-formatted DNA sequence file, 
stores the sequences in memory, 
and allows the user to search for specific sequences by their IDs.
*/

 //Import necessary java features 
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.File;


//Create the public class for DNA to be used by program DNA_Main.java
public class DNAH4 { 
    //Create a private class for use only in the DNA specified class 
    private String id;
    private String seq; 

    //Initialize the variables that will be used 
    public DNAH4(String dna_id, String dna_seq) {
        id = dna_id;
        seq = dna_seq;
    }
    //Getter for the sequecne ID
    public String getID() {
        return id;
    }
    //Getter for the seq 
    public String getSeq() {
        return seq;
    }
    //Create the main for the program to run
    public static void main(String[] args) {
        //Ask the user for the file they wish to use
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter the file you wish to use:");
        String file = userInput.nextLine();   

        //Use a file read to parse the user's fasta file and store as an Array
        ArrayList<DNAH4> dnaSequence = parseFastaFile(file); 

        //Create while loop that asks user for a seq ID and fetches the correct sequence 
        while(true) {
            //Ask the user for the sequence they wish to use 
            System.out.println("\nEnter the sequence ID you are looking for (or enter 'quit' to exit the program):");
            String userID = userInput.nextLine();

            //Create a boolean flag that will designate if the sequence is found 
            boolean match = false;
            //use a for loop to access the data stored in the array 
            for (DNAH4 dna : dnaSequence) {
                if (dna.getID().equalsIgnoreCase(userID.trim())) {
                    System.out.println(">" + dna.getID());
                    System.out.println(dna.getSeq());
                    System.out.println("");
                    match = true;
                    break;
                }
            }
            //Create sequence not found statment
            if (!match) {
                System.out.println("The sequence id: " + userID + " was not found in the file.");
                return;
            }
            //Create exit for the while loop (ignore case of user input)
            if(userID.equalsIgnoreCase("quit")) {
                System.out.println("\nExiting the program");
                break;
            }
        }
        //Close the open scanner
        userInput.close();
    }

    //Create a method that parses the FASTA file 
    public static ArrayList<DNAH4> parseFastaFile (String file) {
        ArrayList<DNAH4> dnaList = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(new File(file));
            //Use a stringBuilder to get the sequences 
            StringBuilder sequenceBuilder = new StringBuilder();
            String currID = null; 

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();

                //Use of nested if statements to find where to start reading
                if (line.startsWith(">")) {
                    if (currID != null && sequenceBuilder.length() > 0) {
                        DNAH4 dna = new DNAH4(currID, sequenceBuilder.toString());
                        dnaList.add(dna);
                    }

                    //Find the current ID in the file by line 
                    int pipeIndex = line.indexOf('|');
                    if (pipeIndex != -1) {
                        currID = line.substring(1, pipeIndex).trim().replaceAll("\\s+", "");
                    } else {
                        currID = line.substring(1).trim().replaceAll("\\s+", "");
                    }
                    //Reset the sequence builder once the id has been recorded 
                    sequenceBuilder.setLength(0);

                } else {
                    //Append the sequence builder 
                    sequenceBuilder.append(line);
                }
            }
            //Add in the last sequence of the file 
            if (currID != null && sequenceBuilder.length() > 0) {
                DNAH4 dna = new DNAH4(currID, sequenceBuilder.toString());
                dnaList.add(dna);
            }
            //Close the scanner
            fileScanner.close();
            
        } 
        //Add a catch is no file is found 
        catch (FileNotFoundException e) {
            System.out.println("Error! File not found.");
        }
        return dnaList;
    }   
}

