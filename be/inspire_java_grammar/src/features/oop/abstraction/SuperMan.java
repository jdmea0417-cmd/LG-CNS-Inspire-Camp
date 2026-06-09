package features.oop.abstraction;

public class SuperMan extends Animal implements Fly{

    @Override
    public void fly() {
        System.out.println("SuperMan flying");
    }
    @Override
    public void takeOff() {
        System.out.println("SuperMan takeOff");
    }
    @Override
    public void landing() {
        System.out.println("SuperMan landing");
    }

}
