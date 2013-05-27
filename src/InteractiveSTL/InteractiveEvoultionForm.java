/*
 * InteractiveEvoultionForm.java
 *
 * Created on Dec 11, 2011, 1:07:50 PM
 */
package InteractiveSTL;

import RenderCA.PickRender;
import java.awt.Color;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.vecmath.Color3f;


/**
 *
 * @author samjacobclift
 */
public class InteractiveEvoultionForm extends javax.swing.JFrame {

    //new CAGAFacade class
    private CAGAFacade facade;
     
    //store if the CA has been selected or not
    private int[] selectedCAs = new int[8];
    
    //default filepath where the files that are
    protected String defaultFilePath = "/Users/samjacobclift/Netbeans/i3FinalVersion/STLFILES/";
    
    //file chooser pop up
    private JFileChooser fc = new JFileChooser();;
    
    //pickrender objects that render the STL files
    private PickRender stlr1;  
    private PickRender stlr2; 
    private PickRender stlr3; 
    private PickRender stlr4; 
    private PickRender stlr5; 
    private PickRender stlr6; 
    private PickRender stlr7; 
    private PickRender stlr8;

    //new preference panel
    private preferencePanel preferencePanel;// = new preferencePanel(this);
   
    
    //get the size that the rendered objects should be
    private int renderX;
    private int renderY;
    
    //the zoom on the render objects
    public float Zoom = -30.f;
    
    //the color of the object when they are selected
    public Color3f selectedColor= new Color3f(Color.GREEN);
    
    //define the number of pixels in an objects axis
    public int numOfPixels =10;

    public boolean enableRotation = false;
    
    /** Creates new form InteractiveEvoultionForm */
    public InteractiveEvoultionForm() throws FileNotFoundException, IOException {
        
        initComponents();
        //initile the CAGA facade
        facade = new CAGAFacade(numOfPixels);
        //generate an inital population of CAs
        facade.generateIntialRandom(defaultFilePath);
        this.renderCAs();
        
        //create the prefrance panel
        preferencePanel  = new preferencePanel(this);
        
        
        
    }

    public final void renderCAs() throws FileNotFoundException, IOException{
    //renders the CAs from their respect file
         
        
        //get the height of each of the panels
        renderX = this.CA1.getWidth();
        renderY = this.CA1.getHeight();
        
        
         //render all the newly created CA's
          stlr1 = new PickRender("CA0", defaultFilePath, renderX , renderY,Zoom,selectedColor);  
          stlr2 = new PickRender("CA1", defaultFilePath, renderX , renderY,Zoom,selectedColor); 
          stlr3 = new PickRender("CA2", defaultFilePath, renderX , renderY,Zoom,selectedColor); 
          stlr4 = new PickRender("CA3", defaultFilePath, renderX , renderY,Zoom,selectedColor); 
          stlr5 = new PickRender("CA4", defaultFilePath, renderX , renderY,Zoom,selectedColor); 
          stlr6 = new PickRender("CA5", defaultFilePath, renderX , renderY,Zoom,selectedColor); 
          stlr7 = new PickRender("CA6", defaultFilePath, renderX , renderY,Zoom,selectedColor); 
          stlr8 = new PickRender("CA7", defaultFilePath, renderX , renderY,Zoom,selectedColor); 
         
          
          
         //clear the jpanels used to display
         //the rendered CA's
         CA1.removeAll();
         CA2.removeAll();
         CA3.removeAll();
         CA4.removeAll();
         CA5.removeAll();
         CA6.removeAll();
         CA7.removeAll();
         CA8.removeAll();
     
 
         //add them all to the jpanels
         CA1.add(stlr1.returnCanvas().getCanvas());
         CA2.add(stlr2.returnCanvas().getCanvas());       
         CA3.add(stlr3.returnCanvas().getCanvas());
         CA4.add(stlr4.returnCanvas().getCanvas());
         CA5.add(stlr5.returnCanvas().getCanvas());
         CA6.add(stlr6.returnCanvas().getCanvas());          
         CA7.add(stlr7.returnCanvas().getCanvas());
         CA8.add(stlr8.returnCanvas().getCanvas());          
 

         
    }
    
        
    
