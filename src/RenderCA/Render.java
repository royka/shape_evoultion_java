
package RenderCA;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javax.media.j3d.*;
import javax.vecmath.Vector3f;

/**
 *
 * @author samjacobclift
 * 
 * Renders and STL file so that it can be
 * added to a panel on the form
 * 
 * adds rotations, lights etc etc
 * 
 */
public class Render extends Applet {

    //Create a Canvas3D object
    private GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    private Canvas3D canvas3D = new Canvas3D(config);

    public Render(String strFileName, String filePath, int X, int Y, 
                        float Zoom) throws FileNotFoundException, IOException {
       
       
        

        //new stl file loader
        STLLoader stlLoad = new STLLoader();

 
        //define the example stl file
        File stlfile = new File(filePath + strFileName + ".stl");

        //new 3D scene that will contain 
        //the stl file
        Scene stlScene;

        //new file locater
        URL url = null;

            //try to find the file
            //from the url
            try {

                //find the filepath for the file
                url = stlfile.toURI().toURL();

                //throw an exception if the file is found
            } catch (IOException e) {
                //put out an error to the console
                System.out.println("STL file not found check filepath");
            }


        //import the stl file into scene
        stlScene = stlLoad.load(url);


        //defines a rectangle to specfiy how big the convas3D is
        //Rectangle canvasBounds = new Rectangle(114, 114);
        Rectangle canvasBounds = new Rectangle(X , Y);

        //add to the centre fo the canvas
        add("Center", canvas3D);

        //set the bounds of the canvas
        canvas3D.setBounds(canvasBounds);

        //new universe with the canvas
        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

        //set the view configuration to the deaulft for a simplu
        simpleU.getViewingPlatform().setNominalViewingTransform();

        //root branch group
        BranchGroup objRoot = new BranchGroup();


        //rotate and temp rotate transform objects
        Transform3D rotate = new Transform3D();
        Transform3D tempRotate = new Transform3D();

        //specfi the rotation angles of the 
        //CA in the scene
        rotate.rotX(Math.PI / 4.0d);
        tempRotate.rotY(Math.PI / 5.0d);
        rotate.mul(tempRotate);


        //create a new vector to repostion the object and add it
        //to the rotate transformation
        Vector3f zoomOut = new Vector3f(.0f, -2.0f, Zoom);
        rotate.setTranslation(zoomOut);


        //new rotate branch group used to store all the rotation
        //movments
        TransformGroup objRotate = new TransformGroup(rotate);

        //new TFG that stores all the spin movements
        //allow us to write movement to said TFG
        TransformGroup objSpin = new TransformGroup();
        objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        // Create a new Behavior object that performs the desired
        // operation on the specified transform object and add it into
        // the scene graph
        Transform3D yAxis = new Transform3D();
        Alpha rotationAlpha = new Alpha(-1, 4000);

        //spcify the rotation
        RotationInterpolator rotator = new RotationInterpolator(rotationAlpha,
                                            objSpin,
                                            yAxis, 
                                            0.0f,
                                            (float) Math.PI * 2.0f);

        
        // a bounding sphere specifies a region a behavior is active
        // create a sphere centered at the origin with radius of 1
        BoundingSphere bounds = new BoundingSphere();
        bounds.setRadius(.22);

        rotator.setSchedulingBounds(bounds);
        objSpin.addChild(rotator);

        //add the stlScene to the transform group
        objSpin.addChild(stlScene.getSceneGroup());


        //load up the transformation bgs
        objRoot.addChild(objRotate);
        objRotate.addChild(objSpin);

        
        //complie the root node
        objRoot.compile();

        //add the root node to the simple universe so that they can be viewed
        simpleU.addBranchGraph(objRoot);
        

    }

    
    
    
    public Canvas3D returnCanvas() {

        //return the csnvas 
        return this.canvas3D;

    }
    
    
    
    
}
