import java.io.File;

/* CPU
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
 */
public class Simulator {

	CPU cpu;
	Memory mem;

	int addressBus; // where the data is written to or read from
	int dataBus; //data that is read or written
	boolean controlBus; //true = 1 = write
	boolean condBit; //altered by arithmetic/logic instructions
    int fetch_count = 0;
    boolean fetch_done = false;

	
	
	public Simulator(){
		cpu = new CPU();
	}
	
	public void fetch()
    {
		addressBus = cpu.get("PC");
		controlBus = false;

		dataBus = mem.get(addressBus);

        cpu.pushIR(dataBus);

        cpu.set(addressBus + 1, "PC");

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
        fetch();
        fetch();
    }
	
	public void decExe(){
		// TODO IMPLEMENT ALL METHODS 
		//Create a method for every instruction?
		//Do the implementation of every instruction inside the switch?
		switch (cpu.getOpCode()){
		//MOVIMIENTO DE DATOS
		case 0:
			//call method for 0

			break;
		//ARITMETICA
		case 7:
			break;
			 //LOGICA Y DESPLAZAMIENTO
        case 11: {
            System.out.println("AND Operation");
            //Interpret F1 Format
            int operands[] = interpretF1Format();

            cpu.set(operands[1] & operands[2], String.valueOf(operands[0]));
            System.out.println("[Rb]: " + hexString(operands[1]));
            System.out.println("[Rc]: " + hexString(operands[2]));
            System.out.println("[Ra]: " + hexString(cpu.get(String.valueOf(operands[0]))));
            break;
        }
        case 12: {
            System.out.println("OR Operation");

            //Interpret F1 Format
            int operands[] = interpretF1Format();

            cpu.set(operands[1] | operands[2], String.valueOf(operands[0]));

            break;

        }
        //XOR
        case 13:{
            System.out.println("XOR Operation");
            //Interpret F1 Format
            int operands[] = interpretF1Format();

            cpu.set(operands[1] ^ operands[2], String.valueOf(operands[0]));

            break;
        }

        //NOT
        case 14:
        {
            System.out.println("NOT Operation");

            //Interpret F1 Format
            int operands[] = interpretF1Format();

            cpu.set(~operands[1], String.valueOf(operands[0]));
            break;

        }

        //NEG IMPLEMENT
        case 15:
        {
            System.out.println("NEG Operation");

            //Interpret F1 Format
            int operands[] = interpretF1Format();

            cpu.set((~operands[1]) +1, String.valueOf(operands[0]));
            break;

        }


        //Shift Right SHR
        case 16:
        {
            System.out.println("SHR Operation");

            //Interpret F1 Format
            int operands[] = interpretF1Format();

            cpu.set(operands[1] >> operands[2], String.valueOf(operands[0]));

            break;

        }
        //SHIFT LEFT SHL
        case 17:
        {
            System.out.println("SHL Operation");

            int operands[] = interpretF1Format();

            cpu.set(operands[1] << operands[2], String.valueOf(operands[0]));

            break;
        }

        //Rotate to Left RTL
        case 18:
        {
            System.out.println("RTL Operation");

            int operands[] = interpretF1Format();

            cpu.set(Integer.rotateLeft(operands[1], operands[2]), String.valueOf(operands[0]));

            break;

        }

        //Rotate to Right RTR
        case 19:
        {
            System.out.println("RTR Operation");

            int operands[] = interpretF1Format();

            cpu.set(Integer.rotateRight(operands[1], operands[2]), String.valueOf(operands[0]));

            break;
        }
		//BRINCOS Y CONTROL
		case 20:
			break;
		}
	}
	
	public void loadRegister(File file){
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
        return Integer.toString(i, 16);
    }

    public int[] interpretF1Format()
    {
        //Interpret F1 Format
        int operands[] = new int[3];
        //Pull instruction
        int instruction = cpu.get("IR");

        //Gather the destination
        operands[0] = (instruction >> 8) & 0x7; //Destination

        int rb = (instruction >> 5) & 0x7; //Value location
        int rc = (instruction >> 2) & 0x7; //Value location

        operands[1] = cpu.get(String.valueOf(rb)); //Value in Rb
        operands[2] = cpu.get(String.valueOf(rc)); //Value in Rc

        System.out.println("Ra: " + operands[0]);
        System.out.println("Rb: " + operands[1]);
        System.out.println("Rc: " + operands[2]);


        return operands;

    }

    //Keyboard input
    public void inputKeyboard(String input){
        int hexInput = Integer.valueOf(input, 16);
        mem.set(hexInput, 128);
    }

    //String getters
    public String getMemoryContents(){
    	String result="";
    	for(int i=0;i<mem.getMemorySize();i++){
    		result = result.concat(i+": "+hexString(mem.get(i))+"\n");
    	}
    	return result;
    }
    
    public String getRegisterContents(String reg){
    	return ""+cpu.get(reg);
    }
    
    public String getKeyboard(){return ""+mem.get(128);}
    public String getParIn(){return ""+mem.get(130);}
    public String getParOut(){return ""+mem.get(132);}
    public String getHex(){
    	return "testHex";
    }
    public String getAscii(){
    	return "testAscii";
    }

    public String getAddressBus(){return ""+addressBus;}
    public String getControlBus(){
    	if (controlBus) return "1";
    	else return "0";
    }
    public String getDataBus(){return ""+dataBus;}
    public String getCondBit(){
    	if (condBit) return "1";
    	else return "0";}


}


