package Table.Objectcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ICY on 4/12/2017.
 */
public class TEXT implements RecordCollector {
    private final int startAddress;
    private int length;
    private final List<String> objectCodes;

    public static final int MAX_LENGTH = 0x20;

    public TEXT(int startAddress) {
        this.startAddress = startAddress;
        length = 0;
        objectCodes = new ArrayList<>();

    }
    public boolean add(String objectCode) {
        if (objectCode.length() == 0) {
            return true;
        } else if (length + objectCode.length() / 2 <= MAX_LENGTH) {
            objectCodes.add(objectCode);
            length += objectCode.length() / 2;

            return true;
        } else {
            return false;
        }
    }


    @Override
    public String toObjectProgram() {
        String buf = String.format("T%06X%02X", startAddress, length);

        for (String s : objectCodes) {
            buf += s;
        }

        return buf;
    }

}
