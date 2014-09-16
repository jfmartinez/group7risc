public class CPU{
		//variables
		private int gpRegs[] = new int[8];
		private int IR; //16 bits
		private int PC; //11 bits
		
		// TODO constructor
		public CPU(){
			
			
		}
		
		//methods
		/**
		 * 
		 * @param s String values: "IR", "PC", integers 0-7 as strings
		 * @return value stored in register in integer format
		 */
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
			
		/**
		 * 
		 * @param newValue value to be stored
		 * @param registerToSet string that represents register tostore in
		 */
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

            int intOpCode = (IR >> 11) & 0x1F; //Shift Code 11 bits to get the first 5 bits
            return intOpCode;
		}

        public void pushIR(int ir_byte)
        {
            this.IR  = this.IR << 8;
            this.IR = this.IR + ir_byte;
        }
	}