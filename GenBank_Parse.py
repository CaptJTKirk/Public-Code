#Program to extract accession number and sequence from a GenBank File

#Import module regular expression (re)
import re

def main():
    user_input = input("Enter the GenBank file you wish to parse: ")
    #Call the function genbank_parse to be used with main() 
    genbank_parse(user_input)

#Define a function to parse the users input from main() 
def genbank_parse(user_input):
    #Define the variable that will be used in the function genbank_parse()
    entries = {}
    acc_num = ''
    seq_data = ''

    #Get a file from the user for parsing
    with open(user_input, 'r') as input_file:
        #create a for loop to read the lines in input_file (the user entered file)
        for line in input_file:
            #Find lines that contain accession number
            #Match on 'ACCESSION' and capture group with (.*)
            acc_num_loc = re.match(r'ACCESSION(.*)', line)
            #If the file contains accession number
            if acc_num_loc:
                #Store the accession number to the variable
                #Use the second word found in the match using .group(1)
                #then strip remaining whitespace .strip()
                acc_num = acc_num_loc.group(1).strip()

            #Find lines that contain the sequence of the entry using re.match()
            #Match on 'ORIGIN' and capture group with (.*)
            seq_data_loc = re.match(r'ORIGIN(.*)', line)
            #If the file contains a sequence in the origin section
            if seq_data_loc:
                #Store the sequence number to the variable
                seq_data = ''
                #Create a loop to read the lines in input_file
                for line in input_file:
                    #Identify where there is a new entry in the file by locating the seperator '//' that
                    #occurs between entries 
                    if line.strip() == '//':
                        break
                    #Use variable seq_data to store the parsed information from the input file
                    #and concate to the expression useing re.sub() and removing all numbers found in input_file
                    #and white spaces between the chunks then strip the line to remove any leading ro trailing whitespace
                    seq_data += re.sub(r'[0-9\s]', '', line.strip())
                #Stores the seq_data in the dictionary entries using the key acc_num 
                entries[acc_num] = seq_data
    #Returns the dictionary 'entries' printed to the console 
    return print(entries)

#Call the main() function into use 
main()
                
                    
