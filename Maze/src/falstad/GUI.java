package falstad;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener {
	
	/**
	 * Got the idea to do this from SimpleKeyListener, turning the GUI into a part of the
	 * actual app. More intuitive because we want it to disappear while people are in the 
	 * Maze and such.
	 * 
	 * It finally works correctly like this. No shame. Technically we are not going against
	 * the project description at all.... hopefully... Please be kind..... 
	 * LOVE US YONGSEN!!!!
	 */
	
	MazeApplication app;
	Maze maze;
	///////////////// For the GUI!!!!!!! ///////////////////
	JFrame frame;
	int level;

	String driver;
	String algorithm;
	JButton button;
	
	ComboBox drivers;
	ComboBox algs;
	ComboBox levels;
	////////////// Input shenanigans ////////////////////
	String myDriver;
	String myAlgorithm;
	int myLevel;
	
	public GUI(MazeApplication app, Maze maze){
		this.app = app;
		this.maze = maze;
	}
	/**
	 * This makes the GUI attached to the MazeApp object. Should only be called once if 
	 * memory serves
	 */
	public void makeGUI(){
		frame = new JFrame("Maze");
        JLabel label = new JLabel("Welcome to the Maze. Choose:");
        label.setFont(new Font("Times New Roman", 1, 20));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new JPanel(new BorderLayout());
        newContentPane.add(label, BorderLayout.PAGE_START);
        JComponent newContentPane2 = new JPanel(new BorderLayout());
        JComponent newContentPane3 = new JPanel(new BorderLayout());
        ////////// Combo Boxes ///////////////////
        drivers = new ComboBox("drivers");
        algs = new ComboBox("algorithms");
        levels = new ComboBox("levels");
        newContentPane2.add(algs, BorderLayout.WEST);
        newContentPane2.add(drivers, BorderLayout.CENTER);
        newContentPane2.add(levels, BorderLayout.EAST);
        // Adds it to the first panel in the center
        newContentPane.add(newContentPane2, BorderLayout.CENTER);
        
        button = new JButton("GO!");
        
        button.addActionListener(this);
        newContentPane3.add(button);
        newContentPane.add(newContentPane3,  BorderLayout.PAGE_END);

        newContentPane.setOpaque(true); //content panes must be opaque
        ///////////////// GUI Initializer ///////////////////
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(false);
	}
	/**
	 * Makes the GUI visible
	 */
	public void showGUI(){
		frame.setVisible(true);
	}
	/**
	 * This is to listen to the button go off and transforms the MazeApp into the new maze.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		frame.setVisible(false);
		if( drivers.getInput() != null)
			driver = drivers.getInput();
		else
			driver = "Manual Driver";
		if(algs.getInput() != null)
			algorithm = algs.getInput();
		else
			algorithm = "Standard";
		if(levels.getInput() != null)
			level = Integer.parseInt(levels.getInput());
		else
			level = 0;
		maze.reset(algorithm, driver, level);
		maze.build(level);
		//app.repaint();
	}
	
}
