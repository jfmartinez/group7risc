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

	CPU cpu = new CPU();
	Memory mem = new Memory();
	int addressBus; // where the data is written to or read from
	int dataBus; //data that is read or written
	boolean controlBus; //true = 1 = write
	boolean condBit; //altered by arithmetic/logic instructions
	
	private void fetch(){
		addressBus = cpu.get("PC");
		controlBus = false;
		dataBus = mem.get(addressBus);
		cpu.set(dataBus, "IR"); //contents of data bus into IR
	}
	
	private void decExe(){
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
		case 11:
			break;
		//BRINCOS Y CONTROL
		case 20:
			break;
		}
	}
}


