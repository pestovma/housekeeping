package buildings;

import enums.WallMaterial;

public class Garage extends BaseBuilding {
            public Garage(int year, WallMaterial material, int floors, double area) {

            super("Гараж", year, material, floors, area);
        }
    }
