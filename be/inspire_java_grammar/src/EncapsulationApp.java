import features.oop.encapsulation.LgTV;
import features.oop.encapsulation.SamsungTV;
import features.oop.encapsulation.TV;
import features.oop.factory.BeanFactory;

public class EncapsulationApp {
    public static void main(String[] args) {
        BeanFactory factory = BeanFactory.getInstance();
        // BeanFactory factory02 = BeanFactory.getInstance();
        // if( factory01 == factory02 ) {
        //     System.out.println("factory01 == factory02 ");
        // }
        TV tv = factory.getBean("lg");
        tv.powerOn();
        tv.powerOff();
    }
}
