package buildings;

import enums.WallMaterial;

public class BathHouse extends BaseBuilding {
    public BathHouse(int year, WallMaterial material, int floors, double area) {

        super("Баня", year, material, floors, area);
    }

}
