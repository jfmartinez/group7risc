public class Memory{
		//variables
		int[] sysMem = new int[1024];
		
		//methods
		public int get(int i){
			return sysMem[i];
		}
			
		public void set(int newValue, int i){
			sysMem[i] = newValue;
		}
		
		public void reset(){
			sysMem = new int[1024];
		}
	}