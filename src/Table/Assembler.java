package Table;
//just a first comment to try commits

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by ICY on 3/14/2017.
 */
public class Assembler {
    private final Map<String, OPERATION> opTable;
    private final Map<String, Integer> registerTable;
    //Q:is making symbol table final a correct move ? this means it can only be intialized in the constructor once
    //if we are going to do that then we need to put the constructor in pass 1
    private final Map<String, Integer> symbolTable;
    private int location;
    private int startAddress;
    private int firstExecAddress;
    private int Length;
    private int baseAddress;

    public Assembler() {
        opTable = Instruc.getOPERATIONTable();
        registerTable = Instruc.getRegisterTable();
        symbolTable = new HashMap<>();
        symbolTable.put(null, 0);


    }

    public static void main(String args[]) {
        try {
            Assembler asmb = new Assembler();
            asmb.run(new File("AssemblyTest.asm"), new File("copy.o"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void run(File input, File output) throws IOException {
        File intermediateFile = new File(".assembler.tmp");

        try {
            intermediateFile.createNewFile();

            pass1(input, intermediateFile);

            //  processPass2(intermediateFile, output);
        } finally {
            intermediateFile.delete();
        }
    }

    void pass1(File input, File output) throws IOException {
        try (Scanner scanner = new Scanner(input);
             FileOutputStream ostream = new FileOutputStream(output);
             ObjectOutputStream objOutputStream = new ObjectOutputStream(ostream)) {
            location = startAddress = 0;
            firstExecAddress = -1;
            while (scanner.hasNext()) {
                try {
                    Statement statement = Statement.parse(scanner.nextLine());
                    if (statement.isComment()) {
                        continue;
                    }
                    statement.setLocation(location);

                    //check Duplication of label
                    if (statement.label() != null) {
                        if (symbolTable.containsKey(statement.label())) {
                            throw new Duplicate(statement);
                        } else {
                            symbolTable.put(statement.label(), location);
                        }
                    }
                    //check operation
                    switch (statement.operation()) {
                        case "START":
                            startAddress = Integer.parseInt(statement.operand1());

                            statement.setLocation(location = startAddress);
                            break;
                        case "BYTE":
                            String s = statement.operand1();

                            switch (s.charAt(0)) {
                                case 'C':
                                    location += (s.length() - 3); // C'EOF' -> EOF -> 3 bytes
                                    break;
                                case 'X':
                                    location += (s.length() - 3) / 2; // X'05' -> 05 -> 2 half bytes
                                    break;
                            }
                            break;

                        case "WORD":
                            location += 3;

                            break;
                        case "RESW":
                            location += 3 * Integer.parseInt(statement.operand1());

                            break;
                        case "RESB":
                            location += Integer.parseInt(statement.operand1());

                            break;


                        case "END":
                            break;

                        default:
                            if (opTable.containsKey(statement.operation())) {
                                if (firstExecAddress < 0) {
                                    firstExecAddress = location;
                                }

                                switch (opTable.get(statement.operation()).getFormat()) {
                                    case "1":
                                        location += 1;
                                        break;
                                    case "2":
                                        location += 2;
                                        break;
                                    case "3/4":
                                        //if e=1 then Format 4 then 3+1=4 BYTE
                                        location += 3 + (statement.isExtended() ? 1 : 0);
                                        break;
                                }
                            } else {
                                throw new WrongOperation(statement);
                            }
                    }
                    System.out.println("Label: " + statement.label());
                    System.out.println("Opcode: " + statement.operation());
                    //  System.out.println("symbol: " + statement.operand1().toString());
                    System.out.println("Locations: " + location);
                } catch (Duplicate | WrongOperation e) {
                    System.out.println(e.getMessage());
                }
                Length = location - startAddress;

            }


        }
    }


}

