#Given a DNA string, prints out the 20 characters upstream of the start codon ATG.
#Import module re 
import re

#Define function seq_split()
def seq_split():
    #Ask the user for a sequence of data 
    dna = input("Please enter a DNA sequence to print the first 20 characters upstream from the start codon ATG: ").upper()
    #Search the user input variable 'dna' for the start codon 'ATG' 
    dna_index = re.search(r"ATG", dna)
    #Create an if statement that will allow for the printing of the upstream bases 
    if dna_index:
        #Starts at the 'ATG' in the user sequence index and wil print out the 20 upstream characters in the sequence from 'dna_index'
        seq_print = dna[max(0, dna_index.start() - 20):dna_index.start()]
        #Print the result to the console 
        print(f"The 20 bases upstream of ATG are {seq_print}.")

#Call the function seq_split() into use    
seq_split()
