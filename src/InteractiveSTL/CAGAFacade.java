/*
 * Cellular Automata and GA facade class
 */
package InteractiveSTL;

import CellularAutomata.CellularAuto;
import GeneticAlgorthirm.GAFacade;
import GeneticAlgorthirm.Genome;

/**
 *
 * @author samjacobclift
 */
public class CAGAFacade {

    //new CA object
    private CellularAuto CA;
    //GA object
    private GAFacade GA;
    
    //new Genome[] to store  genes 
    //that are used to write the files
    private Genome[] genes;
    
    //the number of individuals in the population
    int numOFCAs = 8;

    public CAGAFacade(int numOfPixels) {

        //new GA object
        GA = new GAFacade(numOFCAs, 32);
        
        //define the CA
        CA = new CellularAuto(numOfPixels);
        
        //create an intial random popualtion of genes, 
        //that will be used to popualte the intial screen
        genes = new Genome[numOFCAs];

        genes[0] = new Genome(32);
        genes[1] = new Genome(32);
        genes[2] = new Genome(32);
        genes[3] = new Genome(32);
        genes[4] = new Genome(32);
        genes[5] = new Genome(32);
        genes[6] = new Genome(32);
        genes[7] = new Genome(32);


    }

    public void generateNewGeneration(int[] selectedIndivduals, String filePath) {
        //will generate a new generation of 6 individuals based on
        //the ca's that were selected in the GUI

        Genome[] SelectedGenes = new Genome[selectedIndivduals.length];

        int selected = 0;

        //loop though the stored genes
        for (int i = 0; i < SelectedGenes.length; i++) {
            //if they have been selected add them to the 
            //selected genes array
            if (selectedIndivduals[i] == 1) {
                SelectedGenes[selected] = genes[i];
                selected++;
            }

        }


        //generate a new population of genes based on
        //the selected ones
        genes = GA.generateNewIndividuals(SelectedGenes, selected);

        //create and export CA's based on the new genes
        for (int i = 0; i < this.numOFCAs; i++) {
            CA.createOutputCA(genes[i], "CA" + i, filePath);
        }

    }

    public void generateIntialRandom(String filePath) {
        //generates a randomly created population and writes them 
        //out to files so that they can be imported into the GUI

        //create and export the CA's
        for (int i = 0; i < this.numOFCAs; i++) {

            //evoule using the genes and export       
            CA.createOutputCA(genes[i], "CA" + i, filePath);

        }


    }
}
