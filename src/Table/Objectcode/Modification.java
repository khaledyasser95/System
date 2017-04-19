package Table.Objectcode;

/**
 * Created by ICY on 4/12/2017.
 */
public class Modification implements RecordCollector {
    private final int location;
    private final int length;

    public Modification(int location, int length) {
        this.location = location;
        this.length = length;
    }

    @Override
    public String toObjectProgram() {
        return String.format("M%06X%02X", location, length);
    }

}
