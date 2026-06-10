import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import features.generics.ResponseTemplate;
import features.oop.sub.ManagerDTO;
import features.oop.sub.StudentDTO;
import features.oop.sub.TeacherDTO;
import features.oop.sup.PersonDTO;

public class CollectionApp {
    /*
    Collection API
    - List(중복허용, 순서존재, 요소를 객체만 담고, 가변길이)
    - Set(중복허용 X, 순서존재 X, 요소를 객체만 담고, 가변길이)
    - Map( {key : value} )

    Wrapper Class(Boxing, UnBoxing)
    8 primitive type(int -> Interger, Interger -> int, etc....)

    Generics (파라미터 타입)
    T - type
    E - element(collection)
    K - key
    V - value
    N - number
    */
    public static void main(String[] args) {
        System.out.println(">>>> Array");
        int [] intAry = {1, 2, 3, 4, 5};
        System.out.println(intAry.length);
        System.out.println( Arrays.toString(intAry) );

        System.out.println("\n>>>> Collection API ");

        // add(), get(), remove(), size()
        List<String> list = new Vector<String>();
        // Wrapper Class
        list.add("String");
        list.add("String");
        System.out.println(list);
        for(int idx=0 ; idx < list.size() ; idx++) {
            String data = list.get(idx);
            System.out.println(data);
        }
        System.out.println("\n>>>> ArrayList Elements xxxxDTO");

        List<PersonDTO> personList = new ArrayList<>();

        StudentDTO student = StudentDTO.builder().name("inspire")
        .build();
        TeacherDTO teacher = TeacherDTO.builder().name("jslim")
        .build();
        ManagerDTO manager = ManagerDTO.builder().name("lgcns")
        .build();

        personList.add(student);
        personList.add(teacher);
        personList.add(manager);

        for(PersonDTO person : personList){
            System.out.println(person.personInfo());
        }

        System.out.println("\n>>>> Stream API");
        personList.stream()
            .filter( person -> person.getName().length() > 5 )
            .forEach( person -> System.out.println(person.personInfo()));



        System.out.println("\n>>>> Generics");

        ResponseTemplate<Integer> errCode = new ResponseTemplate<>();
        errCode.setCode(200);
        ResponseTemplate<String> errMsg = new ResponseTemplate<>();
        errMsg.setCode("리소스 생성 완료");
        System.out.println( "code : "+errCode.getCode()+"\nmessage : "+errMsg.getCode());


        System.out.println("\n>>>> set");
        Set<String> set = new HashSet<>();
        set.add("jslim");
        set.add("inspire");
        set.add("lgcns");
        set.add("jslim");
        System.out.println(set);

        Object [] setAry = set.toArray();
        for(Object data : setAry) {
            System.out.println(data);
        }

        System.out.println("\n>>>> Map");

        List<StudentDTO> stuList = new ArrayList<>();
        List<TeacherDTO> teaList = new ArrayList<>();
        List<ManagerDTO> manList = new ArrayList<>();

        //List 중 PersonDTO를 상속받는 List만 Map에 담는다.
        Map<String, List<? extends PersonDTO>> map = new HashMap<>();
        map.put("stu", stuList);
        map.put("tea", teaList);
        map.put("man", manList);

        //와일드카드를 사용하지 않을 시, Generics의 문법이 풀려버린다.
        //즉, object로 받아서 casting 필요
        List<? extends PersonDTO> lst = map.get("tea");
        for(PersonDTO per : lst) {
            System.out.println( per.personInfo() );
        }
    }
}
