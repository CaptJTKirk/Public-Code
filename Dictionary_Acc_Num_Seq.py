#Program that reads in this file and creates a dictionary,
#where the keys are the accession numbers and the values are the sequences.


#Define function GC_Percent()     
def GC_Percent(sequence):
    #Count the number of times G and C are present in the sequence
    gc_counts = sequence.count('G') + sequence.count('C')
    #Calulate how many bp are in the sequence for determining overall GC content 
    total_gc_count = len(sequence)
    #Calculate the percent GC of the Seqeucne 
    total_gc = (gc_counts/total_gc_count)*100
    #Return the total percent GC 
    return total_gc

#Define function AT_Percent()
def AT_Percent(sequence):
    #Count the number of times A and T are present in the sequence
    at_counts = sequence.count('A') + sequence.count('T')
    #Calulate how many bp are in the sequence for determining overall AT content
    total_at_count = len(sequence)
    #Calculate the percent GC of the Seqeucne 
    total_at = (at_counts/total_at_count)*100
    #Return the total percent AT 
    return total_at

#Create function gen_info()
def gen_info():
    #Create the dictionary
    gen_data = {}
    #Ask the user for a file 
    with open(input("Please enter the file you wish to create a dictionary for: ")) as f:
        #Create a for loop to read through the file and set up the dictionary
        #wit hthe accession as the key and the sequence as the value
        for line in f:
            accession, sequence = line.strip().split(",")
            gen_data[accession] = sequence
    #Set up the table headers         
    print("SeqID", '\t', "GC%", '\t', 'AT%')
    #Create a for loop that references the function GC_Percent() and AT_Percent() to get the data from them 
    for accession, sequence in gen_data.items():
        gc_percentage = GC_Percent(sequence)
        at_percentage = AT_Percent(sequence)
        #Print results to the console 
        print(accession, '\t', "{:.2f}".format(gc_percentage), '\t', "{:.2f}".format(at_percentage))
#Call function gen_info() into use 
gen_info()