    private void generateCAs() throws FileNotFoundException, IOException{
    //generates an entire new generation of CAs
    //based on the users selection 
    //and then renders them to the GUI
    

    //set the selected boxes
    this.setSelected();  
    
    //generate a new generation of CAs based
    //on the users selection
    facade = new CAGAFacade(numOfPixels);
    facade.generateNewGeneration(selectedCAs , defaultFilePath);
    
    //render them 
    this.renderCAs();
    
    
    }
    
    
    private void setSelected(){
    //checks the chk boxes on the form
    //if they are selected then
    //mark them as so in the selected[] 
     
        
        //redefine selected to make sure that
        //old selections are not counted
        selectedCAs = new int[8];
     
        //store if any of the CAs have been picked
        if (this.stlr1.isPickedOrNot()) {
            this.selectedCAs[0] =1;
        }
        if (this.stlr2.isPickedOrNot()) {
            this.selectedCAs[1] =1;
        }
        if (this.stlr3.isPickedOrNot()) {
            this.selectedCAs[2] =1;
          
        }
        if (this.stlr4.isPickedOrNot()) {
            this.selectedCAs[3] =1;
    
        }
        if (this.stlr5.isPickedOrNot()) {
            this.selectedCAs[4] =1;

        }
        if (this.stlr6.isPickedOrNot()) {
            this.selectedCAs[5] =1;

        }
        if (this.stlr7.isPickedOrNot()) {
            this.selectedCAs[6] =1;

        }
        if (this.stlr8.isPickedOrNot()) {
            this.selectedCAs[7] =1;

        }
        
    
    }
    
