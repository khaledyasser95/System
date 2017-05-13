package Table.Objectcode;

/**
 * Created by ICY on 5/13/2017.
 */
public class Refine implements RecordCollector  {
    private final String Name;
    private final int Nameother;

    public Refine(String name, int nameother) {
        Name = name;
        Nameother = nameother;
    }
    @Override
    public String toObjectProgram() {
        //%number= which statement to insert
        // $type = type of insertions
        // -6 RIGHT PADDING
        return  String.format("R^%06X^%06X^", Name);
    }
}
