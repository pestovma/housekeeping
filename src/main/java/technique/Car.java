package technique;

public class Car extends Technique{
    public Car(String name, int year) {

        super("Автомобиль", name, year);
    }

    public void use() {

        System.out.println("Используется для поездки");
    }

}
