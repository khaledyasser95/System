package Table.Objectcode;

import Table.Assembler;

/**
 * Created by ICY on 5/13/2017.
 */
public class Define implements RecordCollector {
    private  String name;
    private  int relativeadd;
    private final int repeat;
    String[] ExtD; String[] ExtR; //for EXTDEF and EXTREF definitions
    String Symbols;
    Assembler asm = new Assembler();
    public Define(String name, int relativeadd, int repeat, String symbols) {
        this.name = name;
        this.relativeadd = relativeadd;
        this.repeat = repeat;
        Symbols = symbols;
    }

    public Define(String name, int relativeadd, int repeat) {
        this.name = name;
        this.relativeadd = relativeadd;
        this.repeat = repeat;
    }
    //name of ext. symbol defined
    public  String LabF(String label)
    {
        int max = 6;
        int len = max - label.length();


        for(int i = 0; i < len; i++)
        {
            label+=" ";
        }
        name=label;
        return label;
    }

    //location address must be 3-byte
    //relative address
    public  String LocF(String location)
    {
        int max = 6;
        int len = max - location.length();

        for(int i = 0; i < len; i++)
        {
            location = "0" + location;
        }
        relativeadd = Integer.parseInt(location);
        return location;
    }
    @Override
    public String toObjectProgram() {
        //%number= which statement to insert
        // $type = type of insertions
        // -6 RIGHT PADDING
        return  String.format("D^%06X^%06X^", name, relativeadd);
    }
}
