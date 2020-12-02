/**
 * @author: Erin Johnson
 * @description: In this project, you will create a slot machine that has four slots. The slots are shapes-
 * circles or squares. They can be yellow, green, orange, red or blue in color. The player will start with $5.
 * They can bet all their money by pressing the Max button, half their money by pressing the Mid
 * button, or just a tenth of their money by pressing the Min button.
 * 
 * reference source: MouseandMenuAPP done in class
 */
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
	public Tile(int color, int type) {
		store = 0;
		shapeColor = "YELLOW";
		shapeType = "circle";
	}
	public Tile(int xPos, String color, String type) {
		setX(xPos);
		setShapeColor(color);
		setShapeType(type);
	}
	
	public String setRandomlyColor() {
		Random rand = new Random();
		int randShape = rand.nextInt(5);
		String shapeColor;
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
		
		return shapeColor;
	}
	
	public String  setRandomlyType() {
		Random rand = new Random();
		int randType = rand.nextInt(2);
		
		String shapeType;
		if (randType == 1) {
			shapeType = "circle";
		} 
		else {
			shapeType = "square";
		}
		
		return shapeType;
	}

	
	//toString function to return comprehensive information grabbed from setRandomly
	public String toString() {
		return String.format("%d %s %s", store, shapeColor, shapeType);
	}
	public void setRandomly(Random rand) {
		// TODO Auto-generated method stub
		
	}
	
}
/**
 *the TilePanel class shows a list of four tiles and enables the user to change one of the tiles by clicking on it
 */
