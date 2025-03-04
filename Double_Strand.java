/*
 * Double_Strand.java
 * Author Charles LaCasse 
 */

/*Takes a DNA sequence from the user and generates a complementary strand. 
Displays both original and complementary in program output.
*/

//Import a scanner for user inout 
import java.util.Scanner; 

//Create the class 
public class Double_Strand {
    public static void main(String[] args){
        //Create a string builder for the complement (will be appended later) 
        StringBuilder complement = new StringBuilder();

        //Create a scanner for user input
        Scanner userInput = new Scanner(System.in); 

        //Get the user DNA strand. Add a way to convert lower case to upper case
        System.out.println("Please enter a DNA string: ");
        String userDNA = userInput.nextLine().toUpperCase();

        //Find the complements to the user's DNA sequence 
        for (int i=0; i<userDNA.length(); i++) {
            char base = userDNA.charAt(i);
            /*Utilize a switch for the complement to the user's input 
            append the string builder with the complement for each base*/
            switch (base) {
                case 'A':
                    complement.append('T');
                    break;
                case 'T':
                    complement.append('A');
                    break;
                case 'C': 
                    complement.append('G');
                    break;
                case 'G':
                    complement.append('C');
                    break;
            }
        }
        //Print the original user sequence to the console neatly
        System.out.println("");
        System.out.println("Here is the starting DNA:");
        System.out.println(userDNA);
        System.out.println("");
        System.out.println("Here is the double-stranded DNA:");
        System.out.println(userDNA);
        System.out.println(complement.toString());
        System.out.println("");
        System.out.println("Ending program...");

        //Close the open scanner 
        userInput.close();
    }
    
}
