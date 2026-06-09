package features.oop.encapsulation;

public class LgTV implements TV{

    private static LgTV instance;

    private LgTV() {
        System.out.println("debug >>>> lg tv instance");
    }
    public static LgTV getInstance() {
        if( instance == null ) {
            instance = new LgTV();
        }
        return instance;
    }
    @Override
    public void powerOn() {
        System.out.println("lg tv powerOn");
    }

    @Override
    public void powerOff() {
        System.out.println("lg tv powerOff");
    }

    public void core() {
        System.out.println("lg tv core logic");
    }
}
