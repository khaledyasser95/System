package Table;

import java.io.Serializable;

/**
 * Created by ICY on 3/14/2017.
 */
public class Statement implements Serializable, Comparable {
    private final String Label;
    private final String Operation;
    private final String[] Symbols;
    private final String _comment;
    private final boolean Extend;
    private int _location;

    private Statement(String label, String operation, boolean extended, String[] symbols, String comment) {
        Label = label;
        Operation = operation;
        Extend = extended;
        Symbols = symbols;
        _comment = comment;
    }

    public Statement(String label, String operation, boolean extended, String[] symbols) {
        this(label, operation, extended, symbols, null);
    }

    public Statement(String comment) {
        this(null, ".", false, null, comment);
    }
    // Split the Line LABEL OPCODE OPERAND
    public static Statement parse(String statement) {
        String[] split = statement.trim().split("\t");

        if (split[0].compareTo(".") == 0) {
            //check if comment
            return new Statement(statement.substring(statement.indexOf('.') + 1));
        } else {
            // If not so it will Statement to be fetched
            String label, operation;
            String[] symbols;
            boolean extended = false;
            int index = 0;
            //Check if there is label
            if (split.length == 3) {
                label = split[index++];
            } else {
                label = null;
            }
            //Check Operation it will be in 1 or 2 anyway
            operation = split[index++];
            if (operation.charAt(0) == '+') {
                extended = true;
                operation = operation.substring(1);
            }
            // OPERAND
            symbols = new String[2];
            // ADD M,X example
            if (index < split.length) {
                int pos = split[index].indexOf(',');
                // POS>0==0 because if not found index return -1
                if (pos >= 0) {
                    //Take from 0 to Pos-1
                    symbols[0] = split[index].substring(0, pos);
                    //Take from pos and next one
                    symbols[1] = split[index].substring(pos + 1);
                } else {
                    // COMPR T
                    symbols[0] = split[index];
                    symbols[1] = null;
                }
            } else {
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
