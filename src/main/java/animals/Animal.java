package animals;

import enums.Gender;

public abstract class Animal {
    protected String type;
    protected String name;
    protected Gender gender;
    protected int age;

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

    public String getType() {

        return type;
    }

    public String getName() {

        return name;
    }

    public String toString() {

        return type + " имя:" + name + " возраст:" + age;
    }

}
