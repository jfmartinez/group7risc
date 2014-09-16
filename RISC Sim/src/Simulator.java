import java.io.File;

/** Simulator class represents the state of the system
 * CPU
 * 8 8-bit general purpose registers (R0 always 0, R1 an accumulator)
 * 
 * 11-bit Program Counter
 * 16-bit Instruction Register
 * Condition bit: to do conditional jumps
 * 
 * MEMORY
 * Big endian 16-bit memory with 1024 cells
 * 
 * Address 128: 8-bit Keyboard input
 * Address 130: 8-bit Parallel input
 * Address 132: 8-bit Parallel output
 * Address 136-139: Hex display, 4 cells, each contain 4-bit hex characters
 * Address 140-155: ASCII display
 * 
 * 16-bit instructions: 5-bit opcode, 3-bit registers and 8-bit or 11-bit address
 * Instructions begin in even memory cells
 * Must accept every addressing mode
 * 
 * First instruction executed is in address 0 (RESET Vector = 0)
 * Display shows 8 bits (in hex)
 * Arithmetic operations use 2's complement
 *
 * @author Jose F. Martinez Rivera, Luis Murphy, Jose A. Rodriguez Cartagena
 */
public class Simulator implements Runnable{

    CPU cpu;
    Memory mem;
    Callback program_finish; //Alerts that the program has finished execution
    int addressBus; // where the data is written to or read from
    int dataBus; //data that is read or written
    boolean controlBus; //true = 1 = write
    boolean condBit; //altered by arithmetic/logic instructions
    int fetch_count = 0;
    boolean fetch_done = false;
    boolean stop=false; //Denotes if it has stopped



    public Simulator(){
        cpu = new CPU();
    }


    //Adds a callback function to the Simulator
    public void addCallback(Callback gui_callback)
    {
        this.program_finish = gui_callback;
    }

    public void fetch()
    {
        addressBus = cpu.get("PC");
        controlBus = false;

        dataBus = mem.get(addressBus);

        cpu.pushIR(dataBus);

        cpu.set((addressBus + 1)%mem.getMemorySize(), "PC");

        if(fetch_count == 1) //Done fetching
        {
            fetch_count = 0;
            fetch_done = true;
        }
        else {
            fetch_count++;
            fetch_done = false;
        }
    }

    public void fetchFullIR(){

        cpu.set(0x0000, "IR"); //Reset IR

        fetch();
        fetch();

    }

    //Step by step execution of an instruction
    public void stepExecution()
    {
        fetchFullIR();
        decExe();
        printStatus();

    }

    //Executes a full Execution cycle until the stop flag is true
    public void run() {

        while(!stop){
            try {
                stepExecution();
                this.program_finish.callback();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                throw new RuntimeException("Interrupted",e);
            }
        }
    }

    public void setStop(boolean bool){stop=bool;}



