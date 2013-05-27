

package GeneticAlgorthirm;


import java.util.Random;

/**
 *
 * @author SJ2-CLIFT
 * Genome Class 
 */
public class Genome {

    private int[] genome;
    private int numofGenes;
    private int fitness;
    Random randGen = new Random();

   
    public Genome(int numofgenes) {
        //store the number of genes in the geneome
        numofGenes = numofgenes;
        //define the array as the gene size
        genome = new int[numofgenes];

        //create a random genenome for the object
        for (int X = 0; X < numofGenes; X++) {
            //load the binary value into the array
            genome[X] = randGen.nextInt(2);


        }
       //set the fitness
       // this.setFitness();

    }
  

    public int getGenome(int place) {
        return this.genome[place];
    }

    public void setGenome(int gene, int valueOfGene) {
        this.genome[gene] = valueOfGene;
    }
  
    @Override
    public String toString() {
        String sReturn;
        sReturn = "";
        for (int i = 0; i < numofGenes; i++) {
            sReturn = sReturn + genome[i];
        }
        return sReturn;
    }

    public void overrideFitness(int iCAFit) {
        this.fitness = iCAFit;
    }

    public int getFitness() {
        return fitness;
    }
    
    public void setEntireGenome(Genome gChild) {
        //loop tohugh the entire genome setting it to the gChild
        for (int i = 0; i < numofGenes; i++) {
            this.setGenome(i, gChild.getGenome(i));
        }
        //set the fitness
        //this.setFitness();

    }


    
}
