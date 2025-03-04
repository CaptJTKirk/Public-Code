#Convert a FASTQ file to a FASTA file

def fastq_to_fasta(input_file, output_file):
    with open(input_file, 'r') as f_in, open(output_file, 'w') as f_out:
        while True:
            # Read four lines from the FASTQ file
            seq_id = f_in.readline().strip()
            sequence = f_in.readline().strip()
            _ = f_in.readline()  # Skip the '+' line
            _ = f_in.readline()  # Skip the quality scores line
            
            # Check if we reached the end of the file
            if not seq_id:
                break
            
            # Write the sequence to the FASTA file
            f_out.write(f'>{seq_id[1:]}\n')  # Remove the leading '@'
            f_out.write(f'{sequence}\n')

#Call the function into use:
input_file = input("Enter the FASTQ file you would like to convert to FASTA: ")
output_file = input("What would you like the FASTA file to be called: ")
fastq_to_fasta(input_file, output_file)
