
package CellularAutomata;

/**
 *
 * @author samjacobclift
 * 3D array class
 * 
 */
public class ThreeDArray {


    private int stlArray[][][];
    private int dimensionCA;
    private int numOfGenerations = 0;

    public ThreeDArray(int dims) {
   

        //redim the array to the correct size
        stlArray = new int[dims][dims][dims];

        //store the dimensions
        this.dimensionCA = dims;

    }

    public void insert2Darray(int[][] arrayToAdd) {



        //loop though the Y coordinates 
        for (int i = 0; i < this.dimensionCA; i++) {
            //loop thoguh the X coordinates
            for (int j = 0; j < this.dimensionCA; j++) {

                //set the value in the array to add
                this.stlArray[numOfGenerations][i][j] = arrayToAdd[i][j];

            }

        }

        //increment the num of gens
        numOfGenerations++;
    }

    public int[][][] getSTLArray() {
        
        //return the entire array
        return stlArray;
    }

    public int returnDims() {
        
        //return the dimensions of the array
        return this.dimensionCA;

    }
    
    public int reuturnGivenValue(int x , int y , int z){
        
        //return a given value of the cell
        return this.stlArray[x][y][z];
        
    
    }
}
