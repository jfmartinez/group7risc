import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;


public class CpuGui {
	
	private JFrame mainFrame;					// top level window
	private Container mainContentPane;
	Simulator simulator;
	
	
	public CpuGui(){
		// Make toplevel window
		this.mainFrame = new JFrame("RISC Simulator");
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setSize(800,700);
		this.mainContentPane = this.mainFrame.getContentPane();
		this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS));
	}
	
	public void startSim(){
		simulator = new Simulator();
		mainContentPane.removeAll();
		
		this.mainFrame.setVisible(true);
	}
}