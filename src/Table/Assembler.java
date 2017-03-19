package Table;
//just a first comment to try commits

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
}
