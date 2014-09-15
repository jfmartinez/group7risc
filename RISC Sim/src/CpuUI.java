
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

/**
 *
 * @author LUIS
 */
public class CpuUI extends javax.swing.JFrame {

    private Simulator simulator = new Simulator();
    final JFileChooser fc = new JFileChooser();

    /**
     * Creates new form CpuUI
     */
    public CpuUI() {
    	initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jFileChooser1 = new JFileChooser();
        pcLabel = new javax.swing.JLabel();
        pcField = new javax.swing.JTextField();
        irField = new javax.swing.JTextField();
        irLabel = new javax.swing.JLabel();
        r0Label = new javax.swing.JLabel();
        r1Label = new javax.swing.JLabel();
        r2Label = new javax.swing.JLabel();
        r3Label = new javax.swing.JLabel();
        r4Label = new javax.swing.JLabel();
        r5Label = new javax.swing.JLabel();
        r6Label = new javax.swing.JLabel();
        r7Label = new javax.swing.JLabel();
        r0Field = new javax.swing.JTextField();
        r1Field = new javax.swing.JTextField();
        r3Field = new javax.swing.JTextField();
        r2Field = new javax.swing.JTextField();
        r4Field = new javax.swing.JTextField();
        r5Field = new javax.swing.JTextField();
        r6Field = new javax.swing.JTextField();
        r7Field = new javax.swing.JTextField();
        keyboardLabel = new javax.swing.JLabel();
        asciiLabel = new javax.swing.JLabel();
        hexLabel = new javax.swing.JLabel();
        parInLabel = new javax.swing.JLabel();
        parOutLabel = new javax.swing.JLabel();
        memoryLabel = new javax.swing.JLabel();
        keyboardField = new javax.swing.JTextField();
        asciiField = new javax.swing.JTextField();
        hexField = new javax.swing.JTextField();
        parInField = new javax.swing.JTextField();
        parOutField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        memoryArea = new javax.swing.JTextArea();
        addressBusLabel = new javax.swing.JLabel();
        controlBusLabel = new javax.swing.JLabel();
        dataBusLabel = new javax.swing.JLabel();
        controlBusField = new javax.swing.JTextField();
        addressBusField = new javax.swing.JTextField();
        dataBusField = new javax.swing.JTextField();
        condBitLabel = new javax.swing.JLabel();
        condBitField = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        loadButton = new javax.swing.JButton();
        executeButton = new javax.swing.JButton();
        stepExecuteButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //Set labels
        pcLabel.setText("PC");
        irLabel.setText("IR");
        r0Label.setText("R0");
        r1Label.setText("R1");
        r2Label.setText("R2");
        r3Label.setText("R3");
        r4Label.setText("R4");
        r5Label.setText("R5");
        r6Label.setText("R6");
        r7Label.setText("R7");
        keyboardLabel.setText("Keyboard");
        asciiLabel.setText("ASCII");
        hexLabel.setText("Hex");
        parInLabel.setText("Par IN");
        parOutLabel.setText("Par OUT");
        memoryLabel.setText("Memory");
        addressBusLabel.setText("Address Bus");
        controlBusLabel.setText("Control Bus");
        dataBusLabel.setText("Data Bus");
        condBitLabel.setText("Cond Bit");
        loadButton.setText("Load");
        executeButton.setText("Execute");
        stepExecuteButton.setText("Step Execute");
        stopButton.setText("Stop");
        
        
        //Set menu bar and actions menu
        setJMenuBar(jMenuBar1);
        jMenuBar1.add(jMenu1);
        jMenu1.setText("Actions");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        
        //Add actions to menu
        jMenu1.add(jMenuItem5);
        jMenuItem5.setText("Load");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        
        jMenu1.add(jMenuItem2);
        jMenuItem2.setText("Stop");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        
        jMenu1.add(jMenuItem6);
        jMenuItem6.setText("Step");
        jMenuItem6.addActionListener(new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                simulator.stepExecution();
                setFieldText();

            }
        });



        memoryArea.setColumns(20);
        memoryArea.setRows(5);
        jScrollPane1.setViewportView(memoryArea);

        pcField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        r0Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        r1Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        r2Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        r3Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        r4Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        r5Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        r6Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });

        r7Field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        
        keyboardField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyboardFieldActionPerformed(evt);
            }
        });

        asciiField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asciiFieldActionPerformed(evt);
            }
        });
        
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        stepExecuteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

               fieldEditReplacer();

                simulator.stepExecution();

                setFieldText();
            }
            
        
        });
        
        executeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldEditReplacer();

                simulator.setStop(false);
                (new Thread(simulator)).start();
                setFieldText();
            }
            
        
        });
        
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulator.setStop(true);
            }
        });





        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            		.addComponent(pcLabel)
	            		.addComponent(irLabel)
	            		.addComponent(r0Label)
	            		.addComponent(r1Label)
	            		.addComponent(r2Label)
	            		.addComponent(r3Label)
	            		.addComponent(r4Label)
	            		.addComponent(r5Label)
	            		.addComponent(r6Label)
	                    .addComponent(r7Label))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            		.addComponent(pcField, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
	                    .addComponent(irField, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
	                	.addComponent(r0Field, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
	                    .addComponent(r1Field, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
	                    .addComponent(r2Field, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
	                    .addComponent(r3Field, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
	                    .addComponent(r4Field, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
	                    .addComponent(r5Field, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
	                    .addComponent(r6Field, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
	                    .addComponent(r7Field, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            		.addComponent(keyboardLabel)
	                    .addComponent(asciiLabel)
	                	.addComponent(hexLabel)
	                    .addComponent(parInLabel)
	                    .addComponent(parOutLabel)
	                    .addComponent(memoryLabel))
	                .addGap(20, 20, 20)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                	.addComponent(keyboardField)/////
	                	.addComponent(asciiField)/////
	                    .addComponent(parInField)
	                    .addComponent(hexField)
	                    .addComponent(parOutField, javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(jScrollPane1))
	                .addGap(20, 20, 20)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                	.addComponent(controlBusLabel)
	                	.addComponent(addressBusLabel)
	                    .addComponent(condBitLabel)
	                    .addComponent(dataBusLabel))
	                .addGap(20, 20, 20)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(controlBusField, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)//////
	                    .addComponent(addressBusField, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)//////
	                	.addComponent(condBitField, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
	                    .addComponent(dataBusField, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
	                    .addComponent(loadButton)
	                    .addComponent(executeButton)
	                    .addComponent(stepExecuteButton)
	                    .addComponent(stopButton))
                    .addGap(20,20,20)
                )
    		)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pcLabel)
                            .addComponent(pcField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(keyboardLabel)
                            .addComponent(keyboardField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(controlBusLabel)
                            .addComponent(controlBusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(irLabel)
                            .addComponent(irField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(asciiLabel)
                            .addComponent(asciiField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addressBusLabel)
                            .addComponent(addressBusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r0Label)
                            .addComponent(r0Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hexLabel)
                            .addComponent(hexField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dataBusLabel)
                            .addComponent(dataBusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r1Label)
                            .addComponent(r1Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(parInLabel)
                            .addComponent(parInField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(condBitLabel)
                            .addComponent(condBitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r2Label)
                            .addComponent(r2Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(parOutLabel)
                            .addComponent(parOutField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r3Label)
                            .addComponent(r3Field, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(memoryLabel)
                            .addComponent(jScrollPane1,javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loadButton))/////////
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r4Label)
                            .addComponent(r4Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(executeButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r5Label)
                            .addComponent(r5Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stepExecuteButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r6Label)
                            .addComponent(r6Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stopButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(r7Label)
                            .addComponent(r7Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    /*.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)*/)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
        setVisible(true);
    }// </editor-fold>                        

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {
    }                                      

    private void pcFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }                                           

    private void r3FieldActionPerformed(java.awt.event.ActionEvent evt) {
    }                                           

    private void r4FieldActionPerformed(java.awt.event.ActionEvent evt) {
    }                                           

    private void keyboardFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }                                            

    private void asciiFieldActionPerformed(java.awt.event.ActionEvent evt) {
    }                                            

    private void r1FieldActionPerformed(java.awt.event.ActionEvent evt) {
    }                                           

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
    }  
    
    //Load Action Performed
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {
    	int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            simulator.loadMemory(file);
            setFieldText();
        } else {
            System.out.append("Open command cancelled by user.\n");
        }
    }
    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            simulator.loadMemory(file);
            setFieldText();
        } else {
            System.out.append("Open command cancelled by user.\n");
        }
    }
    
    private void setFieldText(){
    	memoryArea.setText(simulator.getMemoryContents());
    	r0Field.setText(simulator.getRegisterContents("0"));
    	r1Field.setText(simulator.getRegisterContents("1"));
    	r2Field.setText(simulator.getRegisterContents("2"));
    	r3Field.setText(simulator.getRegisterContents("3"));
    	r4Field.setText(simulator.getRegisterContents("4"));
    	r5Field.setText(simulator.getRegisterContents("5"));
    	r6Field.setText(simulator.getRegisterContents("6"));
    	r7Field.setText(simulator.getRegisterContents("7"));
    	irField.setText(simulator.getRegisterContents("IR"));
    	pcField.setText(simulator.getRegisterContents("PC"));
    	
//    	keyboardField.setText(simulator.getKeyboard());
//    	parInField.setText(simulator.getParIn());
    	parOutField.setText(simulator.getParOut());
    	asciiField.setText(simulator.getAscii());
    	hexField.setText(simulator.getHex());
    	
    	addressBusField.setText(simulator.getAddressBus());
    	dataBusField.setText(simulator.getDataBus());
    	controlBusField.setText(simulator.getControlBus());
    	condBitField.setText(simulator.getCondBit());
    	
    }

    private void fieldEditReplacer()
    {
        simulator.editRegisters("0", Integer.parseInt(r0Field.getText(), 16));
        simulator.editRegisters("1", Integer.parseInt(r1Field.getText(), 16));
        simulator.editRegisters("2", Integer.parseInt(r2Field.getText(), 16));
        simulator.editRegisters("3", Integer.parseInt(r3Field.getText(), 16));
        simulator.editRegisters("4", Integer.parseInt(r4Field.getText(), 16));
        simulator.editRegisters("5", Integer.parseInt(r5Field.getText(), 16));
        simulator.editRegisters("6", Integer.parseInt(r6Field.getText(), 16));
        simulator.editRegisters("7", Integer.parseInt(r7Field.getText(), 16));
        simulator.editRegisters("PC", Integer.parseInt(pcField.getText(), 16));
        simulator.editRegisters("IR", Integer.parseInt(irField.getText(), 16));


        simulator.memoryCopy(memoryArea.getText());

        System.out.println(keyboardField.getText());

        simulator.inputKeyboad(keyboardField.getText());




    }



    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel pcLabel; //PC
    private javax.swing.JLabel r7Label; //R7
    private javax.swing.JLabel keyboardLabel; //Keybo
    private javax.swing.JLabel asciiLabel; //ASCII
    private javax.swing.JLabel hexLabel; //Hex
    private javax.swing.JLabel parInLabel; //Par in
    private javax.swing.JLabel parOutLabel; //Par out
    private javax.swing.JLabel memoryLabel; //Mem
    private javax.swing.JLabel addressBusLabel; //Address
    private javax.swing.JLabel controlBusLabel; //Control 
    private javax.swing.JLabel dataBusLabel; // Data bus
    private javax.swing.JLabel irLabel;  //IR
    private javax.swing.JLabel condBitLabel; //Cond bit
    private javax.swing.JLabel r0Label; //R0
    private javax.swing.JLabel r1Label; //R1
    private javax.swing.JLabel r2Label; //R2
    private javax.swing.JLabel r3Label; //R3
    private javax.swing.JLabel r4Label; //R4
    private javax.swing.JLabel r5Label; //R5
    private javax.swing.JLabel r6Label; //R6
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1; //Menu Bar
    private javax.swing.JMenuItem jMenuItem2; //Stop
    private javax.swing.JMenuItem jMenuItem5; //Load
    private javax.swing.JMenuItem jMenuItem6; //Step
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea memoryArea; //Memory
    private javax.swing.JTextField pcField;
    private javax.swing.JTextField r7Field;
    private javax.swing.JTextField keyboardField;
    private javax.swing.JTextField asciiField;
    private javax.swing.JTextField hexField;
    private javax.swing.JTextField parInField;
    private javax.swing.JTextField parOutField;
    private javax.swing.JTextField controlBusField;
    private javax.swing.JTextField addressBusField;
    private javax.swing.JTextField dataBusField;
    private javax.swing.JTextField condBitField;
    private javax.swing.JTextField irField;
    private javax.swing.JTextField r0Field;
    private javax.swing.JTextField r1Field;
    private javax.swing.JTextField r3Field;
    private javax.swing.JTextField r2Field;
    private javax.swing.JTextField r4Field;
    private javax.swing.JTextField r5Field;
    private javax.swing.JTextField r6Field;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton executeButton;
    private javax.swing.JButton stepExecuteButton;
    private javax.swing.JButton stopButton;

    // End of variables declaration                   
}
