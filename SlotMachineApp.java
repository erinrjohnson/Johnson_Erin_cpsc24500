/**
 * @author: Erin Johnson
 * @description: In this project, you will create a slot machine that has four slots. The slots are shapes-
 * circles or squares. They can be yellow, green, orange, red or blue in color. The player will start with $5.
 * They can bet all their money by pressing the Max button, half their money by pressing the Mid
 * button, or just a tenth of their money by pressing the Min button.
 * 
 * reference source: MouseandMenuAPP done in class
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * the Tile class holds the shape and color codes and has get and set functions, two constructors, a setRandomly function
 * that changes the color and shape randomly, and a toString function???
 */
class Tile {
	private String shapeType; //for storing the shape information 
	private String shapeColor; //for storing the color information
	private int store;
	
	//need public methods for each private method in order to return the information and pass it between classes
	public int getstore() {
		return store;
	}
	//returns the color of the shape
	public String getColor() {
		return shapeColor;
	}
	//returns the type of shape
	public String getShape() {
		return shapeType;
	}
	
	public void setX(int x) {
		this.store = x;
	}
	
	public void setShapeColor(String color) {
		shapeColor = color;
	}
	
	public void setShapeType(String type) {
		shapeType = type;
	}
	//constructors
	public Tile() {
		store = 0;
		shapeColor = "YELLOW";
		shapeType = "circle";
	}
	public Tile(int xPos, String color, String type) {
		setX(xPos);
		setShapeColor(color);
		setShapeType(type);
	}
	//tile randomizer named setRandomly to alter shape type and shape color
	//color order according to assignment document: yellow, green, orange, red, or blue
	//need a series of if/else statements 0-3 for the ints of the colors 
	//also need if/else to store shape as being either circle or square, use ""
	public void setRandomly() {
		Random rand = new Random();
		int randType = rand.nextInt(2);
		int randShape = rand.nextInt(5);
		if (randShape == 0) {
			shapeColor = "YELLOW";
		} else if (randShape == 1) {
			shapeColor = "GREEN";
		} else if (randShape == 2) {
			shapeColor = "ORANGE";
		} else if (randShape == 3) {
			shapeColor = "RED";
		} else {
			shapeColor = "BLUE";
		}
		if (randType == 0) {
			shapeType = "Circle";
		} else {
			shapeType = "Square";
		}
	}
	
	//toString function to return comprehensive information grabbed from setRandomly
	public String toString() {
		return String.format("%d %s %s", store, shapeColor, shapeType);
	}
	
}
/**
 *the TilePanel class shows a list of four tiles and enables the user to change one of the tiles by clicking on it
 */
class TilePanel extends JPanel implements MouseListener, MouseMotionListener {
	private String mouseStatus;
	private ArrayList<Tile> tiles;
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	
	public TilePanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		tiles = new ArrayList<Tile>();
		tiles.add(new Tile(25, "YELLOW", "circle"));
		tiles.add(new Tile(225, "BLUE", "square"));
		tiles.add(new Tile(425, "RED", "square"));
		tiles.add(new Tile(625, "RED", "square"));
	}
	//handle shape and color graphics in paintComponent 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int tileL = 150;
		int tileH = 30;
		for (Tile tile: tiles) {
			if (tile.getShape().equals("circle")) {
				if (tile.getColor().equals("YELLOW")) {
					g.setColor(Color.YELLOW);
				} else if (tile.getColor().equals("GREEN")) {
					g.setColor(Color.GREEN);
				} else if (tile.getColor().equals("ORANGE")) {
					g.setColor(Color.ORANGE);
				} else if (tile.getColor().equals("RED")) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLUE);
				}
				g.fillOval(tile.getstore(), tileH, tileL, tileL);
			} else {
				if (tile.getShape().equals("YELLOW")) {
					g.setColor(Color.YELLOW);
				} else if (tile.getColor().equals("GREEN")) {
					g.setColor(Color.GREEN);
				} else if (tile.getColor().equals("ORANGE")) {
					g.setColor(Color.ORANGE);
				} else if (tile.getColor().equals("RED")) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.BLUE);
				}
				g.fillRect(tile.getstore(), tileH, tileL, tileL);
			}
		}
	}
//abstract mouse commands 
//follow example from class for help
	public String getMouseStatus() {
		return mouseStatus;
	}
	public void setMouseStatus(String ms) {
		mouseStatus = ms;
	}
	public void mouseEntered(MouseEvent e) { 
		mouseStatus = "Entered the window.";
		repaint();
	}
	public void mouseExited(MouseEvent e) {
		mouseStatus = "Exited the window.";
		repaint();
	}
	public void mousePressed(MouseEvent e) {
		mouseStatus = String.format("Mouse pressed at (%d, %d)", e.getX(), e.getY());
		repaint();
	}
	public void mouseReleased(MouseEvent e) {
		mouseStatus = String.format("Mouse released at (%d, %d)", e.getX(), e.getY());
		repaint();
	}
	public void mouseClicked(MouseEvent e) { 
		//need to account for each set of circumstances with the shapes and colors 
		mouseStatus = String.format("Mouse clicked at (%d, %d)", e.getX(), e.getY());
		if ((e.getX() >= 25 && e.getX() <= 175) && (e.getY() >= 25 && e.getY() <= 175)) {
			tiles.get(0).setRandomly();
			System.out.println(tiles.get(0));
		} 
		
		if ((e.getX() >= 225 && e.getX() <= 375) && (e.getY() >= 25 && e.getY() <= 175)) {
			tiles.get(1).setRandomly();
		}
		
		if ((e.getX() >= 425 && e.getX() <= 575) && (e.getY() >= 25 && e.getY() <= 175)) {
			tiles.get(2).setRandomly();
		}
		
		if ((e.getX() >= 625 && e.getX() <= 775) && (e.getY() >= 25 && e.getY() <= 175)) {
			tiles.get(3).setRandomly();
		}
		repaint();
	}
	public void mouseMoved(MouseEvent e) { 
		mouseStatus = String.format("Mouse moved at (%d, %d)", e.getX(), e.getY());
		repaint();
		
	}
	public void mouseDragged(MouseEvent e) { 
		mouseStatus = String.format("Mouse dragged at (%d, %d)", e.getX(), e.getY());
		repaint();
	}
}
/**
 * the TileWriter class writes the tiles to a file in text, binary, or XML format, based on the file's extension
 */
