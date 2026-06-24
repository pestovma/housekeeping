package technique;


public abstract class Technique {
    protected String type;
    protected String name;
    protected int year;

    public Technique(String type, String name, int year) {

        this.type = type;
        this.name = name;
        this.year = year;
    }


    public abstract void use();

    public void repair() {

        System.out.println(type + " " + name + " отремонтирован");
    }

    public String getType() {

        return type;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public String toString() {

        return type + " " + name + " год:" + year;
    }

    public static void techniqueType() {
        System.out.println("\nВыбор вида техники");
        System.out.println("----------------------");
        System.out.println("1 Автомобиль");
        System.out.println("2 Техника для отдыха");
        System.out.println("3 Хозяйственная техника");
        System.out.println("0 Назад");
    }

}
