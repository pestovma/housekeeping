package household;

import java.util.*;   
import model.Household;
import buildings.*;
import animals.*;
import technique.*;
import enums.*;



public class Main {
    static Scanner sc = new Scanner(System.in);
    static Household household = new Household();

    public static void main(String[] args) {
        createBuildings();
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
        household.buildings.add(new ChickenCoop(2018, WallMaterial.WOOD, 1, 13));
        household.buildings.add(new ChickenCoop(2018, WallMaterial.WOOD, 1, 14));
        household.buildings.add(new ChickenCoop(2018, WallMaterial.WOOD, 1, 15));
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
            static void animalMenu () {
                boolean exit = false;
                while (!exit) {
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
                            case 6 ->
                                    household.animals.stream().filter(a -> a instanceof Goat).forEach(System.out::println);
                            case 7 ->
                                    household.animals.stream().filter(a -> a instanceof Chicken).forEach(System.out::println);
                            case 8 -> removeAnimal();
                            case 0 -> exit = true;
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


            static void addAnimal () {

                System.out.println("1 Коза");
                System.out.println("2 Курица");
                try {
                    int type = sc.nextInt();
                    if (type != 1 && type != 2) {
                        throw new InvalidMenuChoiceException("Неверный выбор животного. Повторите");
                    }
                    System.out.println("Имя:");
                    String name = sc.next();
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

            static Animal findAnimal () {

                System.out.println("Какой вид?");
                String type = sc.next();

                System.out.println("Имя?");
                String name = sc.next();

                for (Animal a : household.animals)
                    if (a.getType().equalsIgnoreCase(type) &&
                            a.getName().equalsIgnoreCase(name))
                        return a;

                System.out.println("Животное не найдено");
                return household.animals.get(0);
            }

            static void removeAnimal () {

                System.out.println("Какой вид?");
                String type = sc.next();

                System.out.println("Имя?");
                String name = sc.next();

                household.animals.removeIf(a ->
                        a.getType().equalsIgnoreCase(type) &&
                                a.getName().equalsIgnoreCase(name));

                System.out.println("Животное выбыло");
            }

            static void techniqueMenu () {

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
                            case 0 -> exit = true;
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

            static void addTechnique () {

                System.out.println("1 Автомобиль");
                System.out.println("2 Техника отдыха");
                System.out.println("3 Хозяйственная техника");
                try {
                    int type = sc.nextInt();
                    if (type < 1 || type > 3) {
                        throw new InvalidMenuChoiceException("Неверный выбор техники. Повторите");
                    }
                    System.out.println("Название:");
                    String name = sc.next();
                    System.out.println("Год:");
                    int year = sc.nextInt();
                    if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR) + 1) {
                        throw new InvalidInputException("Некорректный год.");
                    }
                    if (type == 1)
                        household.techniques.add(new Car(name, year));
                    else if (type == 2)
                        household.techniques.add(new RecreationalEquipment(name, year));
                    else if (type == 3)
                        household.techniques.add(new FarmEquipment(name, year));
                } catch (InvalidMenuChoiceException | InvalidInputException e) {
                    System.out.println(e.getMessage());
                } catch (InputMismatchException e) {
                    System.out.println("Ошибка ввода. Введите корректные данные.");
                    sc.nextLine();
                }
            }

            static Technique findTechnique () {

                System.out.println("Какой вид?");
                String type = sc.next();

                System.out.println("Название?");
                String name = sc.next();

                for (Technique t : household.techniques)
                    if (t.getType().equalsIgnoreCase(type) &&
                            t.getName().equalsIgnoreCase(name))
                        return t;

                System.out.println("Техника не найдена");
                return household.techniques.get(0);
            }

            static void removeTechnique () {

                System.out.println("Какой вид?");
                String type = sc.next();

                System.out.println("Название?");
                String name = sc.next();

                household.techniques.removeIf(t ->
                        t.getType().equalsIgnoreCase(type) &&
                                t.getName().equalsIgnoreCase(name));

                System.out.println("Техника выбыла");
            }
        }

