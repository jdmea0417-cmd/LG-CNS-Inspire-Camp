package features.oop.abstraction;

public class Bird extends Animal implements Fly{
    @Override
    public void fly() {
        System.out.println("Bird flying");
    }
    @Override
    public void takeOff() {
        System.out.println("Bird takeOff");
    }
    @Override
    public void landing() {
        System.out.println("Bird landing");
    }
}
