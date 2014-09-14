import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class CpuGui extends JFrame implements ActionListener{
	
	private Container mainContentPane;
	final JFileChooser fc = new JFileChooser();
	Simulator simulator;
	
	private JLabel pcLabel = new JLabel("PC");
	private JLabel irLabel= new JLabel("IR");
	private JLabel R0Label=new JLabel("R0");
	private JLabel R1Label=new JLabel("R1");
	private JLabel R2Label=new JLabel("R2");
	private JLabel R3Label=new JLabel("R3");
	private JLabel R4Label=new JLabel("R4");
	private JLabel R5Label=new JLabel("R5");
	private JLabel R6Label=new JLabel("R6");
	private JLabel R7Label=new JLabel("R7");
	private JLabel keyboardLabel=new JLabel("Keyboard");
	private JLabel asciiDisplayLabel=new JLabel("ASCII Display");
	private JLabel hexDisplayLabel=new JLabel("Hex Display");
	private JLabel parallelInLabel=new JLabel("Parallel in");
	private JLabel parallelOutLabel=new JLabel("Parallel out");
	private JLabel memoryLabel=new JLabel("Memory");
	private JLabel dataBusLabel=new JLabel("Data Bus");
	private JLabel addressBusLabel=new JLabel("Address Bus");
	private JLabel controlBusLabel=new JLabel("Control Bus");
	private JLabel conditionBitLabel=new JLabel("Condition Bit");
	
	private JTextField pcField;
	private JTextField irField;
	private JTextField R0Field;
	private JTextField R1Field;
	private JTextField R2Field;
	private JTextField R3Field;
	private JTextField R4Field;
	private JTextField R5Field;
	private JTextField R6Field;
	private JTextField R7Field;
	private JTextField keyboardField;
	private JTextField asciiDisplayField;
	private JTextField hexDisplayField;
	private JTextField parallelInField;
	private JTextField parallelOutField;
	private JTextField dataBusField; 
	private JTextField addressBusField;
	private JTextField controlBusField;
	private JTextField conditionBusField;
	
	private JTextArea memoryArea;
	
	
	
	public CpuGui(){
		// Make toplevel window
		setSize(800,700);
		pcField = new JTextField();
		irField = new JTextField();
		R0Field = new JTextField();
		R1Field = new JTextField();
		R2Field = new JTextField();
		R3Field = new JTextField();
		R4Field= new JTextField();
		R5Field = new JTextField();
		R6Field = new JTextField();
		R7Field = new JTextField();
		keyboardField = new JTextField();
		asciiDisplayField = new JTextField();
		hexDisplayField = new JTextField();
		parallelInField = new JTextField();
		parallelOutField = new JTextField();
		dataBusField = new JTextField();
		addressBusField = new JTextField();
		controlBusField = new JTextField();
		conditionBusField = new JTextField();
		memoryArea = new JTextArea();
		
		this.mainContentPane = getContentPane();
		this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS));
		
		// Menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Game menu
		JMenu loadMenu = new JMenu("Load Program");
		menuBar.add(loadMenu);
		newMenuItem("Load", loadMenu, this);
		
		JLabel label = new JLabel("PC");
		JTextField textField = new JTextField(4);
		mainContentPane.add(label);
		mainContentPane.add(textField);

		
	}
	
	public void startSim(){
		simulator = new Simulator();
		//mainContentPane.removeAll();
		
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Load")){
				int returnVal = fc.showOpenDialog(this);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fc.getSelectedFile();
		            simulator.loadMemory(file);
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