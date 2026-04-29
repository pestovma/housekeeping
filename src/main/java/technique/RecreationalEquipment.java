package technique;

public class RecreationalEquipment extends Technique {
    public RecreationalEquipment(String name, int year) {

        super("Техника для отдыха", name, year);
    }

    public void use() {

        System.out.println(name+" используется для отдыха");
    }

}
