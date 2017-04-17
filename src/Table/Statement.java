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

    public static void main(String args[])
    {
        String code="FIRST  STL    RETADR";
        Statement s=parse(code);
        System.out.println("Label: "+s.Label);
        System.out.println("Label: "+s.Operation);

    }

    //constructor for a statement containing comments
    private Statement(String label, String operation, boolean extended, String[] symbols, String comment) {
        Label = label;
        Operation = operation;
        Extend = extended;

        //array moving?
        Symbols = symbols;
        _comment = comment;
    }
    //constructor for a statement without a comment
    public Statement(String label, String operation, boolean extended, String[] symbols) {

        this(label, operation, extended, symbols, null);
    }

    //constructor for a line containing only a comment
    public Statement(String comment) {
        this(null, ".", false, null, comment);
    }






    //making a parser with the given format
    /*

        The source program to be assembled must be in fixed format as follows:
        1. bytes 1–8 label
        2. 9 blank
        3. 10–15 operation code
        4. 16–17 blank
        5. 18–35 operand
        6. 36–66 comment

     */
    //this parser will check for label only in first 8bytes and leave 9 blank and so on
    public static void stickToFormatParse(String line)
    {
        private final String label;
        private final String operation;
        private final String[] symbols;
        private final String _comment;
        private final String operandsAndComment;
        private final boolean extend;
        private transient int _location;

        //if line is a comment
        if(line.trim().charAt(0)=='.')
        {
            //then whole line is a comment
            _comment=line.substring(1);
            //make a comment statement
            Statement(comment);

        }

        else
        {
            /*1. bytes 1–8 label  2. 9 blank   3. 10–15 operation code
            4. 16–17 blank  5. 18–35 operand   6. 36–66 comment
            */
                //get all from their assigned areas in the format and removing spaces
         label=line.substring(0,7).trim();
         operation=line.substring(9,14).trim();
         operands=line.substring(17,34).trim();
         _comment=line.substring(35,65).trim();

         //methods yet to be implemented
            //check if label exists in symbol table if so give error
         if(labelExists(label))
         {
             labelExistsError(label);
             //stop parsing line only but won't stop reading lines to check all errors in program, or what?
             stopParsingLine();
         }
         else //label not in symTab
         {
             //if(labelValid)  //check label written in correct format or not
             //save label in symbol table with its location
             saveLabel(label,_location);
             //check if operation doesn't exist  in op table (provided that label exists)
             //handels if operation starts with +
             if(!operationExists(operation))
             {
                 //can give line number also as operand and make errors class
                 operationNotExistError(operation);
                 //stop parsing line only but won't stop reading lines to check all errors in program, or what?
                 stopParsingLine();
             }
             else //operation  exists in op-table
             {
                 //method to check where operation is Start
                 //and if not checks format and increments location
                 //careful java is call by value
                 addLocationAccordingToFormat(operation,_location);
                 //check operands and save them to symbols:
                 //and according to format
                 symbols=new String[2];

                 int commaPosition=operandsAndComment.indexOf(',');
                 //if two operands
                 if(commaPosition>0)
                 {
                     symbols[0]=operandsAndComment.substring(0,commaPosition);
                     int dotPosition=operandsAndComment.indexOf('.');
                     //if there exists comment after operands
                     if(dotPosition>=0)
                     {
                         //2nd operand if from char after comma till dot
                         symbols[1]=operandsAndComment.substring(commaPosition+1,dotPosition);
                         _comment=operandsAndComment.substring(dotPosition+1).trim();

                     }
                     else //if no comment then take 2nd operand with no spaces
                        symbols[1]=operandsAndComment.substring(commaPosition+1).trim();
                 }
                 else //if 1 operand only
                 {

                 }


             }





         }







         //check if there is a comment after the code area
         if(_comment.indexOf('.')>=0)
         {
             _comment=_comment.substring(indexOf('.')+1);
         }
         else
         {
             _comment=null;
         }

        }


    }
    public static Statement characterParse(String line)
    {
        //didn't make them final as they were
        private  String label;
        private  String operation;
        private  String[] symbols;
        private  String _comment;
        private  String operandsAndComment;
        private  boolean extend;
        private transient int _location;

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
    boolean isOperation(String passedLabel)
    {
        if(passedLabel.charAt(0)=='+')
        {
            extend=true;
            return true;
        }
        else
        {
            extend=false;

        }
    }

//can do normal parsing through trimming line and iterate till find space then substring right and trim and so on

    //doesn't handle if there exists a comment in same line of code
    // method Split the Line LABEL OPCODE OPERAND
    public static Statement parse(String statement) {
        // array of strings each column contains a type: label,opcode,operand and comment (relatively)
        //trim ommits extra spaces and split splits string into pieces every tab \t
       // String[] split = statement.trim().split("\t");

        //spliting at a space has major problem if spaces are left in label's place

        String[] split = statement.trim().split(" ");
        //compareTo returns 0 if strings are the same order in a dictionary
        //but if there is a comment after . wouldn't it NOT return 0? tried it in separate program and didn't return zero
        //maybe what you mean is comparing split[0][0] to "." that will make more sense
        if (split[0].compareTo(".") == 0) {
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
            // this check for a label by comparing array length to 3,if two spaces " " then there
            //exists a label and an operand at least
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
            // OPERANDs
            //note there can be comments after operand(s) with no spaces
            symbols = new String[2];
            // ADD M,X example
            //checking whether there are operands or not
            if (index < split.length) {
                //checking if there are 2 operands (if comma is found then 2 operands)
                int pos = split[index].indexOf(',');
                // POS>0==0 because if not found index return -1
                if (pos >= 0) {
                    //Take from 0 to Pos-1
                    symbols[0] = split[index].substring(0, pos);
                    //Take from pos and next one till end
                    //should make it till index of '.'there may exist comments
                    //but check first if '.' exists
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
