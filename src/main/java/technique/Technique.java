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

    public String toString() {

        return type + " " + name + " год:" + year;
    }

}
