
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
    boolean stop=false;



    public Simulator(){
        cpu = new CPU();
    }

    public void cycle(){
        do{
            fetch();
            decExe();

        }while(stop=false);

        //Stop cycle
    }

    public void cycleStep(){

        fetch();
        decExe();
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
            {
                System.out.println("JMPR Operation");

                int operands[] = interpretF1Format();

                cpu.set(operands[0], "PC");


                break;
            }
            case 21:
            {
                System.out.println("JMPA Operation");

                int addr = interpretF3Format();

                cpu.set(addr, "PC");

                break;
            }

            case 22:
            {
                System.out.println("JCR Operation");

                int operands[] = interpretF1Format();

                if(this.condBit ==true){
                    cpu.set(operands[0], "PC");
                }

                break;
            }

            case 23:
            {
                System.out.println("JCA Operation");

                int addr = interpretF3Format();

                if(this.condBit ==true){
                    cpu.set(addr, "PC");
                }
                break;
            }

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

            case 25:
            {
                System.out.println("GR Operation");

                int operands[] = interpretF1Format();

                this.condBit= operands[0] > operands[1];

                break;
            }

            case 26:
            {
                System.out.println("GRE Operation");

                int operands[] = interpretF1Format();

                this.condBit= operands[0] >= operands[1];

                break;
            }

            case 27:
            {
                System.out.println("EQ Operation");

                int operands[] = interpretF1Format();

                this.condBit= operands[0] == operands[1];

                break;
            }
            case 28:
            {
                System.out.println("NEQ Operation");

                int operands[] = interpretF1Format();


                this.condBit= operands[0] != operands[1];


                break;
            }
            case 29:
            {
                System.out.println("NOP Operation");

                // int operands[] = interpretF1Format();


                break;
            }

            case 30:
            {
                System.out.println("Stop Operation");

                // int operands[] = interpretF1Format();

                this.stop=true;

                break;
            }

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
        //     operands[0] = (instruction >> 8) & 0x7; //Destination

        int ra = (instruction >> 8) & 0x7; //Value location
        int rb = (instruction >> 5) & 0x7; //Value location
        int rc = (instruction >> 2) & 0x7; //Value location

        operands[0] = cpu.get(String.valueOf(ra)); //Value in Rb
        operands[1] = cpu.get(String.valueOf(rb)); //Value in Rb
        operands[2] = cpu.get(String.valueOf(rc)); //Value in Rc

        System.out.println("Ra: " + operands[0]);
        System.out.println("Rb: " + operands[1]);
        System.out.println("Rc: " + operands[2]);


        return operands;

    }

    public int[] interpretF2Format()
    {
        //Interpret F1 Format
        int operands[] = new int[2];
        //Pull instruction
        int instruction = cpu.get("IR");

        //Gather the destination
        //     operands[0] = (instruction >> 8) & 0x7; //Destination

        int ra = (instruction >> 8) & 0x7; //Value location
        int addr = instruction & 0xFF; //Value location

        operands[0] = cpu.get(String.valueOf(ra)); //Value in Rb
        operands[1] = cpu.get(String.valueOf(addr)); //Value in Rb

        System.out.println("Ra: " + operands[0]);
        System.out.println("const/addr: " + operands[1]);



        return operands;

    }
    public int interpretF3Format()
    {
        //Interpret F1 Format
        int operand ;
        //Pull instruction
        int instruction = cpu.get("IR");

        //Gather the destination
        //     operands[0] = (instruction >> 8) & 0x7; //Destination


        int addr = instruction & 0x7FF; //Value location

        operand = cpu.get(String.valueOf(addr)); //Value in Rb

        System.out.println("const/addr: " + operand);



        return operand;

    }

    //I/O Methods

    //Keyboard input
    public void inputKeyboad(String input)
    {

        int hexInput = Integer.valueOf(input, 16);

        mem.set(hexInput, 128);
    }

    //String getters
    public String getMemoryContents(){
        String result="";
        for(short i=0;i<mem.getMemorySize();){

            String location = String.format("%04d", i);
            String content = String.format("%02d02d", mem.get(i), mem.get(i+1) );
            result = result.concat(location+": " + content + " \n");
            i+=2;
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
        System.out.println(display);

        return display;
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