class TilePanel extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	private String mouseStatus;
	private ArrayList<Tile> tiles;
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	//tile randomizer named setRandomly to alter shape type and shape color
		//color order according to assignment document: yellow, green, orange, red, or blue
		//need a series of if/else statements 0-3 for the ints of the colors 
		//also need if/else to store shape as being either circle or square, use ""
		public String setRandomlyColor() {
			Random rand = new Random();
			int randShape = rand.nextInt(5);
			String shapeColor;
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
			
			return shapeColor;
		}
		
		public String  setRandomlyType() {
			Random rand = new Random();
			int randType = rand.nextInt(2);
			
			String shapeType;
			if (randType == 1) {
				shapeType = "circle";
			} 
			else {
				shapeType = "square";
			}
			
			return shapeType;
		}
	public TilePanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
		tiles = new ArrayList<Tile>();
		tiles = new ArrayList<Tile>();
		tiles.add(new Tile(25, setRandomlyColor(), setRandomlyType()));
		tiles.add(new Tile(225, setRandomlyColor(),setRandomlyType()));
		tiles.add(new Tile(425, setRandomlyColor(), setRandomlyType()));
		tiles.add(new Tile(625, setRandomlyColor(), setRandomlyType()));
		
	}
	public void slotSpin(int numSlotSpin) {
		if(numSlotSpin==4) {
		tiles.get(0).setShapeColor(setRandomlyColor());
		tiles.get(0).setShapeType(setRandomlyType());
		tiles.get(1).setShapeColor(setRandomlyColor());
		tiles.get(1).setShapeType(setRandomlyType());
		tiles.get(2).setShapeColor(setRandomlyColor());
		tiles.get(2).setShapeType(setRandomlyType());
		tiles.get(3).setShapeColor(setRandomlyColor());
		tiles.get(3).setShapeType(setRandomlyType());}
		else if(numSlotSpin==3) {
			tiles.get(2).setShapeColor(setRandomlyColor());
			tiles.get(2).setShapeType(setRandomlyType());
			tiles.get(1).setShapeColor(setRandomlyColor());
			tiles.get(1).setShapeType(setRandomlyType());
			tiles.get(0).setShapeColor(setRandomlyColor());
			tiles.get(0).setShapeType(setRandomlyType());}
		else if(numSlotSpin==2) {
			tiles.get(1).setShapeColor(setRandomlyColor());
			tiles.get(1).setShapeType(setRandomlyType());
			tiles.get(0).setShapeColor(setRandomlyColor());
			tiles.get(0).setShapeType(setRandomlyType());}
		else {

			tiles.get(0).setShapeColor(setRandomlyColor());
			tiles.get(0).setShapeType(setRandomlyType());}
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
			tiles.get(0).setShapeColor(setRandomlyColor());
			tiles.get(0).setShapeType(setRandomlyType());
			
			System.out.println(tiles.get(0));
		} 
		
		if ((e.getX() >= 225 && e.getX() <= 375) && (e.getY() >= 25 && e.getY() <= 175)) {
			tiles.get(1).setShapeColor(setRandomlyColor());
			tiles.get(1).setShapeType(setRandomlyType());
		}
		
		if ((e.getX() >= 425 && e.getX() <= 575) && (e.getY() >= 25 && e.getY() <= 175)) {
			tiles.get(2).setShapeColor(setRandomlyColor());
			tiles.get(2).setShapeType(setRandomlyType());
		}
		
		if ((e.getX() >= 625 && e.getX() <= 775) && (e.getY() >= 25 && e.getY() <= 175)) {
			tiles.get(3).setShapeColor(setRandomlyColor());
			tiles.get(3).setShapeType(setRandomlyType());
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
	/**
	 * This function writes tiles to a text file.
	 * @param fname the name of the file
	 * @param tiles the list of tiles to write
	 * @return true if successful, false if an exception happens
	 */
	public boolean writeToText(String fname, ArrayList<Tile> tiles) {
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
	
	/**
	 * This function writes tiles to a binary file.
	 * @param fname name of binary file
	 * @param tiles tiles the list of tiles to write
	 * @return true if successful, false if an exception happens
	 */
	public boolean writeToBinary(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return writeToBinary(f, tiles);
	}
	public boolean writeToBinary(File f, ArrayList<Tile> tiles) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(tiles);
			oos.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	/**
	 * This function writes tiles to a xml file.
	 * @param fname name of xml file 
	 * @param tiles tiles the list of tiles to write
	 * @return true if successful, false if an exception happens
	 */
	public boolean writeToXML(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return writeToXML(f, tiles);
	}
	public boolean writeToXML(File f, ArrayList<Tile> tiles) {
		try {
			XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)));
			enc.writeObject(tiles);
			enc.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public boolean write(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return write(f, tiles);
	}
	
	/**
	 * This writes tile data to whatever file format the user wants
	 * based on the extension of the file's name
	 * .txt - text
	 * .bin = binary
	 * .xml - xml
	 * @param f the file object
	 * @param tiles the list of tiles to write
	 * @return true if successful, false otherwise
	 */
	public boolean write(File f, ArrayList<Tile> tiles) {
		try {
			String fname = f.getName().toUpperCase();
			if (fname.endsWith(".TXT")) {
				return writeToText(f, tiles);
			} else if (fname.endsWith(".BIN")) {
				return writeToBinary(f, tiles);
			} else if (fname.endsWith(".XML")) {
				return writeToXML(f, tiles);
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}
}

/**
 *the TileReader class reads the tiles from a file in text, binary, or XML format
 */
class TileReader {
	public ArrayList<Tile> readFromText(String fname) {
		File f = new File(fname);
		return readFromText(f);
	}
	//read in the case of text
	public ArrayList<Tile> readFromText(File f) {
		try {
			ArrayList<Tile> tilesRead = new ArrayList<Tile>();
			Scanner fsc = new Scanner(f);
			String line;
			String[] parts;
			int color, type;
			Tile tile;
			while (fsc.hasNextLine()) {
				line = fsc.nextLine();
				parts = line.split(" ");
				color = Integer.parseInt(parts[0]);
				type = Integer.parseInt(parts[1]);
				tile = new Tile(color, type);
				tilesRead.add(tile);
			}
			fsc.close();
			return tilesRead;
		} catch (Exception ex) {
			return null;
		}
	}
	public ArrayList<Tile> readFromBinary(String fname) {
		File f = new File(fname);
		return readFromBinary(f);
	}
	//read in the case of binary
	@SuppressWarnings("unchecked")
	public ArrayList<Tile> readFromBinary(File f) {
		try {
			ArrayList<Tile> tilesRead;
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			tilesRead = (ArrayList<Tile>)ois.readObject();
			ois.close();
			return tilesRead;
		} catch (Exception ex) {
			return null;
		}
	}
	public ArrayList<Tile> readFromXML(String fname) {
		File f = new File(fname);
		return readFromXML(f);
	}
	//read in the case of XML
	@SuppressWarnings("unchecked")
	public ArrayList<Tile> readFromXML(File f) {
		try {
			ArrayList<Tile> tilesRead;
			XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)));
			tilesRead = (ArrayList<Tile>)dec.readObject();
			dec.close();
			return tilesRead;
		} catch (Exception ex) {
			return null;
		}
	}
	//hub of if/else statements choosing which to call based on file extension
	public ArrayList<Tile> read(String fname) {
		File f = new File(fname);
		return read(f);
	}
	public ArrayList<Tile> read(File f) {
		try {
			String fname = f.getName().toUpperCase();
			if (fname.endsWith(".TXT")) {
				return readFromText(f);
			} else if (fname.endsWith(".BIN")) {
				return readFromBinary(f);
			} else if (fname.endsWith(".XML")) {
				return readFromXML(f);
			} else {
				return null;
			}
		} catch (Exception ex) {
			return null;
		}
	}
}