    public void decExe(){
        // TODO IMPLEMENT ALL METHODS
        //Create a method for every instruction?
        //Do the implementation of every instruction inside the switch?
        switch (cpu.getOpCode()){

//====================================== MOVIMIENTO DE DATOS ============================================================//

            case 0:{//Load data in memory address to register\
                int ops[] = interpretF2Format();
                cpu.set(mem.get(ops[1]), ops[0]+"");
                break;}
            case 1:{//Load constant to register
                int ops[]=interpretF2Format();
                cpu.set(ops[1], ops[0]+"");
                break;}
            case 2:{//LDACC const {F3}
                cpu.set(interpretF3Format(), "1");
                break;}
            case 3:{//ST mem,Ra {F2}
                int ops[] = interpretF2Format();
                mem.set(cpu.get(""+ops[0]), ops[1]);
                break;}
            case 4:{//STACC mem {F3}
                mem.set(cpu.get("1"), interpretF3Format());
                break;}
            case 5:{//LDR Ra,Rb{F1}		R[Ra] <- mem[R[Rb]]
                int ops[] = interpretF1Format();
                cpu.set(mem.get(cpu.get(""+ops[1])), ""+ops[0]);
                break;}
            case 6:{//STR Ra,Rb {F1}	R[Rb] <- mem[R[Ra]]
                int ops[]=interpretF1Format();
                int address = cpu.get(""+ops[0]);
                int valueInMem = mem.get(address);
                cpu.set(valueInMem, ""+ops[1]);
                break;}


//================================================ ARITMETICA ============================================================//
            //if ( msbit(opa) == msbit(opb) ) && ( msbit(res) != msbit(opb) ) signed overflow, else no signed overflow
            case 7:{ //ADD Ra,Rb,Rc {F1}		R[ra]<- R[rb]+R[rc]
                //Registers hold numbers from 0-255. From 0-127, all numbers positive, okay.
                //From 128-255, they represent negative numbers, they have a 256 bias.
                int ops[] = interpretF1Format();
                int opa,opb;
                if (cpu.get(""+ops[1])>127) opa=cpu.get(""+ops[1])-256;
                else opa=cpu.get(""+ops[1]);

                if (cpu.get(""+ops[2])>127) opb=cpu.get(""+ops[2])-256;
                else opb=cpu.get(""+ops[2]);

                int toSet = opa+opb;
                if (toSet<0) {condBit=true; toSet+=256;}

                cpu.set(toSet,""+ops[0]);
                break;}
            case 8:{ //SUB Ra,Rb,Rc {F1}  R[ra]<- R[rb]-R[rc]
                //Two's complement subtraction a-b is actually a+(-b)+1
                //a recieves same treatment as case 7 (bias -256 is >127)
                //b must be negative. To do so,
                int ops[] = interpretF1Format();
                int opa,opb;
                if (cpu.get(""+ops[1])>127) opa=cpu.get(""+ops[1])-256;
                else opa=cpu.get(""+ops[1]);

                if (cpu.get(""+ops[2])>127) opb=-(cpu.get(""+ops[2])-256);
                else opb=-cpu.get(""+ops[2]);

                int toSet = opa+opb;
                if (toSet<0) {condBit=true; toSet+=256;}

                cpu.set(toSet,""+ops[0]);
                break;}
            case 9:{//ADI Ra, cons {F2}  R[1]<= R[ra]+cons
                int ops[] = interpretF2Format();

                int opa,opb;
                if (cpu.get(""+ops[0])>127) opa=cpu.get(""+ops[0])-256;
                else opa=cpu.get(""+ops[0]);

                if (ops[1]>127) opb=(ops[1]-256);
                else opb=ops[1];

                int toSet = opa+opb;
                if (toSet<0) {condBit=true; toSet+=256;}

                cpu.set(toSet,"1");

                break;}
            case 10:{//SBI Ra,  cons {F2}  R[1]<= R[ra]-cons
                int ops[] = interpretF2Format();

                int opa,opb;
                if (cpu.get(""+ops[0])>127) opa=cpu.get(""+ops[0])-256;
                else opa=cpu.get(""+ops[0]);

                if (ops[1]>127) opb=-(ops[1]-256);
                else opb=-ops[1];

                int toSet = opa+opb;
                if (toSet<0) {condBit=true; toSet+=256;}

                cpu.set(toSet,"1");
                break;}

//======================================  LOGICA Y DESPLAZAMIENTO ============================================================//
            case 11: {
                System.out.println("AND Operation");
                //Interpret F1 Format
                int operands[] = interpretF1Format();

                int rb_value = cpu.get(String.valueOf(operands[1]));
                int rc_value = cpu.get(String.valueOf(operands[2]));
                cpu.set((rb_value & rc_value) & 0xFF, String.valueOf(operands[0]));

                break;
            }
            case 12: {
                System.out.println("OR Operation");

                //Interpret F1 Format
                int operands[] = interpretF1Format();
                int rb_value = cpu.get(String.valueOf(operands[1]));
                int rc_value = cpu.get(String.valueOf(operands[2]));
                cpu.set((rb_value | rc_value) & 0xFF, String.valueOf(operands[0]));

                break;

            }
            //XOR
            case 13:{
                System.out.println("XOR Operation");
                //Interpret F1 Format
                int operands[] = interpretF1Format();
                int rb_value = cpu.get(String.valueOf(operands[1]));
                int rc_value = cpu.get(String.valueOf(operands[2]));
                cpu.set((rb_value ^ rc_value) & 0xFF, String.valueOf(operands[0]));

                break;
            }

            //NOT
            case 14:
            {
                System.out.println("NOT Operation");

                //Interpret F1 Format
                int operands[] = interpretF1Format();
                int rb_value = cpu.get(String.valueOf(operands[1]));
                int rc_value = cpu.get(String.valueOf(operands[2]));

                cpu.set(~rb_value & 0xFF, String.valueOf(operands[0]));
                break;

            }

            //NEG IMPLEMENT
            case 15:
            {
                System.out.println("NEG Operation");

                //Interpret F1 Format
                int operands[] = interpretF1Format();
                int rb_value = cpu.get(String.valueOf(operands[1]));
                int rc_value = cpu.get(String.valueOf(operands[2]));

                cpu.set(((~rb_value & 0xFF) +1) & 0xFF, String.valueOf(operands[0]));
                break;

            }


            //Shift Right SHR
            case 16:
            {
                System.out.println("SHR Operation");

                //Interpret F1 Format
                int operands[] = interpretF1Format();
                int rb_value = cpu.get(String.valueOf(operands[1]));
                int rc_value = cpu.get(String.valueOf(operands[2]));

                int value =  (int) ((byte)rb_value >> rc_value) & 0xff;
                cpu.set((int) value , String.valueOf(operands[0]));

                break;

            }
            //SHIFT LEFT SHL
            case 17:
            {
                System.out.println("SHL Operation");

                int operands[] = interpretF1Format();
                int rb_value = cpu.get(String.valueOf(operands[1]));
                int rc_value = cpu.get(String.valueOf(operands[2]));
                cpu.set((rb_value << rc_value) & 0xFF, String.valueOf(operands[0]));

                break;
            }

            //Rotate to Left RTR
            case 18:
            {
                System.out.println("RTR Operation");

                int operands[] = interpretF1Format();
                int rb_value = cpu.get(String.valueOf(operands[1]));
                int rc_value = cpu.get(String.valueOf(operands[2]));

                //Assume rc_value is smaller than a byte and it's a nonnegative numebr
                int value = rb_value >>> rc_value %Byte.SIZE| (rb_value << (Byte.SIZE -rc_value%Byte.SIZE));
                cpu.set(value & 0xFF, String.valueOf(operands[0]));


                break;

            }

            //Rotate to Right RTL
            case 19:
            {
                System.out.println("RTL Operation");
                int operands[] = interpretF1Format();
                int rb_value = cpu.get(String.valueOf(operands[1]));
                int rc_value = cpu.get(String.valueOf(operands[2]));
                int value = ( (rb_value << rc_value%Byte.SIZE) |(rb_value  >> (Byte.SIZE - rc_value%Byte.SIZE))) ;


                //Assume rc_value is smaller than a byte and it's a nonnegative numebr
                cpu.set((int) value & 0xFF, String.valueOf(operands[0]));

                break;
            }

//====================================== BRINCOS Y CONTROL============================================================//
            //JMPR : Places the value of the register given and places it in the program counter to simulate a Jump 
            case 20:
            {
                System.out.println("JMPR Operation");

                int operands[] = interpretF1Format();
                int ra_value = cpu.get(String.valueOf(operands[0]));


                cpu.set(ra_value, "PC");


                break;
            }
            
            //JMPA : Places the address given and places it in the program counter to simulate a Jump 
            case 21:
            {
                System.out.println("JMPA Operation");

                int addr = interpretF3Format();

                cpu.set(addr, "PC");

                break;
            }
            //JCR : Places the value of the register given and places it in the program counter to simulate a Jump only
            // if the condition is true
            case 22:
            {
                System.out.println("JCR Operation");

                int operands[] = interpretF1Format();
                int ra_value = cpu.get(String.valueOf(operands[0]));

                if(this.condBit ==true){
                    cpu.set(ra_value, "PC");
                }

                break;
            }
            //JCA : Places the address given and places it in the program counter to simulate a Jump only
            // if the condition is true
            case 23:
            {
                System.out.println("JCA Operation");

                int addr = interpretF3Format();

                if(this.condBit ==true){
                    cpu.set(addr, "PC");
                }
                break;
            }
            // LOOP: The instruction loops until the value of Ra is 0 then it stores the value of Rb in the PC
            case 24:
            {
                System.out.println("LOOP Operation");

                int operands[] = interpretF2Format();
                int loop= operands[0];

                while(loop!=0){
                    loop--;
                    cpu.set(loop, String.valueOf(operands[0]));
                }
                cpu.set(operands[1], "PC");

                break;
            }
            // GR: If the value of Ra is greater than the value of Rb the condition will be true, else false
            case 25:
            {
                System.out.println("GR Operation");

                int operands[] = interpretF1Format();
                int ra_value = cpu.get(String.valueOf(operands[0]));
                int rb_value = cpu.get(String.valueOf(operands[1]));


                this.condBit= ra_value > rb_value;
                System.out.println(condBit);

                break;
            }
            // GRE: If the value of Ra is greater than or equal to the value of Rb the condition will be true, else false
            case 26:
            {
                System.out.println("GRE Operation");

                int operands[] = interpretF1Format();
                int ra_value = cpu.get(String.valueOf(operands[0]));
                int rb_value = cpu.get(String.valueOf(operands[1]));


                this.condBit= ra_value >= rb_value;
                System.out.println(condBit);

                break;
            }
          
            // EQ: If the value of Ra is equal to the value of Rb the condition will be true, else false
            case 27:
            {
                System.out.println("EQ Operation");

                int operands[] = interpretF1Format();
                int ra_value = cpu.get(String.valueOf(operands[0]));
                int rb_value = cpu.get(String.valueOf(operands[1]));


                this.condBit= ra_value == rb_value;
                System.out.println(condBit);

                break;
            }
            
            // NEQ: If the value of Ra is not equal to the value of Rb the condition will be true, else false
            case 28:
            {
                System.out.println("NEQ Operation");

                int operands[] = interpretF1Format();
                int ra_value = cpu.get(String.valueOf(operands[0]));
                int rb_value = cpu.get(String.valueOf(operands[1]));


                this.condBit= ra_value != rb_value;
                System.out.println(condBit);


                break;
            }
            // NOP: The instruction does nothing
            case 29:
            {
                System.out.println("NOP Operation");

                
                break;
            }
            // Stop: the stop flag will be true
            case 30:
            {
                System.out.println("Stop Operation");

                this.stop=true;

                break;
            }
        }}

