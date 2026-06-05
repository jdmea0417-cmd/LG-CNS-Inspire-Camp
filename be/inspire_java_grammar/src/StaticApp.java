import features.stat.StaticDemo;

public class StaticApp {
    public static void main(String[] args) {
        StaticDemo demo = new StaticDemo();
        System.out.println(demo.message);
        demo.message = "메세지 변경";
        System.out.println(demo.message);
        System.out.println(StaticDemo.staticMessage);
        System.out.println(StaticDemo.PI);

        // 상수는 수정할 수 없다
        // StaticDemo.PI = 3.15;

        // StaticApp app = new StaticApp();
        // String msg = app.getMessage();
        String msg = getMessage();
        System.out.println(msg);

        int nan = (int)(Math.random() * 5) + 1;
        System.out.println("nan value : "+ nan);
    } // main end
    public static String getMessage() {
        return "이해완료!!";
    } // getMessage end
}
