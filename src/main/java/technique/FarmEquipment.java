package technique;

public class FarmEquipment extends Technique{
    public FarmEquipment(String name, int year) {

        super("Хозяйственная техника", name, year);
    }

    public void use() {

        System.out.println("Используется для хозяйственных работ");
    }

}
