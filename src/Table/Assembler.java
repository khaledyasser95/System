package Table;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ICY on 3/14/2017.
 */
public class Assembler {
    private final Map<String, OPERATION> opTable;
    private final Map<String, Integer> registerTable;
    private final Map<String, Integer> symbolTable;

    public Assembler() {
        opTable=Instruc.getOPERATIONTable();
        registerTable=Instruc.getRegisterTable();
        symbolTable = new HashMap<>();
        symbolTable.put(null, 0);
    }
}
