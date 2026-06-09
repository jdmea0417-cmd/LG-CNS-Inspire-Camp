package features.oop.encapsulation;

public class SamsungTV implements TV{

    private static SamsungTV instance;

    private SamsungTV() {
        System.out.println("debug >>>> samsung tv instance");
    }
    public static SamsungTV getInstance() {
        if(instance == null) {
            instance = new SamsungTV();
        }
        return instance;
    }

    @Override
    public void powerOn() {
        System.out.println("samsung tv powerOn");
    }

    @Override
    public void powerOff() {
        System.out.println("samsung tv powerOff");
    }

    public void specialize() {
        System.out.println("samsung tv specializ logic");
    }
}
