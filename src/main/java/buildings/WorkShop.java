package buildings;

import enums.WallMaterial;

public class WorkShop extends BaseBuilding {
    public WorkShop(int year, WallMaterial material, int floors, double area) {

        super("Мастерская", year, material, floors, area);
    }

}
