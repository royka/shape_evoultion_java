
package RenderCA;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author samjacobclift
 * 
 * Renders an STL file
 * like before but adds an
 * onPick event to the shape
 * 
 * 
 */
public class PickRender extends MouseAdapter {

    //Create a Canvas3D object
    private GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    private Canvas3D canvas3D = new Canvas3D(config);
    //new frame to store the canvas
    private Frame frame = new Frame("Rendered CA");
    //new pick canvas
    private PickCanvas pickCanvas;
    //define a selected color
    private Color3f selectedCol;
    //define an unselected color
    private Color3f unSelectedcol = new Color3f(Color.white);
    // new private boolean that states if the mouse event
    // has fired
    private boolean pickedOrNot = false;

    public PickRender(String strFileName, String filePath, int X, int Y, 
                        float Zoom, Color3f selectedColor) throws FileNotFoundException, IOException {


        
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


        //try to render the found file
        try {

            //import the stl file into scene
            stlScene = stlLoad.load(url);


            //define the selcted color
            this.selectedCol = selectedColor;

            //defines a rectangle to specfiy how big the convas3D is
            Rectangle canvasBounds = new Rectangle(X, Y);


            //set the bounds of the canvas
            canvas3D.setBounds(canvasBounds);

            //add the canvas to the frame
            frame.add(canvas3D);


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

            //specify the rotation
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
            
            //new scene group taken from the STL file
            // BranchGroup stlShape = stlScene.getSceneGroup();


            //add the stlScene to the transform group
            objSpin.addChild(stlScene.getSceneGroup());

            
            //load up the transformation bgs
            objRoot.addChild(objRotate);
            objRotate.addChild(objSpin);


            //complie the root node
            objRoot.compile();

            //add the root node to the simple universe so that they can be viewed
            simpleU.addBranchGraph(objRoot);



            //now add an onlclick event to canvas object
            pickCanvas = new PickCanvas(canvas3D, objRoot);
            //set the pick mode to bounds only
            pickCanvas.setMode(PickCanvas.BOUNDS);
            //set the tolerance to very high
            pickCanvas.setTolerance(5f);
            //add the mouselistener event 
            //in this class
            this.canvas3D.addMouseListener(this);

        } catch (Exception e) {

            System.out.println("Illegal shape trying to be generated");
            //if a illegal shape has been generated
            //render a standard shape
            


        }


    }

    public PickCanvas returnCanvas() {
        //return the csnvas 
        return this.pickCanvas;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //onclick event thay changes the color of the selected shape   

        //set the pick location to where the evnt is
        pickCanvas.setShapeLocation(e);
        
        //pick the closes shape to the location
        PickResult result = pickCanvas.pickClosest();

        //the selected appearance
        Appearance selected = new Appearance();



        //color attribute
        ColoringAttributes selectedCA = new ColoringAttributes(selectedCol, ColoringAttributes.NICEST);

        //add the coloring to the appearance
        selected.setColoringAttributes(selectedCA);

        //allow the appearance to be overwritten
        selected.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);

        //the unselected apperance
        //the selected appearance
        Appearance unSelected = new Appearance();

        ColoringAttributes unSelectedCA = new ColoringAttributes(unSelectedcol, ColoringAttributes.NICEST);

        //add the coloring to the appearance
        unSelected.setColoringAttributes(unSelectedCA);

        //allow the appearance to be overwritten
        unSelected.setCapability(Appearance.ALLOW_COLORING_ATTRIBUTES_WRITE);


        //if can find any shape near
        //to the pick
        if (result == null) {

            System.out.println("Nothing picked");

        } else {

            //get the 3D shape that has been picked 
            Shape3D shape = (Shape3D) result.getNode(PickResult.SHAPE3D);


            //if a shape is found and it hasnt been picked
            if (!pickedOrNot) {



                //set the appearance of the item selected
                //to the new one
                shape.setAppearance(selected);

                //flag that the mouse even has fired
                pickedOrNot = true;

                System.out.println("shape selected");




            } else {
                //it has been picked befoe deselect it by going back to white            

                //set the apperance to unslected
                shape.setAppearance(unSelected);

                //set it to not picked
                pickedOrNot = false;


            }

        }

    }

    public boolean isPickedOrNot() {
        //returns true if the shape has been picked    
        return pickedOrNot;
        
    }
}