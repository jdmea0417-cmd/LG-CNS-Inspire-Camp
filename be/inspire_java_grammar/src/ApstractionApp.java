import features.oop.abstraction.Animal;
import features.oop.abstraction.Bird;
import features.oop.abstraction.Fly;
import features.oop.abstraction.SuperMan;

public class ApstractionApp {
    public static void main(String[] args) {
        // AbstractClass demo = new AbstractClass() ; <- 객체 생성 불가
        // AbstractInterface demo = new AbstractInterface() ; <- 객체 생성 불가

        Animal [] ary = new Animal[2];
        ary[0] = new Bird();
        ary[1] = new SuperMan();

        for(Animal animal : ary) {
            animal.eating("");
            ((Fly) animal).fly();
            ((Fly) animal).landing();
            ((Fly) animal).takeOff();
        }
    }
}
