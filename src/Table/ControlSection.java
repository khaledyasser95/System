package Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kareem on 16-May-17.
 */
public class ControlSection implements Serializable {
    String name;
    ArrayList<String> externallyDefined;
    // ArrayList<String> externalReference;
    Map<String, Integer> symbolTable;

    public ControlSection(String name) {
        this.name = name;
        externallyDefined=new ArrayList<>(5);
        //  externalReference=new ArrayList<>(5);
        symbolTable = new HashMap<>();
        //Initializing the symbol table
        symbolTable.put(null, 0);

    }

    public ControlSection() {
        this.name = null;
        externallyDefined=new ArrayList<>(5);
        //externalReference=new ArrayList<>(5);
        symbolTable = new HashMap<>();
        //Initializing the symbol table
        symbolTable.put(null, 0);

    }

    public void setName(String name) {
        this.name = name;
    }
    public String searchExtDef(String label)
    {
        for(int i=0;i<externallyDefined.size();i++)
        {
            if(label==externallyDefined.get(i))
            {
                return label;
            }
        }
        return null;
    }
}
