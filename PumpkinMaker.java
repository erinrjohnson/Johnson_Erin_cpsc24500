/**
 * @author: Erin Johnson
 * @description: In this assignment, you will create a tool perfect for the season: 
 * one that draws a custom pumpkin image on the screen. This is called Pumpkin Maker. 
 * It allows you to set the position, width and height, eye shape, nose shape, and mouth shape 
 * of the pumpkin you want to make.
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.Graphics;

public class PumpkinMaker extends JPanel {
	static int frameWidth = 800;
	static int frameHeight = 480;
	
	static int div=2;
	
	static int pumpkinIntialX = 200;
	static int pumpkinIntialY = 100;
	static int pumpkinIntialW = 100;
	static int pumpkinIntialH = 100;
	
	static String noseShape = "S";
	static String eyeShape = "O";
	static String mouthShape = "O";

/**
 * Graphics portion that corresponds to appearance of pumpkin
 * This block of code deals with the drawing of the stem, eyes, mouth, and nose	
 */
    public void paintComponent(Graphics g) {
    	
    	int stemIntialX = pumpkinIntialX+43;
    	int stemIntialY = pumpkinIntialY-17;
    	int stemIntialW = pumpkinIntialW-85;
    	int stemIntialH = pumpkinIntialW-82;
    	
    	int eye1IntialX = pumpkinIntialX+20;
    	int eye1IntialY = pumpkinIntialY+20;
    	int eye1IntialW = pumpkinIntialW-80;
    	int eye1IntialH = pumpkinIntialH-80;
    	
    	int eye2IntialX = pumpkinIntialX+55;
    	int eye2IntialY = pumpkinIntialY+20;
    	int eye2IntialW = pumpkinIntialW-80;
    	int eye2IntialH = pumpkinIntialH-80;
    	
    	int mouthIntialX = pumpkinIntialX+28;
    	int mouthIntialY = pumpkinIntialY+60;
    	int mouthIntialW = pumpkinIntialW-60;
    	int mouthIntialH = pumpkinIntialH-85;
    	
    	int noseIntialX = pumpkinIntialX+40;
    	int noseIntialY = pumpkinIntialY+40;
    	int noseIntialW = pumpkinIntialW-85;
    	int noseIntialH = pumpkinIntialH-85;
    	
    	
    	int[] noseTriangleXPoints = {pumpkinIntialX+17,pumpkinIntialX+27,pumpkinIntialX+37};
    	int[] noseTriangleYPoints = {pumpkinIntialY+33,pumpkinIntialY+13,pumpkinIntialY+33};
    	int triangleNumPoints = 3;
    	
    	int[] eye1TriangleXPoints = {pumpkinIntialX+37,pumpkinIntialX+47,pumpkinIntialX+57};
    	int[] eye1TriangleYPoints = {pumpkinIntialY+53,pumpkinIntialY+33,pumpkinIntialY+53};
  
    	int[] eye2TriangleXPoints = {pumpkinIntialX+57,pumpkinIntialX+67,pumpkinIntialX+77};
    	int[] eye2TriangleYPoints = {pumpkinIntialY+33,pumpkinIntialY+13,pumpkinIntialY+33};
    	
    	// Create white background
    	g.clearRect(0, 0,frameWidth, frameHeight);
    	
    	
    	g.create(0, 0,frameWidth, frameHeight);
    	// Color Pumpkin
    	g.setColor(Color.orange);
    	g.fillOval(pumpkinIntialX,pumpkinIntialY, pumpkinIntialW, pumpkinIntialH);
    	//Color Stem
    	g.setColor(Color.white);
    	g.fillRect(stemIntialX,stemIntialY, stemIntialW, stemIntialH);
    	// draw frame of pumpkin and stem
    	g.setColor(Color.black);
    	g.drawOval(pumpkinIntialX,pumpkinIntialY, pumpkinIntialW, pumpkinIntialH);
    	g.drawRect(stemIntialX,stemIntialY, stemIntialW, stemIntialH);
/**
 * Provide a series of if & else statements that will allow the user to make a choice on eye shape, 
 * nose shape, mouth shape    	
 */
    	if(eyeShape == "O") {
    		g.setColor(Color.white);
    		g.fillOval(eye1IntialX,eye1IntialY, eye1IntialW, eye1IntialH);
    		g.fillOval(eye2IntialX,eye2IntialY, eye2IntialW, eye2IntialH);
    		g.setColor(Color.black);
    		g.drawOval(eye1IntialX,eye1IntialY, eye1IntialW, eye1IntialH);
    		g.drawOval(eye2IntialX,eye2IntialY, eye2IntialW, eye2IntialH);
    		
    	}
    	else if(eyeShape == "S") {
    		g.setColor(Color.white);
    		g.fillRect(eye1IntialX,eye1IntialY, eye1IntialW, eye1IntialH);
    		g.fillRect(eye2IntialX,eye2IntialY, eye2IntialW, eye2IntialH);
    		g.setColor(Color.black);
    		g.drawRect(eye1IntialX,eye1IntialY, eye1IntialW, eye1IntialH);
    		g.drawRect(eye2IntialX,eye2IntialY, eye2IntialW, eye2IntialH);
    	}
    	//need to do
    	else{
    		g.setColor(Color.white);
    		g.fillPolygon(eye1TriangleXPoints, eye1TriangleYPoints, triangleNumPoints);
    		g.fillPolygon(eye2TriangleXPoints, eye2TriangleYPoints, triangleNumPoints);
    		g.setColor(Color.black);
    		g.drawPolygon(eye1TriangleXPoints, eye1TriangleYPoints, triangleNumPoints);
    		g.drawPolygon(eye2TriangleXPoints, eye2TriangleYPoints, triangleNumPoints);

    	}
    	if(noseShape == "O") {
    		g.setColor(Color.white);
    		g.fillOval(noseIntialX,noseIntialY, noseIntialW, noseIntialH);
    		g.setColor(Color.black);
    		g.drawOval(noseIntialX,noseIntialY, noseIntialW, noseIntialH);
    	}
    	else if(noseShape == "S") {
    		g.setColor(Color.white);
    		g.fillRect(noseIntialX,noseIntialY, noseIntialW, noseIntialH);
    		g.setColor(Color.black);
    		g.drawRect(noseIntialX,noseIntialY, noseIntialW, noseIntialH);
    	}
    	//need to do
    	else{
    		g.setColor(Color.white);
    		g.fillPolygon(noseTriangleXPoints, noseTriangleYPoints, triangleNumPoints);
    		g.setColor(Color.black);
    		g.drawPolygon(noseTriangleXPoints, noseTriangleYPoints, triangleNumPoints);
    	}
    	
    	if(mouthShape == "O") {
    		g.setColor(Color.white);
    		g.fillOval(mouthIntialX,mouthIntialY, mouthIntialW, mouthIntialH);
    		g.setColor(Color.black);
    		g.drawOval(mouthIntialX,mouthIntialY, mouthIntialW, mouthIntialH);
    	}
    	//need to do
    	else {
    		g.setColor(Color.white);
    		g.fillRect(mouthIntialX,mouthIntialY, mouthIntialW, mouthIntialH);
    		g.setColor(Color.black);
    		g.drawRect(mouthIntialX,mouthIntialY, mouthIntialW, mouthIntialH);
    		System.out.print("WHY");
    		
    	}
       	}
    	
    /**
	  * @param args
	  * @throws Exception
	  * this is the main body of code which  performs necessary actions to fit the program's description
	  */   

    public static void main(String [] args) {
    	
    	/**
    	 * Main part of JFrame appearance
    	 */
    	
    	JFrame Frame = new JFrame("Pumpkin");
    	Frame.setSize(frameWidth,frameHeight);
    	PumpkinMaker Pumpkin = new PumpkinMaker();
    	//JPanel PumpkingPanel = new JPanel();
    	JPanel panSouth= new JPanel();
    	
    	panSouth.setLayout(new FlowLayout());
    	//PumpkingPanel.setLayout(new FlowLayout());
    	panSouth.add(new JLabel("Left: "));
    	JTextField textLeft = new JTextField(3);
    	panSouth.add(textLeft);
    	
    	panSouth.add(new JLabel("Top: "));
    	JTextField textTop = new JTextField(3);
    	panSouth.add(textTop);
    	
    	panSouth.add(new JLabel("Width: "));
    	JTextField textWidth = new JTextField(3);
    	panSouth.add(textWidth);
    	
    	panSouth.add(new JLabel("Height: "));
    	JTextField textHeight = new JTextField(3);
    	panSouth.add(textHeight);
    	
    	panSouth.add(new JLabel("Eye (C S T) "));
    	JTextField textOjos = new JTextField(1);
    	panSouth.add(textOjos);
    	
    	panSouth.add(new JLabel("Nose (C S T) "));
    	JTextField textNose = new JTextField(1);
    	panSouth.add(textNose);
    	
    	panSouth.add(new JLabel("Mouth (O R) "));
    	JTextField textMouth = new JTextField(1);
    	panSouth.add(textMouth);
    	
    	
    	JButton btnChange= new JButton("DRAW");
        panSouth.add(btnChange);
        btnChange.addActionListener(new ActionListener() {
        	

			public void actionPerformed(ActionEvent e) {
				/**
				 * Corresponds to the first portion of my code and subsequently displays all parts of the pumpkin
				 */
				try {
					
				PumpkinMaker Pumpkin = new PumpkinMaker();
        		int pLft = Integer.parseInt(textLeft.getText());
        		pumpkinIntialX=pLft;
        		int pTop = Integer.parseInt(textTop.getText());
        		pumpkinIntialY=pTop;
        		int pWidth = Integer.parseInt(textWidth.getText());
        		pumpkinIntialW = pWidth;
        		int pHeight = Integer.parseInt(textHeight.getText());
        		pumpkinIntialH = pHeight;
        		String pOjos = textOjos.getText();
        		eyeShape = pOjos;
        		String pNose = textNose.getText();
        		noseShape = pNose;
        		String pMouth = textMouth.getText();
        		mouthShape =pMouth;
        		Frame.repaint();
        		JOptionPane.showMessageDialog(null, pLft + ", " + pTop + ", " + pWidth + ", " + pHeight + ", " + pOjos + ", " + pNose + ", " + pMouth);
				} 
				/**
				 * Need to catch bad input from the user
				 */
				catch (Exception ex){
					
					JOptionPane.showMessageDialog(null,"Enter an integer in the first four boxes. Enter respective characters in the last three boxes.");
					
				}
        		
     
        	}

        });
        
       
    	Frame.add(Pumpkin,BorderLayout.CENTER);
    	Frame.add(panSouth,BorderLayout.SOUTH);
    	Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	Frame.setVisible(true);
    	//repaint();
    }
    
	
}