package Table;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ICY on 3/13/2017.
 */

public class Instruc {
    private static final Map<String, OPERATION> OPERATIONTable;
    private static final Map<String, Integer> registerTable;

    static {
        //Operations can be done
        OPERATIONTable = new HashMap<>();
        registerTable = new HashMap<>();
        //operation arguments: mnemonic,format,opcode
        //Q: why not put 3/4 format operations as two different ones in map with same key?
        OPERATIONTable.put("ADD", new OPERATION("ADD", "3", "18"));
        OPERATIONTable.put("CLEAR", new OPERATION("CLEAR", "2", "B4"));
        OPERATIONTable.put("COMP", new OPERATION("COMP", "3/4", "28"));
        OPERATIONTable.put("COMPR", new OPERATION("COMPR", "2", "A0"));
        OPERATIONTable.put("J", new OPERATION("J", "3/4", "3C"));
        OPERATIONTable.put("JEQ", new OPERATION("JEQ", "3/4", "30"));
        OPERATIONTable.put("JLT", new OPERATION("JLT", "3/4", "38"));
        OPERATIONTable.put("JSUB", new OPERATION("JSUB", "3/4", "48"));
        OPERATIONTable.put("LDA", new OPERATION("LDA", "3/4", "00"));
        OPERATIONTable.put("LDB", new OPERATION("LDB", "3/4", "68"));
        OPERATIONTable.put("LDCH", new OPERATION("LDCH", "3/4", "50"));
        OPERATIONTable.put("LDT", new OPERATION("LDT", "3/4", "74"));
        OPERATIONTable.put("RD", new OPERATION("RD", "3/4", "D8"));
        OPERATIONTable.put("RSUB", new OPERATION("RSUB", "3/4", "4C"));
        OPERATIONTable.put("STA", new OPERATION("STA", "3/4", "0C"));
        OPERATIONTable.put("STCH", new OPERATION("STCH", "3/4", "54"));
        OPERATIONTable.put("STL", new OPERATION("STL", "3/4", "14"));
        OPERATIONTable.put("STX", new OPERATION("STX", "3/4", "10"));
        OPERATIONTable.put("TD", new OPERATION("TD", "3/4", "E0"));
        OPERATIONTable.put("TIXR", new OPERATION("TIXR", "2", "B8"));
        OPERATIONTable.put("WD", new OPERATION("WD", "3/4", "DC"));
        //SIC REGISTERS WITH NUMBER TO IDENTIFY
        //Q: why did we put null at first one?
        registerTable.put(null, 0);
        registerTable.put("A", 0);
        registerTable.put("X", 1);
        registerTable.put("L", 2);
        registerTable.put("B", 3);
        registerTable.put("S", 4);
        registerTable.put("T", 5);
        registerTable.put("F", 6);
        registerTable.put("SW", 9);
    }

    public static Map<String, OPERATION> getOPERATIONTable() {
        return OPERATIONTable;
    }

    public static Map<String, Integer> getRegisterTable() {
        return registerTable;
    }
}
