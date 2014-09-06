
import java.io.*;
/**
 * Created by joframart on 9/6/14.
 */
public class CodeReader {


    private File programFile;

    private int memoryMirror[];

    public static void main(String[] args) throws FileNotFoundException
    {

        CodeReader reader = new CodeReader("code.txt");

        reader.extractCode();
    }

    public CodeReader(String filePath)
    {
        programFile = new File(filePath);
        System.out.println("Path: " + programFile.getAbsolutePath());
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
                System.out.println("Address: " + address);
                String data = line.substring(6,10);
                System.out.println("Data: " + data);
            }

            reader.close();
        }

        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
