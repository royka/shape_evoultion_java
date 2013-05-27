
package CellularAutomata;

import java.util.LinkedList;

/**
 *
 * @author samjacobclift
 * generates a list of binary strings, used by the CA class
 * 
 * 
 */
public class BinaryCombinations {
    
    //list to store all the combinations
    protected LinkedList<String> binary = new LinkedList<String>();

    public BinaryCombinations(int numOfBits) {

        String binaryString;
        //max number of binary strings
        int maxLengthOfBinaryString;

        //convert the number of bits in to number of combinations
        //use 2^NumOfBits
        int NumOfCombinations = (int) Math.pow(2, numOfBits);
        //find the max binar value
        maxLengthOfBinaryString = Integer.toBinaryString(NumOfCombinations).length();

        //produce all the binary numbers
        for (int i = 0; i < NumOfCombinations; i++) {

            //get the binary string of i
            binaryString = Integer.toBinaryString(i);

            //add in the 0's
            while (binaryString.length() < maxLengthOfBinaryString - 1) {
                binaryString = 0 + binaryString;
            }

            //System.out.println(binaryString);
            //add it to the list 
            binary.add(binaryString);


        }

    }

    public String getBinary(int postion) {
        return binary.get(postion);
    }

    public int NumCombinations() {
        return binary.size();

    }

    @Override
    public String toString() {
        return "BinaryCombinations{" + "binary=" + binary + '}';
    }
}
