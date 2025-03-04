#Program parses a SAM file to count the number of reads aligning to each gene

#Import module re 
import re

#Define the function gene_id() 
def gene_id():
    #Set up the dictionary gene_dic
    gene_dic = {}
    #Ask the user for an input file they wish to parse 
    with open(input("Please enter the SAM file you wish to use: "), 'r') as input_file:
        #Create a for loop to read through the line in the file
        for line in input_file:
            #Set a varibale 'location' to the location of the geneID
            location = re.search(r'SN:(gene\d+)', line)
            if location:
                #Select the propper group for formatting to match the prompt (Removal of SN:)
                gene_id = location.group(1)
                #Count the number of instances of the genes in the dictionary with key gene_id and vlaue being count
                if gene_id in gene_dic:
                    gene_dic[gene_id] += 1
                #If the gene_id only appears once set the count to 1 
                else:
                    gene_dic[gene_id] = 1

    #Sort the gene_dic in decending order 
    sorted_gene_dic = sorted(gene_dic.items(), key=lambda x: x[1], reverse=True)
    #Print the table headers to the consolse 
    print("GeneID", '\t', "Number of Reads Aligning")  
    #use a loop to print the results of the gene_if and count to the console 
    for gene_id, count in sorted_gene_dic:
        print(gene_id, '\t', count)

#Call function gene_id into use          
gene_id()
