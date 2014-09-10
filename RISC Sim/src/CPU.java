public class CPU{
		//variables
		private int gpRegs[] = new int[8];
		private int IR; //16 bits
		private int PC; //11 bits
		
		// TODO constructor
		public CPU(){
			
			
		}
		
		//methods
		public int get(String s){
			if (s.equalsIgnoreCase("IR")){
				return IR;
			}
			else if (s.equalsIgnoreCase("PC")){
				return PC;
			}
			else{
				return gpRegs[Integer.parseInt(s)];
			}
		}
			
		public void set(int newValue, String registerToSet){
			if (registerToSet.equalsIgnoreCase("IR")){
				IR = newValue;
			}
			else if (registerToSet.equalsIgnoreCase("PC")){
				PC = newValue;
			}
			else{
				gpRegs[Integer.parseInt(registerToSet)] = newValue;
			}
		}
		
		public void reset(){
			IR = PC = 0;
			for (int i=0; i<8; i++){
				gpRegs[i] =0;
			}
		}
		public int getOpCode(){

            int intOpCode = (IR & 0xF800) >> 11; //Shift Code 11 bits to get the first 5 bits
            System.out.println("Opcode: " + intOpCode);
            return intOpCode;
		}
	}