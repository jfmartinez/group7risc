
import java.io.*;
/**
 * Created by joframart on 9/6/14.
 */
public class CodeReader {


    private File programFile;

    private static int[] memoryMirror;


    public CodeReader(String filePath)
    {
        programFile = new File(filePath);
        System.out.println("Path: " + programFile.getAbsolutePath());
        memoryMirror = new int[1024];

        String number = "000A";
        System.out.println(Long.parseLong(number, 16));

    }
    
    public CodeReader(File file)
    {
        programFile = file;
        memoryMirror = new int[2048];

    }

    public void extractCode()
    {
        try{

            FileReader fileReader = new FileReader(programFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;

            while((line = reader.readLine())!= null)
            {
                //Extract code

                String address = line.substring(0,4);
//                System.out.println("Address: " + address);
                String data = line.substring(6,10);
//                System.out.println("Data: " + data);

                long address_number = Long.parseLong(address, 16);


                String big_endian_one = data.substring(0,2);
                String big_endian_two = data.substring(2,4);


                memoryMirror[(int)address_number] = (int) Integer.parseInt(big_endian_one, 16);
                memoryMirror[(int)address_number + 1] = (int) Integer.parseInt(big_endian_two, 16);


            }
//
//            for(int i = 0; i < memoryMirror.length; i++)
//            {
//                System.out.println("Content: " + memoryMirror[i]);
//            }

            reader.close();
        }

        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    


    public int[] getMemoryMirror(){
    	return memoryMirror;
    }
}
