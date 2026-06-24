package buildings;

import enums.WallMaterial;

public class ChickenCoop extends BaseBuilding {
    public ChickenCoop(int year, WallMaterial material, int floors, double area) {

        super("Птичник", year, material, floors, area);
    }

}