class TileWriter {
	public boolean writeToText(String fname, ArrayList<Tile> tiles) {//array list
		File f = new File(fname);
		return writeToText(f, tiles);
	}
	
	public boolean writeToText(File f, ArrayList<Tile> tiles) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			for (Tile tile: tiles) {
				pw.println(tile);
			}
			pw.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}
/**
 *uhhh not yet
 */
class TileChecker{	
	
}

/**
 *the TileReader class reads the tiles from a file in text, binary, or XML format
 */
class TileReader {
	public ArrayList<Tile> readFromText(String fname) {
		File f = new File(fname);
		return readFromText(f);
	}
	
	public ArrayList<Tile> readFromText(File f) {
		try {
			ArrayList<Tile> tilesRead = new ArrayList<Tile>();
			Scanner fsc = new Scanner(f);
			String line;
			String[] parts;
			int x;
			String color, type;
			Tile tile;
			while (fsc.hasNextLine()) {
				line = fsc.nextLine();
				parts = line.split(" ");
				x = Integer.parseInt(parts[0]);
				color = parts[1];
				type = parts[2];
				tile = new Tile(x, color, type);
				tilesRead.add(tile);
			}
			fsc.close();
			return tilesRead;
		} catch (Exception ex) {
			return null;
		}
	}
}

/**
 *the SlotMachineFrame follows the MenuandMouseFrame code and has the following components:
 *a SlotMachineFrame with three buttons, a "$" label, and a JTextField that shows the
 *user's balance in the south panel, a TilePanel in the Center, and a main menu that has
 *"Load Tiles", "Save Tiles", "Print", "Restart", and "Exit" options under File, and "About"
 *under Help, which will open a JOptionPane that shows your name and the URL of the
 *github project. Attach ActionListeners to Load Tiles, Save Tiles, Exit, and Help
 */
class SlotMachineFrame extends JFrame{
	private TilePanel pan;
	public void setupMenu() {
		JMenuBar mbar = new JMenuBar();
		JMenu mnuFile = new JMenu("File");
		mbar.add(mnuFile);
		
		JMenuItem miLoadTiles = new JMenuItem("Load Tiles");
		miLoadTiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				TileReader tr;
				ArrayList<Tile> tilesRead;
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					tr = new TileReader();
					tilesRead = tr.readFromText(jfc.getSelectedFile());
					if (tilesRead == null) {
						JOptionPane.showMessageDialog(null, "Could not read tiles.");
					} else {
						pan.setTiles(tilesRead);
						repaint();
					}
				}
			}
			
		});
		mnuFile.add(miLoadTiles);
		
		JMenuItem miSaveTiles = new JMenuItem("Save Tiles");
		miSaveTiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				TileWriter tw;
				if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					tw = new TileWriter();
					if (tw.writeToText(jfc.getSelectedFile(), pan.getTiles())) {
						JOptionPane.showMessageDialog(null, "Tiles were written.");
					} else {
						JOptionPane.showMessageDialog(null, "Tiles could not be written.");
					}
				}
				
			}
		});
		mnuFile.add(miSaveTiles);
		
		JMenuItem miPrint = new JMenuItem("Print");
		miPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnuFile.add(miPrint);
		
		JMenuItem miRestart = new JMenuItem("Restart");
		miRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnuFile.add(miRestart);
		
		JMenuItem miExit = new JMenuItem("Exit");
		miExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnuFile.add(miExit);
		
		JMenu mnuHelp = new JMenu("Help");
		mbar.add(mnuHelp);
		JMenuItem miAbout = new JMenuItem("About");
		miAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Erin Johnson, https://github.com/erinrjohnson/Johnson_Erin_cpsc24500");
			}
		});
		mnuHelp.add(miAbout);
		setJMenuBar(mbar);
		
	}
	
	public void setupLook() { 
		Double userAmount = 5.00;
		//setBounds of app frame
		setBounds(100,100,800,500);//l,t,w,h
		setTitle("Vegas Baby Vegas Slot Machine");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		pan = new TilePanel();
		c.add(pan, BorderLayout.CENTER);
		JPanel panSouth = new JPanel();
		panSouth.setLayout(new FlowLayout());
		JButton btnMax = new JButton("Max");
		panSouth.add(btnMax);
		JButton btnMid = new JButton("Mid");
		panSouth.add(btnMid);
		JButton btnMin = new JButton("Min");
		panSouth.add(btnMin);
		panSouth.add(new JLabel("$"));
		JTextField Money = new JTextField(3);
		Money.setText(userAmount.toString()); 
	    panSouth.add(Money);
		c.add(panSouth, BorderLayout.SOUTH);
		setupMenu();
	}
	
	public SlotMachineFrame() {
		setupLook();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

/**
 * main body
 */
public class SlotMachineApp {
	public static void main(String[] args) {
		SlotMachineFrame frm = new SlotMachineFrame(); 
		frm.setVisible(true);
		frm.setLocationRelativeTo(null);
	}

}
