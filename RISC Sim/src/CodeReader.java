
import java.io.*;
/**
 * Created by joframart on 9/6/14.
 */
public class CodeReader {


    private File programFile;

    private static int[] memoryMirror;


    public CodeReader()
    {


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
                System.out.println("Data: " + data);

                long address_number = Long.parseLong(address, 16);


                String big_endian_one = data.substring(0,2);
                String big_endian_two = data.substring(2,4);


                memoryMirror[(int)address_number] = (int) Integer.parseInt(big_endian_one, 16);
                memoryMirror[(int)address_number + 1] = (int) Integer.parseInt(big_endian_two, 16);



            }

            reader.close();
        }

        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void extractCodeString(String memoryArea)
    {
        memoryMirror = new int[2048];
        try{
            StringReader textarea = new StringReader(memoryArea);
            BufferedReader reader = new BufferedReader(textarea);
            System.out.println(memoryArea);
            String line = null;

            while((line = reader.readLine())!= null)
            {
                //Extract code
                System.out.println("Extracting code");
                System.out.println(line);

                String address = line.substring(0,4);

                String data = line.substring(6,10);

                long address_number = Long.parseLong(address, 16);


                String big_endian_one = data.substring(0,2);
                String big_endian_two = data.substring(2,4);


                memoryMirror[(int)address_number] = (int) Integer.parseInt(big_endian_one, 16);
                memoryMirror[(int)address_number + 1] = (int) Integer.parseInt(big_endian_two, 16);


            }


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
