package Export;

/**
 *
 * @author SamjacobClift
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CAtoSTL {

    //grid size
    private int CA_GRID_SIZE;
    private final int SCALE = 10;
    //file write outs
    private transient FileWriter fW;
    private transient BufferedWriter bW;
    private transient PrintWriter stl;

    public CAtoSTL(int iGridSize) {
        //set the grid size
        this.CA_GRID_SIZE = iGridSize;

    }
    
    
    public void writeCAtoSTL(int[][][] grid, String Filename, String filePath) {

        //set the buffers to null
        fW = null;
        bW = null;
        stl = null;
        
        //store the filepath
        String Path = filePath + Filename;           
        
        
        try {

            //set the new file and open the write buffers
            fW = new FileWriter(new File(filePath + Filename + ".stl"));
            bW = new BufferedWriter(fW);
            stl = new PrintWriter(bW);

        } catch (Exception e) {
            //cant find the .STL file
            System.out.println("Error creating STL file" + e);
        }


        //first line 
        stl.printf("solid automata\n");
        //loop though the grid/voxels
        for (int z = 0; z < CA_GRID_SIZE; z++) {
            for (int x = 0; x < CA_GRID_SIZE; x++) {
                for (int y = 0; y < CA_GRID_SIZE; y++) 
                {
                    dovoxel(x, y, z, grid);
                }
            }
        }
        
        //last line
        stl.printf("endsolid automata\n");

        //clsoe the file
        try {

            stl.flush();
            bW.flush();
            fW.flush();
            fW.close();

        } catch (Exception e) {
            //error closing the file
            System.out.println("Error closing STL file" + e);
        }



    }

    private double topos(int i) {

        return (double) i / (double) (CA_GRID_SIZE);
    }

    private void dotri(int x0, int y0, int z0,
                        int x1, int y1, int z1,
                        int x2, int y2, int z2,
                        int nx, int ny, int nz) {
        
        //write the voxel
        stl.printf("facet normal %d %d %d\n  outer loop\n", nx, ny, nz);
        stl.printf("    vertex %.5f %.5f %.5f\n", topos(x0 * SCALE), topos(y0 * SCALE), topos(z0 * SCALE));
        stl.printf("    vertex %.5f %.5f %.5f\n", topos(x1 * SCALE), topos(y1 * SCALE), topos(z1 * SCALE));
        stl.printf("    vertex %.5f %.5f %.5f\n", topos(x2 * SCALE), topos(y2 * SCALE), topos(z2 * SCALE));
        stl.printf("  end loop\n  end facet\n");
    }

    private void doface(int x, int y, int z,
                            int x0, int y0, int z0,
                            int x1, int y1, int z1,
                            int x2, int y2, int z2,
                            int x3, int y3, int z3,
                            int nx, int ny, int nz) {
        dotri(x + x0, y + y0, z + z0, x + x1, y + y1, z + z1, x + x2, y + y2, z + z2, nx, ny, nz);
        dotri(x + x2, y + y2, z + z2, x + x3, y + y3, z + z3, x + x0, y + y0, z + z0, nx, ny, nz);
    }

    private int getvoxel(int x, int y, int z, int[][][] voxels) {
        //if the x y z coodinates r out of bounds make it 0
        if ((x >= CA_GRID_SIZE) || (x < 0)) {
            return 0;
        }
        if ((y >= CA_GRID_SIZE) || (y < 0)) {
            return 0;
        }
        if ((z >= CA_GRID_SIZE) || (z < 0)) {
            return 0;
        }

        //else return the voxel value
        return voxels[x][y][z];
    }

    private void dovoxel(int x, int y, int z, int[][][] voxels) {
        
        //if the voxel != 0 a 1
        if (getvoxel(x, y, z, voxels) != 0) {

            if (getvoxel(x, y, z - 1, voxels) == 0) {
                doface(x, y, z, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, -1);
            }

            if (getvoxel(x, y, z + 1, voxels) == 0) {
                doface(x, y, z, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1);
            }

            if (getvoxel(x - 1, y, z, voxels) == 0) {
                doface(x, y, z, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, -1, 0, 0);
            }

            if (getvoxel(x + 1, y, z, voxels) == 0) {
                doface(x, y, z, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0);
            }

            if (getvoxel(x, y - 1, z, voxels) == 0) {
                doface(x, y, z, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, -1, 0);
            }

            if (getvoxel(x, y + 1, z, voxels) == 0) {
                doface(x, y, z, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0);
            }
        }
    }

    
}
