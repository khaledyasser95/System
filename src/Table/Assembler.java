package Table;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//out main class
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ICY on 3/14/2017.
 */
public class Assembler {
    private final Map<String, OPERATION> opTable;
    private final Map<String, Integer> registerTable;
    //Q:is making symbol table final a correct move ? this means it can only be intialized in the constructor once
    //if we are going to do that then we need to put the constructor in pass 1
    private final Map<String, Integer> symbolTable;

    public Assembler() {
        opTable=Instruc.getOPERATIONTable();
        registerTable=Instruc.getRegisterTable();
        symbolTable = new HashMap<>();
        symbolTable.put(null, 0);
    }
    public static void main(String args[])
    {
        Assembler A=new Assembler();

        //take file path and name as input
        Scanner scanner =new Scanner(System.in);
        System.out.println("please enter file path ended by file name and extention to initiate pass 1");
        String filePath=scanner.nextLine();
        pass1(filePath);


    }



    public static void  pass1(String filePath)
    {
        //an array list to save read lines to process them one by one
        List <String> linesList=new ArrayList<>();
        //initiate file and start reading
        File f=new File(filePath);
        try{
            linesList=readLines(f);
        }catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("reading file failed");
        }
        //now we have lines in linesList time to parse






    }

    public static Statement characterParse(String line)
    {
        //didn't make them final as they were
          String label;
          String operation;
          String[] symbols;
          String _comment;
          String operandsAndComment;
          String directive;
          boolean extend;
          int _location;

        line.trim();
        if(line.charAt(0)=='.')
        {
            _comment=line.substring(1);
            return new Statement(_comment);
        }
        if(line.indexOf(' ')>0)
        {
            label=line.substring(0,indexOf(' '));
            line=line.substring(indexOf(' ')+1).trim();
            //check if 1st substring is operation taking in concideration + may be avaialble
            if(isOperation(label))
            {
                operation=label;
                label=null;
            }

        }
        else //only case is empty line or format 1 instruction or error
        {

        }

        for(int i=0;i<line.length();i++)
        {

        }

    }
    public static void pass2()
    {

    }
    public static List<String> readLines(File f)throws IOException
    {
        //file reader object takes a file
        FileReader fr=new FileReader(f);
        //buffer takes a file reader object
        BufferedReader br=new BufferedReader(fr);
        //variable to store each line read
        String line;
        //list of lines of program
        List<String>lines=new ArrayList<>();

        while((line=br.readLine())!=null)
        {
            lines.add(line);

        }
        //print read lines by their numbers
        for(int i=0;i<lines.size();i++)
        {
            System.out.println(i+" - "+lines.get(i));
        }
        //close readers stream
        br.close();
        fr.close();
        return lines;
    }


  /*  public static void main(String args[])
    {
        //must put file path and without .txt
        File f=new File("E:\\self learning\\CodeForces\\src\\test");
        //function must be called between try and catch
        try{
            readLines(f);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

*/

}
