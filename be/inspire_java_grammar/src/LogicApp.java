import features.logic.LogicDemo;
import features.user.domain.UserResponseDTO;

public class LogicApp {
    public static void main(String[] args) {
        LogicDemo demo = new LogicDemo();
        // UserResponseDTO response = demo.register("jdmea@naver.com", "1234", "양성민");
        // System.out.println(response.getStatus());
        // System.out.println(response.getMessage());

        // if
        // String answer = demo.ifwoodman(3);
        // System.out.println(answer);


        // int result = demo.sumNumber(20, 10);
        // System.out.println(result);

        // int result = demo.sumRandom();
        // System.out.println("debug >>> result : "+result);

        //demo.printGugudan();
        demo.popStr("inspire lgcns camp");
    }
}
