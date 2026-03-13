package buildings;

import enums.WallMaterial;

public class ResidentialHouse extends BaseBuilding {
    public ResidentialHouse(int year, WallMaterial material, int floors, double area) {

        super("Жилой дом", year, material, floors, area);
    }



}
