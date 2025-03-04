/*
 * File Name: FileRead.java
 * Author: Charles LaCasse
 */

/*Reads a DNA sequence from a file, 
translates the DNA into codons, 
and maps those codons to their corresponding amino acids using 
a codon-to-amino acid lookup table.
*/


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Arrays;

 //Create a class to read DNA from a file 
 public class FileRead {
    
    //Create private variables only accesible by this file
    private Scanner r; 
    private StringBuilder DnaSeq = new StringBuilder();

    //Create the hash map to store the codon to amino acid chart in a directory
    private HashMap<String, HashMap<String, String>> codonDirectory;

    //Initialize the map
    public FileRead() {
        codonDirectory = new HashMap<>();
        initializeCodonMap();
    }

    //Initialize the directory 
    private void initializeCodonMap() {
        //Define each codon to amino acid single letter for DNA
        String[][] CodonInfo = {
            //Alanine
            {"A", "GCA"}, {"A", "GCC"}, {"A", "GCG"}, {"A", "GCT"},
            //Arginine 
            {"R", "CGT"}, {"R", "CGA"}, {"R", "CGC"}, {"R", "AGG"}, {"R", "CGG"}, {"R", "AGA"},
            //Asparagine
            {"N", "AAT"}, {"N", "AAC"},
            //Aspartic acid
            {"D", "GAT"}, {"D", "GAC"},
            //Cysteine 
            {"C", "TGT"}, {"C", "TGC"},
            //Glutamic acid
            {"E", "GAA"}, {"E", "GAG"},
            //Glutamine
            {"Q", "CAA"}, {"Q", "CAG"},
            //Glycine
            {"G", "GGG"}, {"G", "GGA"}, {"G", "GGC"}, {"G", "GGT"},
            //Histidine
            {"H", "CAT"}, {"H", "CAC"},
            //Isoleucine
            {"I", "ATT"}, {"I", "ATA"}, {"I", "ATC"},
            //Leucine
            {"L", "CTG"}, {"L", "CTA"}, {"L", "CTC"}, {"L", "CTT"}, {"L", "TTG"}, {"L", "TTA"},
            //Lysine
            {"K", "AAA"}, {"K", "AAG"},
            //Methionine
            {"M", "ATG"},
            //Phenylalnine
            {"F", "TTT"}, {"F", "TTC"},
            //Proline
            {"P", "CCT"}, {"P", "CCG"}, {"P", "CCA"}, {"P", "CCC"},
            //Serine
            {"S", "TCT"}, {"S", "TCA"}, {"S", "TCC"}, {"S", "TCG"}, {"S", "AGT"}, {"S", "AGC"},
            //Threonine
            {"T", "ACT"}, {"T", "ACC"}, {"T", "ACG"}, {"T", "AGA"},
            //Tryptophan
            {"W", "TGG"},
            //Tyrosine 
            {"Y", "TAT"}, {"Y", "TAC"},
            //Valine
            {"V", "GTG"}, {"V", "GTA"}, {"V", "GTC"}, {"V", "GTT"},
            //Stop Codons
            {"*", "TAA"}, {"*", "TAG"}, {"*", "TGA"}
        };

        //Populate the DNA directory 
        for (String[] codon : CodonInfo) {
            //If the codon length is less than 2 trigger this flag
            if (codon.length < 2) {
                //Create a system error to display where the error takes place
                System.err.println("Invalid information " + Arrays.toString(codon));
                continue;
            }
            //Define parts of the array spacing
            String aa = codon[0];
            String codonCode = codon[1];

            //Add a hashmap to the aa variable defined above
            codonDirectory.putIfAbsent(aa, new HashMap<>());
            //Map the info
            codonDirectory.get(aa).put(codonCode, aa);
        }
    }

    //Create a file open method
    public void openFile(String file) {
        try {
            r = new Scanner(new File(file));
        } 
        //Create a catch for the try if there is no file found
        catch(FileNotFoundException e) {
            System.out.println("File not found. Please try again.");
        }
    }
    //Create a readfile method that reads the user's file
    public void readFile() {
        while (r.hasNext()) {
            String Dna = r.next();
            DnaSeq.append(Dna);
        }
    }
    //Create a close file method
    public void closeFile() {
        r.close();
    }

    //Turn the DnaSeq to a string 
    public String getDnaSeq() {
        return DnaSeq.toString();
    }

    //Create the codons from the DNA found in the file 
    public ArrayList<String> codon(int frame) {
        ArrayList<String> codons = new ArrayList<>();
        int startIndex = frame -1;
        //Define the parameters of the codons to map in groups of 3 from the DNA sequence 
        for (int i = startIndex; i < DnaSeq.length() - 2; i += 3) {
            String codon = DnaSeq.substring(i, i+3);
            codons.add(codon);
        }
        //Return the codons formatted correctly 
        return codons;
    }

    //Create a method that will build the correct amino acid single letters in order of the codon appearances
    public ArrayList<String> codon2aa(int frame) {
        ArrayList<String> aminoAcid = new ArrayList<>();
        StringBuilder aaLine = new StringBuilder();
        int startIndex = frame -1;

        //Create a for loop that will assign the correct amino acid from the 
        for (int i = startIndex; i < DnaSeq.length() - 2; i += 3) {
            String codon = DnaSeq.substring(i, i+3);
            //Set the string aa to a default variable that will print if the amino acid is not found
            String aa = "#";
            //Use a for loop to iterate over the codon variable to extract the single letter amino acid from the directory
            for (Map.Entry<String, HashMap<String, String>> entry : codonDirectory.entrySet()) {
                if (entry.getValue().containsKey(codon)) {
                    aa = entry.getKey();
                    break;
                }
            }
            //Add formatting for the stringbuilder aaLine to be spaced under the first letter of each codon
            aaLine.append(aa).append("   ");
        }

        //Add the single amino acid to the variable stringbuilder aaLine
        aminoAcid.add(aaLine.toString().trim());
        //Return the amino acid formatted to one letter
        return aminoAcid; 

    }

    
}
