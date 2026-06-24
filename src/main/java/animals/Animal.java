package animals;

import enums.Gender;

public class Animal {
    protected String type;
    protected String name;
    protected Gender gender;
    protected int age;
    protected int q;

    public Animal(String type, String name, Gender gender, int age) {

        this.type = type;
        this.name = name;
        this.gender = gender;
        this.age = age;

    }
    public void feed() {

        System.out.println(type + " " + name + " накормлен");
    }

    public void drink() {

        System.out.println(type + " " + name + " напоен");
    }

    public void walk() {

        System.out.println(type + " " + name + " выгулян");
    }
    public void nothing() {

        System.out.println(type + " "  + " не найдено");
    }

    public String getType() {

        return type;
    }

    public String getName() {

        return name;
    }

    public String toString() {

        return type + " имя:" + name + " возраст:" + age;
    }
    public static void animalType(){
        System.out.println("\nВыбор вида животного");
        System.out.println("-----------------------");
        System.out.println("1 Козы");
        System.out.println("2 Курицы");
        System.out.println("0 Назад");
    }

}
