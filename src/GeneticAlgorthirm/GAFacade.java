package GeneticAlgorthirm;

import java.util.Random;

/**
 *
 * @author samjacobclift
 * Genetic Algorthirm Facade class
 * handles selection, crossover and mutation 
 * as well as crossover
 * 
 */
public class GAFacade {

    //pop size
    private int popSize;
    //num of genes
    private int numofGenes;
    //Genome that will be returned
    private Genome returnedGenes[];
    //new random generator
    private Random ranGen = new Random();

    public GAFacade(int populationSize, int numGenes) {

        //store the pop size and the num of genes    
        popSize = populationSize;
        numofGenes = numGenes;

        //create the new population  wth the specfied pop size    
        returnedGenes = new Genome[populationSize];

        //create a blank population of genes
        for (int X = 0; X < popSize; X++) {
            //insert dummy genes 
            returnedGenes[X] = new Genome(numofGenes);
        }

    }

    public Genome[] generateNewIndividuals(Genome selectedGenes[], int numOfSelected) {
//generates 6 new genomes based on the ones that are passed into
//this object


        //generate new individuals until there
        //are 8 new individuals
        for (int i = 0; i < 8; i++) {

            //crossover need not be used
            //selects a random individual, crossover is not needed        
            //returnedGenes[i] = this.crossOver(selectedGenes, numOfSelected);

            //mutate the new indiviual
            returnedGenes[i] = this.mutateTheString(returnedGenes[i]);

        }

        return returnedGenes;


    }

    private Genome crossOver(Genome[] selectedGenes, int numOFSelectedGenes) {
        //single point crossover
        

        //get a random crossover point 
        int crossoverPoint = ranGen.nextInt(numofGenes);

        //make sure the crossover point isnt = 0 
        if (crossoverPoint == 0) {
            crossoverPoint++;
            
        }
        
        
        //Genome to return
        Genome gReturn = new Genome(numofGenes);


        //blank genome array to store indviuals    
        Genome gSelected1;
        Genome gSelected2;

        //Pick 2 Random Individual from the population selected
        gSelected1 = selectedGenes[ranGen.nextInt(numOFSelectedGenes)];        
        gSelected2 = selectedGenes[ranGen.nextInt(numOFSelectedGenes)];
     

        //build up the new genome
        for (int i = 0; i < crossoverPoint; i++) {
            //set the current postion of the geneome to
            //to corresponding value in the first selected
            gReturn.setGenome(i, gSelected1.getGenome(i));
        }

        for (int j = crossoverPoint; j < numofGenes; j++) {
            //set the current postion of the geneome to
            //to corresponding value in the first selected
            gReturn.setGenome(j, gSelected2.getGenome(j));
        }



        //return the most fitest indvidual
        return gReturn;

    }

    public Genome mutateTheString(Genome gGenome) {
    // 1in32 mutation
        
        //new blank geneome
        Genome MutatedGene = gGenome;
        
                //loop though the population being parsed in
        for (int i = 0; i < popSize; i++) {
            //random flip one bit in the gene
            int ranNum = this.ranGen.nextInt(this.numofGenes);
            
            
            if (MutatedGene.getGenome(ranNum) == 1) {
                        //convert to 0 if 1
                        MutatedGene.setGenome(ranNum, 0);
                    } else {
                        //convert to 1 if 0
                       MutatedGene.setGenome(ranNum, 1);

                    }
            
            

        }
        
        
        return MutatedGene;
        
    }
}