    public void loadMemory(File file){
        CodeReader codereader = new CodeReader(file);
        codereader.extractCode();
        mem = new Memory(codereader.getMemoryMirror());
    }

    public void setMemory(Memory new_mem)
    {
        mem = new_mem;
    }


    public String hexString(int i)
    {
        String leading_zero = "";
        if(i <= 0xF)
        {
            leading_zero = "0";
        }

        return leading_zero + Integer.toString(i, 16);
    }


    public int[] interpretF1Format()
    {
        //Interpret F1 Format
        int operands[] = new int[3];

        //Pull instruction
        int instruction = cpu.get("IR");

        //Gather the destination
        int ra = (instruction >> 8) & 0x7; //Value location
        int rb = (instruction >> 5) & 0x7; //Value location
        int rc = (instruction >> 2) & 0x7; //Value location

        operands[0] = ra;
        operands[1] = rb;
        operands[2] = rc;

        return operands;

    }

    public int[] interpretF2Format()
    {

        int operands[]=new int[2];
        int instruction = cpu.get("IR");

        operands[0] = (instruction>>8) & 0x7; //Ra
        operands[1] = instruction & 0xFF; //Address / const

        return operands;

    }


    public int interpretF3Format()
    {
        //Interpret F3 Format
        int instruction = cpu.get("IR");
        int operand = instruction & 0x7FF;

        return operand;

    }




