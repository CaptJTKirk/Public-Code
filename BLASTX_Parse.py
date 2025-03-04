#Parses a BLASTX file and extracts specific data such as the query, 
#best hit, e-value, and identity percentages.

#Import module regular expression (re)
import re

#Define the function main()
def main():
    #Ask the user for the file the wish to parse
    user_input = input("Enter the BLASTX file you wish to parse: ")
    #Set blastxdic to the information from the function blastx_parse from the user input 
    blastxdic = blastx_parse(user_input)
    #Set up table headers 
    print("Query", '\t', "BestHit", '\t', "E-value", '\t', "Identities", '\t')
    #Fill results to the console using new lines 
    for query, (best_hit, evalue, identities) in blastxdic.items():
        print(query, '\t', best_hit, '\t', evalue, '\t', identities,'\n')
        
#Define the variable blastx_parse() 
def blastx_parse(user_input):
    #Define the variables that will be used in the function blastx_parse()
    query = None
    blastx_dic = {}
    
    #Get a file from the user for parsing
    with open(user_input, 'r') as input_file:
        #Create a for loop to read the lines in the input file
        for line in input_file:
            query_loc = re.match(r'Query= (.*)', line)
            #If the file contains a query
            if query_loc:
                query = query_loc.group(1)
                continue 
            #Set variable to the location of the best hit 
            best_hit_loc = re.match(r'>(.*)\s', line)
            #Define the best hit in a variable and select the group number for propper formatting to match prompt
            if best_hit_loc:
                best_hit = best_hit_loc.group(1)
            #Set variable to the location of the e-value 
            evalue_hit = re.search(r'Expect = (.*)', line)
            #Define the e-value in a variable and select the group number for propper formatting to match prompt
            if evalue_hit:
                evalue = evalue_hit.group(0).replace("Expect = ", '')
            #Set variable to the location of the identity     
            identity_hit = re.search(r'Identities = (.*?),', line)
            #Define the identity in a variable and select the group number for propper formatting to match prompt
            if identity_hit:
                identities = identity_hit.group(0).replace("Identities = ", '').replace(",", '')


                #Create the dictionary with the query as the key and the best_hit, evalue, identities as values
                blastx_dic[query] = (best_hit, evalue, identities)
    return blastx_dic
                
#Call the main() function into use
main()
