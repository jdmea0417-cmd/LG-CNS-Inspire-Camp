package features.oop.factory;

import features.oop.encapsulation.LgTV;
import features.oop.encapsulation.SamsungTV;
import features.oop.encapsulation.TV;

/*
- factory method pattern

singleton pattern
- 인스턴스를 1개로 유지하는 방법
- 생성자의 접근지정자를 private
- 팩토리 내부에서 자기 자신의 객체를 생성
- 참조할 수 있도록 반환
*/
public class BeanFactory {

    private SamsungTV samsung;
    private LgTV lg;

    private static BeanFactory instance;
    private BeanFactory() {
        samsung = SamsungTV.getInstance();
        lg = LgTV.getInstance();
    }
    public static BeanFactory getInstance() {
        if( instance == null ) {
            instance = new BeanFactory();
        }
        return instance;
    }

    public TV getBean(String brand) {
        return (brand.equalsIgnoreCase("lg")) ? lg : samsung;
    }
}
