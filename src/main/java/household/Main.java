package household;


import java.util.*;

import model.Household;
import buildings.*;
import animals.*;
import technique.*;
import enums.*;


public class Main {
    public static Scanner sc = new Scanner(System.in);
    static Household household = new Household();

    public static void main(String[] args) {
        createBuildings();
        mainMenu();

    }

    static void mainMenu() {
        while (true) {
            System.out.println("\n===== ГЛАВНОЕ МЕНЮ =====");
            System.out.println("1 Строения");
            System.out.println("2 Животные");
            System.out.println("3 Техника");
            System.out.println("0 Выход");
            try {
                int choice = sc.nextInt();
                if (choice != 1 && choice != 2 && choice != 3 && choice != 0) {
                    throw new InvalidMenuChoiceException("Неверный выбор. Повторите");
                }
                switch (choice) {
                    case 1 -> buildingMenu();
                    case 2 -> animalMenu();
                    case 3 -> techniqueMenu();
                    case 0 -> System.exit(0);
                }
            } catch (InvalidMenuChoiceException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Введите число.");
                sc.nextLine();
            }
        }
    }

    static void createBuildings() {

        household.buildings.add(new ResidentialHouse(2000, WallMaterial.WOOD, 2, 120));
        household.buildings.add(new WorkShop(2005, WallMaterial.BRICK, 1, 50));
        household.buildings.add(new Garage(2010, WallMaterial.METAL, 1, 30));
        household.buildings.add(new BathHouse(2012, WallMaterial.WOOD, 1, 35));
        household.buildings.add(new GoatHouse(2015, WallMaterial.BRICK, 1, 30));
        household.buildings.add(new ChickenCoop(2018, WallMaterial.WOOD, 1, 12));
    }

    static void buildingMenu() throws InvalidMenuChoiceException {


        boolean exit = false;
        while (!exit) {
            System.out.println("\nСТРОЕНИЯ");
            for (int i = 0; i < household.buildings.size(); i++)
                System.out.println((i + 1) + " " + household.buildings.get(i).getName());
            int n = household.buildings.size();
            System.out.println((n + 1) + " Список строений");
            System.out.println("0 Назад");
            try {
                int choice = sc.nextInt();
                if (choice == (n + 1)) {
                    household.buildings.forEach(System.out::println);
                    System.out.println("Нажмите Enter для продолжения...");
                    new Scanner(System.in).nextLine();
                    continue;
                }
                if (choice == 0) {
                    exit = true;
                    continue;
                }
                if (choice < 1 || choice > household.buildings.size()) {
                    throw new InvalidMenuChoiceException("Неверный выбор строения. Повторите");
                }
                BaseBuilding b = household.buildings.get(choice - 1);
                System.out.println("1 Ремонт");
                System.out.println("2 Уборка");
                int action = sc.nextInt();
                if (action == 1) {
                    b.repair();
                } else if (action == 2) {
                    b.clean();
                } else throw new InvalidMenuChoiceException("Неверный выбор действия. Повторите");
            } catch (InvalidMenuChoiceException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Введите число.");
                sc.nextLine();
            }
        }

        System.out.println("0 Назад");
    }

