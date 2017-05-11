package Table;

import java.io.Serializable;

/**
 * Created by ICY on 3/14/2017.
 */
//comment as
/*
for a class to be serialized successfully, two conditions must be met −

The class must implement the java.io.Serializable interface.

All of the fields in the class must be serializable. If a field is not serializable, it must be marked transient.

resource: https://www.tutorialspoint.com/java/java_serialization.htm
 */


public class Statement implements Serializable, Comparable {
    private final String Label;
    private final String Operation;
    private final String[] Symbols;
    private final String _comment;
    private final boolean Extend;
    private transient int _location;

    //constructor for a statement containing comments
    private Statement(String label, String operation, boolean extended, String[] symbols, String comment) {
        Label = label;
        Operation = operation;
        Extend = extended;
        Symbols = symbols;
        _comment = comment;
    }

    //constructor for a statement without a comment
    public Statement(String label, String operation, boolean extended, String[] symbols) {

        this(label, operation, extended, symbols, null);
    }

    //why put . in operation not null?
    //constructor for a line containing only a comment
    public Statement(String comment) {
        this(null, ".", false, null, comment);
    }

    // method Split the Line LABEL OPCODE OPERAND
    public static Statement parse(String statement) {
        // array of string statics each column contains a type: label,opcode,operand and comment (relatively)
        //trim ommits extra  spaces and split splits string into pieces every tab \t
        String[] split = statement.trim().split("\\s+");
        //compareTo returns 0 if strings are the same order in a dictionary
        //but if there is a comment after . wouldn't it NOT return 0? tried it in separate program and didn't return zero
        //maybe what you mean is comparing split[0][0] to "." that will make more sense
        //split[0].compareTo(".") == 0
        if (split[0].charAt(0) == '.') {

            //check if comment
            //this returns comment without the . as a new string
            //and makes a new statement using the comment only constructor
            return new Statement(statement.substring(statement.indexOf('.') + 1));

        } else {

            // If not so it will be Statement to be fetched
            String label, operation;
            String[] symbols;
            boolean extended = false;
            int index = 0;
            //Check if there is label
            //K: how did this check for a label  ? by comparing array length to 3 ?
            //there could be format 1 instructions and also there could be a comment after the statement
            /*TODO : this parse for format 1 instns and directives with no operand will not assign label +
                doesn't handle if a comment comes after a statement
             */
            if (split.length == 3) {
                label = split[index++];
            } else {
                label = null;
            }
            //newly added
            String check=null;
            if(split.length==2)
            {
                check=split[1];
                if(check.equals("RSUB") )
                    label=split[index++];
            }






            //Get Operation code
            operation = split[index++];
            //check if the operation is Format 4
            if (operation.charAt(0) == '+') {
                extended = true;
                operation = operation.substring(1);
            }
            // OPERAND
            symbols = new String[2];
            // ADD M,X example
            //checking whether there are operands or not
            if (index < split.length) {
                //checking if there are 2 operands (if comma is found then 2 operands)
                int pos = split[index].indexOf(',');
                // POS>0==0 because if not found index return -1
                if (pos >= 0) {
                    //Take from 0 to Pos-1 M,
                    symbols[0] = split[index].substring(0, pos);
                    //Take from pos and next one ,X
                    symbols[1] = split[index].substring(pos + 1);
                } else {//else if only one operand
                    // COMPR T
                    symbols[0] = split[index];
                    symbols[1] = null;
                }
            } else {//else if index>=split.length which means no operands
                symbols[0] = symbols[1] = null;
            }

            return new Statement(label, operation, extended, symbols);
        }
    }

    public String label() {
        return Label;
    }

    public String operation() {
        return Operation;
    }

    public String operand1() {
        return Symbols[0];
    }

    public String operand2() {
        return Symbols[1];
    }

    public boolean isComment() {
        return _comment != null;
    }

    public boolean isExtended() {
        return Extend;
    }

    public void setLocation(int loc) {
        _location = loc;
    }

    public int location() {
        return _location;
    }

    @Override
    //method to make the statement into a line again
    public String toString() {
        // Make 4 digit string
        String s = String.format("%1$04X", _location) + "\t";

        if (isComment()) {
            s += ".\t" + _comment;
        } else {
            if (Label != null) {
                s += Label;
            }

            s += "\t";

            if (Extend) {
                s += '+';
            }

            s += Operation + "\t";

            if (Symbols != null) {
                if (Symbols[0] != null) {
                    s += Symbols[0];
                }

                if (Symbols[1] != null) {
                    s += "," + Symbols[1];
                }
            }
        }

        return s;
    }

    @Override
    public int compareTo(Object o) {
        return Operation.compareTo((String) o);
    }
}
