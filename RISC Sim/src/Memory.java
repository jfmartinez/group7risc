public class Memory{
		//variables
		int[] sysMem;

        private final int keyboard_location = 128;

        private final int parallel_in = 130;
        private final int parallel_out = 132;

        private final int hex_display_min = 136;
        private final int hex_display_max = 139;

        private final int ascii_display = 140;
        private final int ascii_display_final = 155;
        private final int MEMORY_SIZE = 2048;

        
        //constructor
        public Memory(int[] new_mem)
        {
            sysMem = new_mem;
        }

        
        
		//methods
		public int get(int i){
			return sysMem[i];
		}
			
		public void set(int newValue, int i){

            if(checkAccess(i))
              sysMem[i] = newValue & 0xFF;
		}

    public void inputIO(int newValue, int i){

            sysMem[i] = newValue & 0xFF;
    }
		
		public void reset(){
			sysMem = new int[MEMORY_SIZE];
		}


        private boolean checkAccess(int address_access){

            if(address_access == keyboard_location) return false;

            if(address_access == parallel_in) return false;

            if(address_access == parallel_out) return true;

            if(address_access >= hex_display_min && address_access <= hex_display_max) return true;

            if(address_access >= ascii_display && address_access <= ascii_display_final) return true;

            else
                return true;
        }



        public int getMemorySize(){
        	return MEMORY_SIZE;
        }





	}