
package CellularAutomata;

import Export.CAtoSTL;
import GeneticAlgorthirm.Genome;
import java.util.Random;
/**
 *
 * @author samjacobclift
 * Cellular Automata class
 * 
 * 
 */
public class CellularAuto {

    private Random ranGen = new Random();
    private int Axis;
    //stores the current gen of the CA
    private int[][] caCurrentGen;
    //stores the next gen of the CA
    private int[][] caNextGen;
    //stores the IC of the CA
    private int[][] caIntialConfiguartion;
    //all the binary combinations
    private BinaryCombinations bc = new BinaryCombinations(5);

    public CellularAuto(int Axis) {

        //set the number of rows in the X and Y axis
        this.Axis = Axis;
      

        //set the size of the parents array
        caCurrentGen = new int[Axis][this.Axis];
        caNextGen = new int[Axis][this.Axis];
        caIntialConfiguartion = new int[Axis][this.Axis];


        //create and store the IC for the CA's
        for (int y = 0; y < this.Axis; y++) {
            for (int x = 0; x < this.Axis; x++) {
                //select a random number either 1 or 0
                this.caIntialConfiguartion[y][x] = ranGen.nextInt(2);
            }
        }

        
        this.caIntialConfiguartion[1][1] = 1;
    }

    public void createOutputCA(Genome Gene, String FileName, String filePath) {
        //creates a CA based on a Gene passed on to it    

        //new 3D array object   
        ThreeDArray STLarray = new ThreeDArray(this.Axis);
        
        //set the current CA to IC
        this.caCurrentGen = this.getIC();


        
        //evoule the CA inserting into the 3D array each new gen
        for (int i = 0; i < 10; i++) {
            //evoule the CA
            //this.evouleGridWithRule(Gene.toString());
            
        }

        
        //evoule the CA inserting into the 3D array each new gen
        for (int i = 1; i < this.Axis; i++) {


            //evoule the CA
            this.evouleGridWithRule(Gene.toString());

            //add the CA to the 3D array
            STLarray.insert2Darray(this.caCurrentGen);

        }

        //export the CA 
        CAtoSTL export = new CAtoSTL(STLarray.returnDims());


        //print out the 3d array to a file
        export.writeCAtoSTL(STLarray.getSTLArray(), FileName, filePath);
        System.out.println("Exported file");


    }

    private void evouleGridWithRule(String Rule) {
        //evoules the grid with a rule parsed in


        //loop  y axis
        for (int y = 0; y < this.Axis; y++) {

            //loop x axis                      
            for (int x = 0; x < this.Axis; x++) {

                //loop though all the rules
                for (int i = 0; i < bc.NumCombinations() ; i++) {

                    //goes though the linked list implementing its rule
                    this.caNextGen[y][x] = this.implement5Rule(x, y,
                            bc.getBinary(i),
                            Integer.parseInt("" + Rule.charAt(i)));

                }

            }
        }
        //make the next gen the current one 
        this.replaceGens();

    }

    private int implement5Rule(int X, int Y, String neighbours, int Result) {
        //will implent a 5 (neighbour) bit rule
        /*
         *  o
         * ooo 
         *  o
         *
         */

        int iResult;
        int iRightPostion;
        int iLeftPostion;
        int iTopPostion;
        int iBottomPostion;


        //place in front
        if (X == 0) {
            iRightPostion = X;
            iLeftPostion = this.Axis - 1;

        } //place is at the end
        else if (X == this.Axis - 1) {
            iRightPostion = 0;
            iLeftPostion = this.Axis - 2;


        } //standard postions
        else {
            iRightPostion = X + 1;
            iLeftPostion = X - 1;
        }

        //descide on the top and bottom postions
        if (Y == 0) {
            iTopPostion = this.Axis - 1;
            iBottomPostion = Y + 1;
        } else if (Y == this.Axis - 1) {

            iTopPostion = this.Axis - 2;
            iBottomPostion = 1;

        } else {
            iTopPostion = Y - 1;
            iBottomPostion = Y + 1;


        }

        //deconstruct the string
        int nR = Integer.parseInt("" + neighbours.charAt(0));
        int nC = Integer.parseInt("" + neighbours.charAt(1));
        int nL = Integer.parseInt("" + neighbours.charAt(2));
        int nT = Integer.parseInt("" + neighbours.charAt(3));
        int nB = Integer.parseInt("" + neighbours.charAt(4));


        //check to see if the rule condition is met
        if (this.caCurrentGen[Y][iRightPostion] == nR
                && this.caCurrentGen[Y][X] == nC
                && this.caCurrentGen[Y][iLeftPostion] == nL
                && this.caCurrentGen[iTopPostion][X] == nT
                && this.caCurrentGen[iBottomPostion][X] == nB) {

            //set it to the rule result
            iResult = Result;

        } else {
            //set it to currently what the child is 
            iResult = this.caNextGen[Y][X];

        }

        //return the reslut of this rule
        return iResult;

    }

    private int[][] getIC() {
        //returns the Intial Confg of the CA

        return this.caIntialConfiguartion;

    }

    private void replaceGens() {
        //set this item in the children to the parents one                   
        this.caCurrentGen = this.caNextGen.clone();

    }

    public void printCurrentGrid() {
        //prints out the parent grid
        String sRow = "";

        //loop  y axis
        for (int y = 0; y < this.Axis; y++) {
            //loop x axis
            for (int x = 0; x < this.Axis; x++) {
                //construct a string of this row   
                sRow = sRow + Integer.toString(this.caCurrentGen[y][x]);
            }

            //print out that row
            System.out.println(sRow);
            //reset the string
            sRow = "";
        }

    }

    public void printNextGenGrid() {
        //prints out the parent grid
        String sRow = "";

        //loop y axis
        for (int y = 0; y < this.Axis; y++) {
            //loop x axis
            for (int x = 0; x < this.Axis; x++) {
                //construct a string of this row   
                sRow = sRow + Integer.toString(this.caNextGen[y][x]);
            }

            //print out that row
            System.out.println(sRow);
            //reset the string
            sRow = "";
        }


    }

    public int getCAValues(int X, int Y) {
        return caCurrentGen[Y][X];
    }

    public int[][] get2DCA() {
        return caCurrentGen;
    }

    public int returnCADim() {
        //returns the dim of the CA (cube)
        return this.Axis;
    }
    
    
    
}