    //====================================== I/O Methods ============================================================//

    //Keyboard input
    public void inputKeyboad(String input)
    {
        if(input.equalsIgnoreCase("")) return;
        else {
            int hexInput = Integer.valueOf(input, 16);
            mem.inputIO(hexInput & 0xFF, 128);
        }

    }


    //Input parallel in into memory
    public void setParIn(String parallel_in){

        try {
            int par_in = Integer.parseInt(parallel_in, 16) ;
            mem.inputIO(par_in & 0xFF, 130);
        } catch(NumberFormatException e) {
            //Do Nothing
        }
    }

    //Read from the parallel output
    public String getParOut(){return hexString(mem.get(132));}


    public String getHex(){
        String firstCharacter = Integer.toBinaryString(mem.get(136) & 0xF)+Integer.toBinaryString(mem.get(137)& 0xF);
        String secondCharacter = Integer.toBinaryString(mem.get(138)& 0xF)+Integer.toBinaryString(mem.get(139)& 0xF);
        return interpretAsHexDisplay(firstCharacter)+interpretAsHexDisplay(secondCharacter);
    }


    /**
     * Gets the ASCII Display contents in the memory
     * @return String representing the ascii content
     */
    public String getAscii(){
        String display = "";

        int i = 0;
        while(i < 15)
        {

            int byteOne = mem.get(140 + i); //Retrieve byte

            char c1 = (char) byteOne; //int to ASCII


            display += c1;
            i++;
        }

        return display;
    }
    private String interpretAsHexDisplay(String toInterpret){
        String result;
        int parsed = Integer.parseInt(toInterpret,2);
        int anded = parsed & 0x7F;
        //given a seven segment display where A turns on with bit 1, B turns on with bit 2, etc...
        switch(anded){
            case 0b0000110:
                result ="1";
                break;
            case 0b1011011:
                result ="2";
                break;
            case 0b1001111:
                result ="3";
                break;
            case 0b1100110:
                result ="4";
                break;
            case 0b1101101:
                result ="5";
                break;
            case 0b1111101:
                result ="6";
                break;
            case 0b0000111:
                result ="7";
                break;
            case 0b1111111:
                result ="8";
                break;
            case 0b1101111:
                result ="9";
                break;
            case 0b1110111:
                result ="A";
                break;
            case 0b1111100:
                result ="b";
                break;
            case 0b0111001:
                result ="C";
                break;
            case 0b1011110:
                result ="d";
                break;
            case 0b1111001:
                result ="E";
                break;
            case 0b1110001:
                result ="F";
                break;
            default:
                result="_";
        }
        if(toInterpret.substring(0,1).equals("1")) result = result.concat(".");
        return result;
    }

