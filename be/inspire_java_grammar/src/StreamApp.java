/*
Collection(List, Set, Map)
- Java Stream(코드의 가독성, 병렬처리, 유지보수 향상)

Stream API
- 원본데이터에 손상을 가하지 않는다.
- 일회성(한번 사용하면 재사용 X)
- 병렬처리(thread)로 실행의 속도가 빠르다.
- 작업을 내부적으로 처리(lambda expreassion () -> {} )

이러한 람다식을 호라용하기 위해서는 함수형 인터페이스를 이해
함수형 인터페이스
- 인터페이스가 가질 수 있는 메서드가 딱 하나인 것을 의미

Supplier : 매개변수가 없고 반환값만 가지고 있느 함수
Function : 매개변수를 받아서 처리하고 반환값이 있는 함수
Consumer : 매개변수를 받고 반환값이 없는 함수
Predicate : 매개변수를 받아서 Boolean 반환하는 함수

*/

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import features.lambda.InspireFunction;

public class StreamApp {
    public static void main(String[] args) {
        InspireFunction func01 = (x, y) -> x > y ? x : y;
        System.out.println(func01.max(100, 200));
        InspireFunction func02 = (x, y) -> x + y;
        System.out.println(func02.max(100, 200));

        System.out.println("\n>>>> Supplier");
        Supplier<String> supplier = () -> "inspire";
        System.out.println(supplier.get());

        System.out.println("\n>>>> Consumer");
        Consumer<String> consumer = (str) -> System.out.println(str.split(" ")[1]);
        consumer
                .andThen(System.out::println)
                .accept("LG CNS Inspire Camp");

        System.out.println("\n>>>> Function");
        Function<String, Integer> function = (str) -> {
            return str.length();
        };
        int len = function.apply("jslim lgcns inspire camp 5th");
        System.out.println(len);

        System.out.println("\n>>>> Predicate");
        Predicate<String> predicate = (str) -> str.equals("lgcns");
        boolean isFlag = predicate.test("lgcns");
        System.out.println(isFlag);

        List<String> brands = Arrays
                .asList("samsung", "lg", "lgcns", "inspire", "camp", "5th");

        brands.forEach(System.out::println);
        System.out.println("\n>>>> stream forEach");
        brands.stream()
            .forEach(str -> System.out.println(str));//결과는 동일하나, stream forEach가 성능면에서 우세

        /*
        Optional
        - 메서드 호출시 반환값을 확신할 수 없을 때 사용
        - null.method() : null 값 처리를 놓쳐서 발생하는 예외를 피하고자 할 때
        - npe 회피하기 위한 방법으로 사용
        주의사항)
        - 메서드의 반환타입으로만 사용(전역변수, 매개변수 x)
        - 사용의도에 맞게 사용해야함( null 할당 x)
        */
        System.out.println("\n>>>> OPtional");
        Optional<String> optional = Optional.of("jslim");
        if(optional.isPresent()) {
            System.out.println(optional.orElseGet( () -> "default" ));
            //optional.orElseThrow(() -> new Exception("예외"));
        }
    }
}
