package buildings;

import enums.WallMaterial;

public abstract class BaseBuilding {
    protected String name;
    protected int year;
    protected WallMaterial material;
    protected int floors;
    protected double area;

    public  BaseBuilding(String name, int year, WallMaterial material, int floors, double area) {

        this.name = name;
        this.year = year;
        this.material = material;
        this.floors = floors;
        this.area = area;
    }

    public void repair() {

        System.out.println(name + " отремонтировано");
    }

    public void clean() {

        System.out.println(name + " убрано");
    }

    public String getName() {

        return name;
    }

    public String toString() {

        return name + " год:" + year + " материал:" + material +
                " этажей:" + floors + " площадь:" + area;
    }
}