    private String interpretAsHexDisplay(String toInterpret){
    	String result;
    	int parsed = Integer.parseInt(toInterpret,2);
    	int anded = parsed & 0x7F;
    	//given a seven segment display where A turns on with bit 1, B turns on with bit 2, etc...
    	switch(anded){
        case 0b0000110:
        	result ="1";
        	break;
        case 0b1011011:
        	result ="2";
        	break;
        case 0b1001111:
        	result ="3";
        	break;
        case 0b1100110:
        	result ="4";
        	break;
        case 0b1101101:
        	result ="5";
        	break;
        case 0b1111101:
        	result ="6";
        	break;
        case 0b0000111:
        	result ="7";
        	break;
        case 0b1111111:
        	result ="8";
        	break;
        case 0b1101111:
        	result ="9";
        	break;
        case 0b1110111:
        	result ="A";
        	break;
        case 0b1111100:
        	result ="b";
        	break;
        case 0b0111001:
        	result ="C";
        	break;
        case 0b1011110:
        	result ="d";
        	break;
        case 0b1111001:
        	result ="E";
        	break;
        case 0b1110001:
        	result ="F";
        	break;
        default:
        	result="_";
        }
        if(toInterpret.substring(0,1).equals("1")) result = result.concat(".");
        return result;
    }
    //====================================== Microprocessor State Getters ============================================================//


    //Return the memory in an organized format for the GUI
    public String getMemoryContents(){
        String result="";


        for(int i=0;i<mem.getMemorySize();){

            String location = "";
            if(i < 0xFF) location = "00" + hexString(i); //Pattern for numbers lower than 0xFF
            else if(i < 0xFFF) location = "0" + hexString(i);
            else location = hexString(i);


            String content = hexString(mem.get(i)) + "" + hexString(mem.get(i+1));
            result = result.concat(location+": "+content + "\n");
            i+=2;
        }
        return result;
    }

    //Returns the register content from the cpu
    public String getRegisterContents(String reg){
        return ""+hexString(cpu.get(reg));
    }

    //the address bus
    public String getAddressBus(){return hexString(addressBus);}

    //Control bus representation
    public String getControlBus(){
        if (controlBus) return "1";
        else return "0";
    }


    //Gets the databus value of the CPU
    public String getDataBus(){return hexString(dataBus);}

    //Condition bit
    public String getCondBit(){
        if (condBit) return "1";
        else return "0";}

    //Refreshes the reigster data in the CPU with respect to the GUI
    public void editRegisters(String registerID, int value){
    	cpu.set(value, registerID);
    }


    //Copies the memory from the GUI, could be edited
    public void memoryCopy(String memoryCopy){
        CodeReader codereader = new CodeReader();
        codereader.extractCodeString(memoryCopy);
        mem = new Memory(codereader.getMemoryMirror());

    }

    private void printStatus()
    {
        for(int i = 0; i < 8; i ++)
            System.out.print("R"+i+": " + hexString(cpu.get(""+i)) + " | ");

        System.out.println();
        System.out.println("PC: " + hexString(cpu.get("PC"))+ " | IR: " + hexString(cpu.get("IR")));

    }

    public void resetSim()
    {
        this.cpu = new CPU();

    }




}


