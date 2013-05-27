/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RenderCA;

import com.sun.j3d.utils.applet.MainFrame;
import java.awt.Frame;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * @author samjacobclift
 */
public class testRender {

    /** 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        
        
       //create a new frame contains an stl file  and displays it 
       Frame frame = new MainFrame(new Render("example object","/Users/samjacobclift/Netbeans/ComputingProject/STLFILES/", 1000 , 1000, -35.f), 1500, 1500);

    
        
    }
}