/**
 *the TileChecker class checks for matches 
 *it will detect for winning combinations in both shapes and colors or just in colors
 */

class TileChecker {
	//no match 
	//colors of the shapes match
	///shapes AND colors match
	public int tilesCheck(ArrayList<Tile> tiles) {
		int matchType = 2; //in the condition that all tiles are matching-- have to start with BOTH conditions being met
		for (int i = 1; i < tiles.size(); i ++) { 
			 if (tiles.get(0).getColor() != tiles.get(i).getColor()) { //colors don't match
				 matchType = 0; //no match condition
			//need an else in the case that colors match
			 } else { 
				 //shapes do not match
				 if(matchType ==2) {
				 if (tiles.get(0).getShape() != tiles.get(i).getShape()) {
					 matchType = 1; 
				 }}
			 }
		}
		System.out.println(matchType);
		return matchType;
	}


}

/**
 * the TileRadomizer randomizes the four tiles
 * it will randomize the slots whenever the user clicks on the Max, Mid, or Min buttons, and also randomize it
 * at the very beginning of the game so that the TilePanel shows a random arrangement at the start
 */
class TileRandomizer {
	public void randomizeTiles(ArrayList<Tile> tiles, int numSlotSpin) {
		
		if(numSlotSpin==4) {
			tiles.get(0).setShapeColor(tiles.get(0).setRandomlyColor());
			tiles.get(0).setShapeType(tiles.get(0).setRandomlyType());
			tiles.get(1).setShapeColor(tiles.get(1).setRandomlyColor());
			tiles.get(1).setShapeType(tiles.get(1).setRandomlyType());
			tiles.get(2).setShapeColor(tiles.get(2).setRandomlyColor());
			tiles.get(2).setShapeType(tiles.get(2).setRandomlyType());
			tiles.get(3).setShapeColor(tiles.get(3).setRandomlyColor());
			tiles.get(3).setShapeType(tiles.get(3).setRandomlyType());}
			else if(numSlotSpin==3) {
				tiles.get(2).setShapeColor(tiles.get(2).setRandomlyColor());
				tiles.get(2).setShapeType(tiles.get(2).setRandomlyType());
				tiles.get(1).setShapeColor(tiles.get(1).setRandomlyColor());
				tiles.get(1).setShapeType(tiles.get(1).setRandomlyType());
				tiles.get(0).setShapeColor(tiles.get(0).setRandomlyColor());
				tiles.get(0).setShapeType(tiles.get(0).setRandomlyType());}
			else if(numSlotSpin==2) {
				tiles.get(1).setShapeColor(tiles.get(1).setRandomlyColor());
				tiles.get(1).setShapeType(tiles.get(1).setRandomlyType());
				tiles.get(0).setShapeColor(tiles.get(0).setRandomlyColor());
				tiles.get(0).setShapeType(tiles.get(0).setRandomlyType());}
			else {

				tiles.get(0).setShapeColor(tiles.get(0).setRandomlyColor());
				tiles.get(0).setShapeType(tiles.get(0).setRandomlyType());}
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
	private static final long serialVersionUID = 1L;
	//use TilePanel to display tiles
	private TilePanel pan = new TilePanel();
	private Container c = getContentPane();
	//use TileChecker to check tiles for matching shapes and colors
	private TileChecker tc; 
	//use TileRandomizer to randomize all the tiles on the panel when buttons are clicked
	private TileRandomizer tr;
	//need a random variable
	private Random rand;
	
	//set up buttons
	private JButton btnMax;
	private JButton btnMid;
	private JButton btnMin;
	
	//set up balance text field as well as user amount
	private JTextField txtBalance;
	private double userAmount = 5.00;
	
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
				//need to reset balance
				userAmount = 5.00;
				txtBalance.setText(String.format("%.2f", userAmount));
				//random
				quickSlotRoll(4);
				//need to reset buttons
				btnMax.setEnabled(true);
				btnMid.setEnabled(true);
				btnMin.setEnabled(true);
			}
		});
		
		mnuFile.add(miRestart);
		JMenuItem miExit = new JMenuItem("Exit");
		miExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
	
	/**
	 * call function to set up the appearance of the frame
	 */
	public void setupLook() { 
		//setBounds of app frame
		setBounds(100,100,800,500);//l,t,w,h
		//set title
		setTitle("Vegas Baby Vegas Slot Machine");
		c.setLayout(new BorderLayout());
		pan = new TilePanel(); // sets up tile panel
		c.add(pan, BorderLayout.CENTER);
		// sets up bottom bar with max, mid, and min buttons
		JPanel panSouth = new JPanel();
		panSouth.setLayout(new FlowLayout());
		btnMax = new JButton("Max");
		panSouth.add(btnMax);
		btnMid = new JButton("Mid");
		panSouth.add(btnMid);
		btnMin = new JButton("Min");
		panSouth.add(btnMin);
		panSouth.add(new JLabel("$"));
		txtBalance = new JTextField(6);
		txtBalance.setText(String.format("%.2f", userAmount)); // sets initial balance
		panSouth.add(txtBalance);
		c.add(panSouth, BorderLayout.SOUTH);
		
		//balance setup
		userAmount = Double.parseDouble(txtBalance.getText());
		
		//type of bet
		//max (all shapes and colors)- 100 times wagered
		//max (just colors) - 25 times wagered
		//mid (all shapes and colors)- 50 times wagered
		//mid (just colors)- 10 times wagered
		//min (all shapes and colors) - 10 times wagered
		//min (just colors)- 5 times wagered
		
		btnMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					tr.randomizeTiles(pan.getTiles(), 4);
					repaint();
				if (tc.tilesCheck(pan.getTiles()) == 0) { //no match
					txtBalance.setText(String.format("%.2f", 0.00));
					btnMax.setEnabled(false); // disable buttons when user runs out of $
					btnMid.setEnabled(false);
					btnMin.setEnabled(false);
				} else if (tc.tilesCheck(pan.getTiles()) == 1) { //only colors match
					userAmount = userAmount*25;
					txtBalance.setText(String.format("%.2f", userAmount));
				} else { //colors and shapes match
					userAmount = userAmount*100;
					txtBalance.setText(String.format("%.2f", userAmount));
				}
			}
		});

		btnMid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 double bet=(0.5*userAmount);
				 
				 
					tr.randomizeTiles(pan.getTiles(), 4);
					repaint();
				if (tc.tilesCheck(pan.getTiles()) == 0) {//no match
					userAmount -= bet;
					txtBalance.setText(String.format("%.2f", userAmount));
					if (Double.parseDouble(txtBalance.getText()) == 0.00) {//disable buttons when user runs out of $
						btnMax.setEnabled(false);
						btnMid.setEnabled(false);
						btnMin.setEnabled(false);
					
					}
				} else if (tc.tilesCheck(pan.getTiles()) == 1) {//only colors match
					userAmount = userAmount+bet*10;
					txtBalance.setText(String.format("%.2f", userAmount));
				} else {//colors and shapes match
					userAmount = userAmount+bet*50;
					txtBalance.setText(String.format("%.2f", userAmount));
				}
			}
		});
		
		btnMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double bet=(0.1*userAmount);
					tr.randomizeTiles(pan.getTiles(), 4);
					repaint();
				if (tc.tilesCheck(pan.getTiles()) == 0) { //no match
					userAmount -= bet;
					txtBalance.setText(String.format("%.2f", userAmount));
					if (Double.parseDouble(txtBalance.getText()) == 0.00) { //disable buttons when user runs out of $
						btnMax.setEnabled(false);
						btnMid.setEnabled(false);
						btnMin.setEnabled(false);
					}
				} else if (tc.tilesCheck(pan.getTiles()) == 1) { //shapes match
					userAmount = userAmount+bet*5;
					txtBalance.setText(String.format("%.2f", userAmount));
				} else { //colors and shapes match
					userAmount = userAmount+bet*10;
					txtBalance.setText(String.format("%.2f", userAmount));
				}
				
			}
		});
		
		setupMenu();
		// randomizes tiles at start of game
		quickSlotRoll(4);
		tr.randomizeTiles(pan.getTiles(), 4);
		repaint();
		
	}	
	public void slotRoll(int numSlotSpin) { 
		for (int i = 0; i < 30; i++) {
			pan.slotSpin(numSlotSpin);
			c.add(pan, BorderLayout.CENTER);
			repaint();
			try {
			TimeUnit.MILLISECONDS.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			}}
	public void quickSlotRoll(int numSlotSpin) { 
			pan.slotSpin(numSlotSpin);
			c.add(pan, BorderLayout.CENTER);
			repaint();
		}
	public SlotMachineFrame() {
		tr = new TileRandomizer(); 
		tc = new TileChecker();
		rand = new Random();
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
		frm.setupMenu();
		frm.setupLook();
		frm.setVisible(true);
		frm.slotRoll(4);
		frm.slotRoll(3);
		frm.slotRoll(2);
		frm.slotRoll(1);

	}

}
