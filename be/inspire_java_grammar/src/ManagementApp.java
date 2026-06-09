import java.util.Scanner;

import features.oop.service.OopService;
import features.oop.sup.PersonDTO;
import features.oop.util.Flag;

public class ManagementApp {
    public static void main(String[] args) {
        OopService service = new OopService();
        System.out.println("인스턴스 생성");
        service.makePerson(Flag.STU, "손주희", 20, "서울", "2026");
        service.makePerson(Flag.TEA, "임섭순", 20, "서울", "자바");
        service.makePerson(Flag.MAN, "김혜림", 20, "서울", "교육팀");
        System.out.println("\n>>>> 전체 출력");
        
        // Q) 아래 코드에 정의된 메서드를 구현하고 출력코드를 작성하세요
        PersonDTO [] ary = service.getAry();
        for(PersonDTO person : ary) {
            if(person == null) {
                break;
            }
            System.out.println(person.personInfo());
        }

        // Q) 이름을 키(key)로 d해서 사용자 정보를 검색하고자 한다면?
        System.out.print("\n>>>> 이름으로 검색 : ");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        PersonDTO findPerson = service.findPerson(name);
        if( findPerson == null) {
            System.out.println(name+" Found Not!!!");
        } else {
            System.out.println( findPerson.personInfo() );
        }
        
    }
}
