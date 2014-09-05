import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class CpuGui implements ActionListener{
	
	private JFrame mainFrame;					// top level window
	private Container mainContentPane;
	final JFileChooser fc = new JFileChooser();
	Simulator simulator;
	
	
	public CpuGui(){
		// Make toplevel window
		this.mainFrame = new JFrame("RISC Simulator");
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setSize(800,700);
		this.mainContentPane = this.mainFrame.getContentPane();
		this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS));
		
		// Menu bar
		JMenuBar menuBar = new JMenuBar();
		this.mainFrame.setJMenuBar(menuBar);
		
		// Game menu
		JMenu gameMenu = new JMenu("Load Program");
		menuBar.add(gameMenu);
		newMenuItem("Load", gameMenu, this);
	}
	
	public void startSim(){
		simulator = new Simulator();
		mainContentPane.removeAll();
		
		this.mainFrame.setVisible(true);
	}
	
	



	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Load")){
				int returnVal = fc.showOpenDialog(mainFrame);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            simulator.loadRegister(file);
		        } else {
		            System.out.append("Open command cancelled by user.\n");
		        }
			}
	}
	
	/**
	 * Make a JMenuItem, associate an action command and listener, add to menu
	 */
	private static void newMenuItem(String text, JMenu menu, ActionListener listener)
	{
		JMenuItem newItem = new JMenuItem(text);
		newItem.setActionCommand(text);
		newItem.addActionListener(listener);
		menu.add(newItem);
	}
}