    static void animalMenu() {
        while (true) {
            System.out.println("\nЖИВОТНЫЕ");
            System.out.println("1 Добавить");
            System.out.println("2 Накормить");
            System.out.println("3 Напоить");
            System.out.println("4 Выгулять");
            System.out.println("5 Список всех");
            System.out.println("6 Список коз");
            System.out.println("7 Список кур");
            System.out.println("8 Выбытие");
            System.out.println("0 Назад");
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> addAnimal();
                    case 2 -> findAnimal().feed();
                    case 3 -> findAnimal().drink();
                    case 4 -> findAnimal().walk();
                    case 5 -> household.animals.forEach(System.out::println);
                    case 6 -> household.animals.stream().filter(a -> a instanceof Goat).forEach(System.out::println);
                    case 7 -> household.animals.stream().filter(a -> a instanceof Chicken).forEach(System.out::println);
                    case 8 -> removeAnimal();
                    case 0 -> mainMenu();
                    default -> throw new InvalidMenuChoiceException("Неверный выбор. Повторите");
                }
            } catch (InvalidMenuChoiceException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Введите число.");
                sc.nextLine();
            }
        }
    }


    static void addAnimal() {
        Animal.animalType();
        try {
            int type = sc.nextInt();
            if (type != 1 && type != 2 && type != 0) {
                throw new InvalidMenuChoiceException("Неверный выбор животного. Повторите");
            }
            if (type == 0) {
                animalMenu();
            }
            sc.nextLine();
            System.out.println("Имя:");
            String name = sc.nextLine();
            System.out.println("Пол (MALE/FEMALE)");
            String genderInput = sc.next();
            Gender gender;
            try {
                gender = Gender.valueOf(genderInput.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new InvalidInputException("Неверный пол. Используйте MALE или FEMALE.");
            }
            System.out.println("Возраст:");
            int age = sc.nextInt();
            if (age < 0) {
                throw new InvalidInputException("Возраст не может быть отрицательным.");
            }
            if (type == 1) {
                household.animals.add(new Goat(name, gender, age));
                System.out.println("Коза по имени " + name + " добавлена");
            } else if (type == 2) {
                household.animals.add(new Chicken(name, gender, age));
                System.out.println("Курица по имени " + name + " добавлена");
            }
        } catch (InvalidMenuChoiceException | InvalidInputException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода. Введите корректные данные.");
            sc.nextLine();
        }
    }

    static Animal findAnimal() throws InvalidMenuChoiceException {
        String type = "";
        boolean exit = false;
        while (!exit) {
            Animal.animalType();
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        type = "Коза";
                        exit = true;
                    }
                    case 2 -> {
                        type = "Курица";
                        exit = true;
                    }
                    case 0 -> animalMenu();
                    default -> throw new InvalidMenuChoiceException("Неверный выбор. Повторите");
                }
            } catch (InvalidMenuChoiceException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Введите число.");
                sc.nextLine();
            }
        }
        sc.nextLine();

        System.out.println("Имя?");
        String name = sc.nextLine();

        for (Animal a : household.animals) {
            if (a.getType().equalsIgnoreCase(type) && a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        throw new InvalidMenuChoiceException("Животное не найдено");
    }

    static void removeAnimal() {

        String type = "";
        boolean exit = false;
        while (!exit) {

            Animal.animalType();
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        type = "Коза";
                        exit = true;
                    }
                    case 2 -> {
                        type = "Курица";
                        exit = true;
                    }
                    case 0 -> animalMenu();
                    default -> throw new InvalidMenuChoiceException("Неверный выбор. Повторите");
                }
            } catch (InvalidMenuChoiceException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Введите число.");
                sc.nextLine();
            }
        }
        sc.nextLine();
        System.out.println("Имя?");
        String name = sc.nextLine();
        String tempType = type;

        boolean removed = household.animals.removeIf(a ->
                a.getType().equalsIgnoreCase(tempType) && a.getName().equalsIgnoreCase(name));

        if (removed) {
            System.out.println("Животное выбыло");
        } else {
            System.out.println("Такого животного нет");
        }
    }

    static void techniqueMenu() {
        sc.nextLine();
        boolean exit = false;
        while (!exit) {
            System.out.println("\nТЕХНИКА");
            System.out.println("1 Добавить");
            System.out.println("2 Использовать");
            System.out.println("3 Ремонт");
            System.out.println("4 Список");
            System.out.println("5 Выбытие");
            System.out.println("0 Назад");
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> addTechnique();
                    case 2 -> findTechnique().use();
                    case 3 -> findTechnique().repair();
                    case 4 -> household.techniques.forEach(System.out::println);
                    case 5 -> removeTechnique();
                    case 0 -> mainMenu();
                    default -> throw new InvalidMenuChoiceException("Неверный выбор. Повторите");
                }
            } catch (InvalidMenuChoiceException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Введите число.");
                sc.nextLine();
            }
        }
    }

    static void addTechnique() {

        Technique.techniqueType();
        try {
            int type = sc.nextInt();
            if (type < 1 || type > 3) {
                throw new InvalidMenuChoiceException("Неверный выбор техники. Повторите");
            }
            System.out.println("Название:");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.println("Год:");
            int year = sc.nextInt();
            if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR) + 1) {
                throw new InvalidInputException("Некорректный год.");
            }
            if (type == 1) {
                household.techniques.add(new Car(name, year));
                System.out.println("Автомобиль" + " " + name + " добавлен");
            } else if (type == 2) {
                household.techniques.add(new RecreationalEquipment(name, year));
                System.out.println("Техника для отдыха" + " " + name + " добавлена");

            } else if (type == 3) {
                household.techniques.add(new FarmEquipment(name, year));
                System.out.println("Хозяйственная техника" + " " + name + " добавлена");
            } else if (type == 0) {
                techniqueMenu();

            }
        } catch (InvalidMenuChoiceException | InvalidInputException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода. Введите корректные данные.");
            sc.nextLine();
        }
    }

    public static Technique findTechnique() throws InvalidMenuChoiceException {

        String type = "";
        boolean exit = false;
        while (!exit) {
            Technique.techniqueType();
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        type = "Автомобиль";
                        exit = true;
                    }
                    case 2 -> {
                        type = "Техника для отдыха";
                        exit = true;
                    }
                    case 3 -> {
                        type = "Хозяйственная техника";
                        exit = true;
                    }
                    case 0 -> techniqueMenu();
                    default -> throw new InvalidMenuChoiceException("Неверный выбор. Повторите");
                }
            } catch (InvalidMenuChoiceException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Введите число.");
                sc.nextLine();
            }
        }
        sc.nextLine();
        System.out.println("Название?");
        String name = sc.nextLine();

        for (Technique t : household.techniques) {
            if (t.getType().equalsIgnoreCase(type) && t.getName().equalsIgnoreCase(name)) {
                return t;
            }
        }
        throw new InvalidMenuChoiceException("Техника не найдена");

    }


    static void removeTechnique() {

        String type = "";
        boolean exit = false;
        while (!exit) {
            System.out.println("\nКакой вид?");
            Technique.techniqueType();
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1 -> {
                        type = "Автомобиль";
                        exit = true;
                    }
                    case 2 -> {
                        type = "Техника для отдыха";
                        exit = true;
                    }
                    case 3 -> {
                        type = "Хозяйственная техника";
                        exit = true;
                    }
                    case 0 -> techniqueMenu();
                    default -> throw new InvalidMenuChoiceException("Неверный выбор. Повторите");
                }
            } catch (InvalidMenuChoiceException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Введите число.");
                sc.nextLine();
            }
        }
        sc.nextLine();
        System.out.println("Название?");
        String name = sc.nextLine();
        String tempType = type;
        boolean removed = household.techniques.removeIf(t ->
                t.getType().equalsIgnoreCase(tempType) && t.getName().equalsIgnoreCase(name));

        if (removed) {
            System.out.println("Техника выбыла");
        } else {
            System.out.println("Такой техники нет");
        }
    }
}


