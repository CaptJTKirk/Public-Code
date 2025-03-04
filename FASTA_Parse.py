#Program that accepts as input a file with Genbank records
#The program should parse out the accession number and sequence
#Store the pair in a dictionary, where the key is the accession number and the value is the sequence

#Define variables
input_file = ""
acc_seq = {}

#Split file into two line FASTA reads (1st line is accession number and second is the sequnece
def get_seq():
     with open(input('Which file would you like to open: '), 'r') as f, open('output_test.txt', 'w') as o:
        text = []

        for line in f:
            if line.startswith('>'):
                if text:
                    o.write(''.join(text) + '\n')
                    text = []
                o.write(line)
            else:
                text.append(line.strip())
        if text:
            o.write(''.join(text) + '\n')
#Create a holding file that will store the first word of each line
#This will only use the accesion number in the first line and remove the rest 
def hold():
    location = ''

    with open('output_test.txt', 'r') as x, open('Holding_File.txt', 'w') as o:
        #input_file = x.readlines()
        #Import the module regular expression (re) 
        import re

        for line in x:
            first_word = line.split()
            location = first_word[0].strip('>')
            o.write(location + '\n')

#Use the 'Holding_File.txt' to write to the 'Combine_File.txt' for use in the extraction for the dictionary 
def combine():
    with open('Holding_File.txt', 'r') as f, open('Combine_File.txt', 'w') as w:
        line1 = f.readline().strip()
        line2 = f.readline().strip()
        line3 = f.readline().strip()
        line4 = f.readline().strip()
        line5 = f.readline().strip()
        line6 = f.readline().strip()
        line7 = f.readline().strip()
        line8 = f.readline().strip()
        line9 = f.readline().strip()
        line10 = f.readline().strip()
        w.write(line1 + ' ,' + line2 + '\n')
        w.write(line3 + ' ,' + line4 + '\n')
        w.write(line5 + ' ,' + line6 + '\n')
        w.write(line7 + ' ,' + line8 + '\n')
        w.write(line9 + ' ,' + line10 + '\n')

#Create a dictionary with the information aquired from the file 'Combine_File.txt'  
def main():
    #Open the file
    with open('Combine_File.txt', 'r') as f:
        input_file = f.read()

    #Create empty dictionary
    acc_seq = {}

    #Copy the contents of the file into the dictionary
    for line in input_file.splitlines():
        if line:
            #Use map() for each iteration  
            key, value = map(str.strip, line.split(','))
            acc_seq[key] = value

    #Print dictionary to the console
    print(acc_seq)
    
#Call the functions into use  
get_seq()
hold()
combine()
main()