    private int selectedCA(){
    //returns the number of the selected CA  
    //only used by the export button
        
        int selectedCA = 0;
        
        //loop though this.selectedCAs
        for (int i = 0; i < this.selectedCAs.length; i++) {
            if (this.selectedCAs[i]==1) {
                selectedCA = selectedCA +1;
            }

        }

        return selectedCA;  
    }
    
    
    private int isSelected(){
        //returns true if there is 1 CA selected 
        // on the form
        
        //if an item has been selected then this will
        //be greater than 0
        int totalSelected =  this.selectedCAs[0]
                            +this.selectedCAs[1]
                            +this.selectedCAs[2]
                            +this.selectedCAs[3]
                            +this.selectedCAs[4]
                            +this.selectedCAs[5]
                            +this.selectedCAs[6]
                            +this.selectedCAs[7];
        
        return totalSelected;  
        
        
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        CAPanel = new javax.swing.JPanel();
        topRowPanel = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        CA1 = new javax.swing.JPanel();
        panel2 = new javax.swing.JPanel();
        CA2 = new javax.swing.JPanel();
        panel3 = new javax.swing.JPanel();
        CA3 = new javax.swing.JPanel();
        panel4 = new javax.swing.JPanel();
        CA4 = new javax.swing.JPanel();
        bottomRowPanel = new javax.swing.JPanel();
        panel5 = new javax.swing.JPanel();
        CA5 = new javax.swing.JPanel();
        panel6 = new javax.swing.JPanel();
        CA6 = new javax.swing.JPanel();
        panel7 = new javax.swing.JPanel();
        CA7 = new javax.swing.JPanel();
        panel8 = new javax.swing.JPanel();
        CA8 = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        bGenerate = new javax.swing.JButton();
        bExport = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mProject = new javax.swing.JMenu();
        mPreferences = new javax.swing.JMenuItem();
        mAbout = new javax.swing.JMenu();
        menuAboutItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        org.jdesktop.layout.GroupLayout titlePanelLayout = new org.jdesktop.layout.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1120, Short.MAX_VALUE)
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 101, Short.MAX_VALUE)
        );

        getContentPane().add(titlePanel, java.awt.BorderLayout.PAGE_START);

        CAPanel.setLayout(new java.awt.GridLayout(2, 1));

        topRowPanel.setPreferredSize(new java.awt.Dimension(222, 105));
        topRowPanel.setLayout(new java.awt.GridLayout(1, 4));

        CA1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.jdesktop.layout.GroupLayout CA1Layout = new org.jdesktop.layout.GroupLayout(CA1);
        CA1.setLayout(CA1Layout);
        CA1Layout.setHorizontalGroup(
            CA1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 266, Short.MAX_VALUE)
        );
        CA1Layout.setVerticalGroup(
            CA1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 186, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout panel1Layout = new org.jdesktop.layout.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        topRowPanel.add(panel1);

        CA2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.jdesktop.layout.GroupLayout CA2Layout = new org.jdesktop.layout.GroupLayout(CA2);
        CA2.setLayout(CA2Layout);
        CA2Layout.setHorizontalGroup(
            CA2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 266, Short.MAX_VALUE)
        );
        CA2Layout.setVerticalGroup(
            CA2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 186, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout panel2Layout = new org.jdesktop.layout.GroupLayout(panel2);
        panel2.setLayout(panel2Layout);
        panel2Layout.setHorizontalGroup(
            panel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel2Layout.setVerticalGroup(
            panel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        topRowPanel.add(panel2);

        CA3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.jdesktop.layout.GroupLayout CA3Layout = new org.jdesktop.layout.GroupLayout(CA3);
        CA3.setLayout(CA3Layout);
        CA3Layout.setHorizontalGroup(
            CA3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 266, Short.MAX_VALUE)
        );
        CA3Layout.setVerticalGroup(
            CA3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 186, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout panel3Layout = new org.jdesktop.layout.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        topRowPanel.add(panel3);

        CA4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.jdesktop.layout.GroupLayout CA4Layout = new org.jdesktop.layout.GroupLayout(CA4);
        CA4.setLayout(CA4Layout);
        CA4Layout.setHorizontalGroup(
            CA4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 266, Short.MAX_VALUE)
        );
        CA4Layout.setVerticalGroup(
            CA4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 186, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout panel4Layout = new org.jdesktop.layout.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        topRowPanel.add(panel4);

        CAPanel.add(topRowPanel);

        bottomRowPanel.setLayout(new java.awt.GridLayout(1, 4));

        CA5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.jdesktop.layout.GroupLayout CA5Layout = new org.jdesktop.layout.GroupLayout(CA5);
        CA5.setLayout(CA5Layout);
        CA5Layout.setHorizontalGroup(
            CA5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 266, Short.MAX_VALUE)
        );
        CA5Layout.setVerticalGroup(
            CA5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 186, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout panel5Layout = new org.jdesktop.layout.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bottomRowPanel.add(panel5);

        CA6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.jdesktop.layout.GroupLayout CA6Layout = new org.jdesktop.layout.GroupLayout(CA6);
        CA6.setLayout(CA6Layout);
        CA6Layout.setHorizontalGroup(
            CA6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 266, Short.MAX_VALUE)
        );
        CA6Layout.setVerticalGroup(
            CA6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 186, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout panel6Layout = new org.jdesktop.layout.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bottomRowPanel.add(panel6);

        CA7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.jdesktop.layout.GroupLayout CA7Layout = new org.jdesktop.layout.GroupLayout(CA7);
        CA7.setLayout(CA7Layout);
        CA7Layout.setHorizontalGroup(
            CA7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 266, Short.MAX_VALUE)
        );
        CA7Layout.setVerticalGroup(
            CA7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 186, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout panel7Layout = new org.jdesktop.layout.GroupLayout(panel7);
        panel7.setLayout(panel7Layout);
        panel7Layout.setHorizontalGroup(
            panel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel7Layout.setVerticalGroup(
            panel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bottomRowPanel.add(panel7);

        CA8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        org.jdesktop.layout.GroupLayout CA8Layout = new org.jdesktop.layout.GroupLayout(CA8);
        CA8.setLayout(CA8Layout);
        CA8Layout.setHorizontalGroup(
            CA8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 266, Short.MAX_VALUE)
        );
        CA8Layout.setVerticalGroup(
            CA8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 186, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout panel8Layout = new org.jdesktop.layout.GroupLayout(panel8);
        panel8.setLayout(panel8Layout);
        panel8Layout.setHorizontalGroup(
            panel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel8Layout.setVerticalGroup(
            panel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(CA8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bottomRowPanel.add(panel8);

        CAPanel.add(bottomRowPanel);

        getContentPane().add(CAPanel, java.awt.BorderLayout.CENTER);

        bGenerate.setText("Combine Shapes");
        bGenerate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bGenerateMouseClicked(evt);
            }
        });

        bExport.setText("Export Selected Shapes");
        bExport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bExportMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout buttonPanelLayout = new org.jdesktop.layout.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(bExport, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1108, Short.MAX_VALUE)
                    .add(bGenerate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(bGenerate)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(bExport)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        getContentPane().add(buttonPanel, java.awt.BorderLayout.PAGE_END);

        mProject.setText("Project");

        mPreferences.setText("Preferences");
        mPreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mPreferencesActionPerformed(evt);
            }
        });
        mProject.add(mPreferences);

        jMenuBar1.add(mProject);

        mAbout.setText("About");

        menuAboutItem.setText("Details");
        mAbout.add(menuAboutItem);

        jMenuBar1.add(mAbout);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bGenerateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bGenerateMouseClicked
        
        //see if any shapes have been selected
        this.setSelected();
        
        //if there is none selected display warning to the user
        if (this.selectedCA() == 0) {
            
            JOptionPane.showMessageDialog(this,
                    "To Generate New Shapes You Must Select at Least 1",
                    "Error",
                    JOptionPane.INFORMATION_MESSAGE);
            
        }
        else{
        //more than one selected
        try {
        
            //generate the new shapes
            this.generateCAs();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InteractiveEvoultionForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InteractiveEvoultionForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
    }//GEN-LAST:event_bGenerateMouseClicked

    private void bExportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bExportMouseClicked
    //exports the selected CAs to a specfied file location
        
        this.setSelected();
        
        //if 0 or >1 CAs selected
        if (this.isSelected()!=1) {

            //inform the user that only one CA can be 
            //exported at a time
            JOptionPane.showMessageDialog(this,
                    "Only 1 Object may be exported at a time",
                    "Error - cannot export",
                    JOptionPane.INFORMATION_MESSAGE);

        } 
        else {
            //only 1 CA has been selected

            //set the file chooser to directorys only
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            //show the file chooser and if a approved option is selected
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                //get the filepath for the file
                String strNewFilePath = fc.getSelectedFile().getAbsolutePath();

                //get the selected file from the GUI
                File selectedFile = new File(defaultFilePath + "/CA"+this.selectedCA()+".stl");
                
                //construct the new file with eh filepath specfied by the user
                File newFilePath = new File(strNewFilePath + "/CA"+this.selectedCA()+".stl");
                
                //move the selected CA's .stl file to the selected location
                try{
                        FileInputStream in = new FileInputStream(selectedFile);
                        FileOutputStream out = new FileOutputStream(newFilePath);

                        byte[] buf = new byte[1024];
                        int i;
                            while ((i = in.read(buf)) != -1) {
                                out.write(buf, 0, i);
                            }
                        in.close();
                        out.close();
                } catch(IOException e) {
                    System.out.println("Error copying file");
                }

            }
            else{
            //if the user doesnt select a dir
                  JOptionPane.showMessageDialog(this,
                    "That is not a Directory",
                    "Error - cannot export",
                    JOptionPane.INFORMATION_MESSAGE);
            
            }
        }
  
    }//GEN-LAST:event_bExportMouseClicked

    
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         //when the program is closed delete all the files that have been wrote
        
        
        //new file object
        File delete;
        
        delete = new File(defaultFilePath +"/CA0.stl");
        delete.delete();
        
        delete = new File(defaultFilePath +"/CA1.stl");
        delete.delete();
        
        delete = new File(defaultFilePath +"/CA2.stl");
        delete.delete();
        
        delete = new File(defaultFilePath +"/CA3.stl");
        delete.delete();
        
        delete = new File(defaultFilePath +"/CA4.stl");
        delete.delete();
        
        delete = new File(defaultFilePath +"/CA5.stl");
        delete.delete();
        
        delete = new File(defaultFilePath +"/CA6.stl");
        delete.delete();
        
        delete = new File(defaultFilePath +"/CA7.stl");
        delete.delete();  
    }//GEN-LAST:event_formWindowClosing

    private void mPreferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mPreferencesActionPerformed
        //show the prefrance panel
        this.preferencePanel.setVisible(true);
    
    }//GEN-LAST:event_mPreferencesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InteractiveEvoultionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InteractiveEvoultionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InteractiveEvoultionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InteractiveEvoultionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    new InteractiveEvoultionForm().setVisible(true);
 
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(InteractiveEvoultionForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(InteractiveEvoultionForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
   
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CA1;
    private javax.swing.JPanel CA2;
    private javax.swing.JPanel CA3;
    private javax.swing.JPanel CA4;
    private javax.swing.JPanel CA5;
    private javax.swing.JPanel CA6;
    private javax.swing.JPanel CA7;
    private javax.swing.JPanel CA8;
    private javax.swing.JPanel CAPanel;
    private javax.swing.JButton bExport;
    private javax.swing.JButton bGenerate;
    private javax.swing.JPanel bottomRowPanel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu mAbout;
    private javax.swing.JMenuItem mPreferences;
    private javax.swing.JMenu mProject;
    private javax.swing.JMenuItem menuAboutItem;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel5;
    private javax.swing.JPanel panel6;
    private javax.swing.JPanel panel7;
    private javax.swing.JPanel panel8;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JPanel topRowPanel;
    // End of variables declaration//GEN-END:variables
}
