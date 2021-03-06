
package InteractiveSTL;

import RenderCA.Render;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.vecmath.Color3f;

/**
 *
 * @author samjacobclift
 */
public class preferencePanel extends javax.swing.JFrame {

  
    
    //refrance to interactive form
    private InteractiveEvoultionForm mainForm;
    //color selected by user
    private Color3f returnColor;
    ///zoom level set by user
    private float zoom;
    //render object
    private Render render;
    
    public preferencePanel(InteractiveEvoultionForm IF) {
        initComponents();
        
        //store the form 
        this.mainForm = IF;
        try {
            //render the example CA
            this.setZoomLevel();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(preferencePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(preferencePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfNumOfPixels = new javax.swing.JTextField();
        cbColours = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bClose = new javax.swing.JButton();
        bApply = new javax.swing.JButton();
        jZoomSlider = new javax.swing.JSlider();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pExample = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        tfNumOfPixels.setText("6");
        tfNumOfPixels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNumOfPixelsActionPerformed(evt);
            }
        });

        cbColours.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Green", "Red", "Yellow", "Blue" }));

        jLabel1.setText("Select Colour of Rendered Objects");

        jLabel4.setText("Number Of Pixels per Object Axis");

        jLabel3.setText("Zoom on Objects");

        bClose.setText("Close");
        bClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bCloseMousePressed(evt);
            }
        });

        bApply.setText("Apply");
        bApply.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bApplyMouseClicked(evt);
            }
        });

        jZoomSlider.setMaximum(10);
        jZoomSlider.setMinimum(0);
        jZoomSlider.setValue(5);
        jZoomSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jZoomSliderMouseClicked(evt);
            }
        });

        jLabel5.setText("Min ");

        jLabel6.setText("Max");

        pExample.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pExample.setMaximumSize(new java.awt.Dimension(75, 75));
        pExample.setPreferredSize(new java.awt.Dimension(75, 75));
        pExample.setLayout(new java.awt.GridLayout());

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(25, 25, 25)
                .add(bClose)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(bApply)
                .add(19, 19, 19))
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(40, 40, 40)
                        .add(jLabel3))
                    .add(layout.createSequentialGroup()
                        .add(57, 57, 57)
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jZoomSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel6))
                    .add(layout.createSequentialGroup()
                        .add(123, 123, 123)
                        .add(pExample, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 121, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(101, Short.MAX_VALUE))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .add(38, 38, 38)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel4)
                                .add(layout.createSequentialGroup()
                                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 224, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(cbColours, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                            .add(36, 36, 36)
                            .add(tfNumOfPixels, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(48, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .add(jLabel3)
                .add(34, 34, 34)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel6)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel5)
                            .add(jZoomSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(39, 39, 39)
                        .add(pExample, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(46, 46, 46)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(bApply)
                            .add(bClose))))
                .add(25, 25, 25))
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                    .add(45, 45, 45)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel4)
                        .add(tfNumOfPixels, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(18, 18, 18)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jLabel1)
                        .add(cbColours, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(368, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.setVisible(false);
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void bApplyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bApplyMouseClicked
        //make the changes required
  
        //retrevie the number of pixels
        this.mainForm.numOfPixels = Integer.parseInt(this.tfNumOfPixels.getText());
            
 
        //set the color
        this.setSelectedColor();        
        
        
       
        try {
            
            //set the zoom level
            this.setZoomLevel();
            
            //rerender the CAs on the main form
            this.mainForm.renderCAs();
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(preferencePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(preferencePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        
     
        
        
        //set the frame to invisable
        this.setVisible(false);
        
        
    }//GEN-LAST:event_bApplyMouseClicked

    private void tfNumOfPixelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNumOfPixelsActionPerformed
        //check to see its a number 
        
        if (this.validateInputTF(this.tfNumOfPixels.getText())==false) {
            JOptionPane.showMessageDialog(this,
                    "Numerical Field Only",
                    "Can only inout numbers to this field",
                    JOptionPane.INFORMATION_MESSAGE);
            
            
            
        }
        
        
    }//GEN-LAST:event_tfNumOfPixelsActionPerformed

    private void jZoomSliderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jZoomSliderMouseClicked
        try {
            //when the slider is clicked
            
            //re-set the zoom level and render the example panel
            this.setZoomLevel();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(preferencePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(preferencePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_jZoomSliderMouseClicked

    private void bCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bCloseMousePressed
        //close button pressed
        //set the form to invisable
        this.setVisible(false);
  
    }//GEN-LAST:event_bCloseMousePressed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
      //when the form is set to visable, re-render the CA
         
        
        try {         
            
            this.setZoomLevel();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(preferencePanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(preferencePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_formWindowActivated

    private boolean validateInputTF(String input){
        //validates the text input so that it only accepts
        //numbers
        
        try {
            
           int isNumber = Integer.parseInt(input); 
           
            if (isNumber >=0) {
                //is a number
                return true;
                
            }else{
                //isnt a number
                return false;
            }
           
            
        } catch (Exception e) {
            //the text entered isnt a nu,ner
            return false;
            
        }
            
        
    
    }
    
    
    private void setSelectedColor(){
    
     //decides what the color should be based on the users selection   
        
        if (cbColours.getSelectedItem().toString().matches("Red")) {
            
            //set the color to red
            this.returnColor = new Color3f(Color.RED);
           
        }else if(cbColours.getSelectedItem().toString().matches("Green")) {
            
            //set the color to red
            this.returnColor = new Color3f(Color.GREEN);
            
            
        }else if(cbColours.getSelectedItem().toString().matches("Yellow")) {
            
            //set the color to red
            this.returnColor = new Color3f(Color.YELLOW);
            
        }else if(cbColours.getSelectedItem().toString().matches("Blue")) {
            //set the color to red
            this.returnColor = new Color3f(Color.BLUE);
            
        }
        
        this.mainForm.selectedColor = returnColor;
        
    
    }
    
    
    private void setZoomLevel() throws FileNotFoundException, IOException{
    
    
        //get the value from the slider and cast to double
        this.zoom = (float) -(25 +jZoomSlider.getValue());

        
        //render the example panel
        this.pExample.removeAll();
          
        int renderX = this.pExample.getWidth();
        int renderY = this.pExample.getHeight();
        
        //redner the .STL file
        render = new Render("CA0", mainForm.defaultFilePath, 
                            renderX , renderY, this.zoom);
                      
        this.pExample.add(render.returnCanvas());
     
        
        //store the zoom level
        this.mainForm.Zoom = this.zoom;
    }
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(preferencePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(preferencePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(preferencePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(preferencePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
        
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bApply;
    private javax.swing.JButton bClose;
    private javax.swing.JComboBox cbColours;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSlider jZoomSlider;
    private javax.swing.JPanel pExample;
    private javax.swing.JTextField tfNumOfPixels;
    // End of variables declaration//GEN-END:variables
}
