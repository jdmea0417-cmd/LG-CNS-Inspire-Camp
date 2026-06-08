import features.oop.service.OopService;
import features.oop.sub.ManagerDTO;
import features.oop.sub.StudentDTO;
import features.oop.sub.TeacherDTO;
import features.oop.sup.PersonDTO;

public class OopApp {
    public static void main(String[] args) {
        // PersonDTO person = PersonDTO.builder()
        // .name("임섭순")
        // .age(20)
        // .address("서울")
        // .build();
        // System.out.println(person);

        // StudentDTO student = StudentDTO.builder()
        // .name(null)
        // .age(0)
        // .address(null)
        // .ssn(null)
        // .build();
        // System.out.println(student.studentInfo());

        // TeacherDTO teacher = TeacherDTO.builder()
        // .name(null)
        // .age(0)
        // .address(null)
        // .subject(null)
        // .build();
        // System.out.println(teacher.teacherInfo());

        // Q) 학생객체, 강사객체, 매니저객체 여러개를 생성해서 꾸러미(배열)에 담고 싶다면?
        // 타입의 다형성
        // PersonDTO[] perAry = new PersonDTO[10];
        // System.out.println(perAry);
        StudentDTO student = StudentDTO.builder()
                .name("박도연")
                .age(20)
                .address("대전")
                .ssn("2026")
                .build();

        // System.out.println(((StudentDTO) student).studentInfo());
        // System.out.println(student.personInfo());

        PersonDTO teacher = TeacherDTO.builder()
                .name("임정섭")
                .age(20)
                .address("서울")
                .subject("자바")
                .build();

        // System.out.println(((TeacherDTO) teacher).teacherInfo());
        // System.out.println(teacher.personInfo());

        // System.out.println(">>>> 타입의 다형성을 배열적용한다면? ");
        // perAry[0] = student;
        // perAry[1] = teacher;

        // System.out.println(">>>> Quiz) 배열 요소를 추출하여 정보를 출력하라......");
        /*
         * runtime 시점에 타입체크를 도와주는 논리연산 : instanceof
         */
        // for(int idx = 0; idx < perAry.length ; idx++){
        // PersonDTO person = perAry[idx];
        // if( person == null) {
        // break;
        // } else if( person instanceof StudentDTO ) {
        // System.out.println( ((StudentDTO)student).studentInfo() );
        // } else if( person instanceof TeacherDTO) {
        // System.out.println( ((TeacherDTO)teacher).teacherInfo() );
        // }
        // }
        // for(int idx= 0; idx < perAry.length; idx++) {
        // PersonDTO person = perAry[idx];
        // if(person == null) {
        // break;
        // }
        // System.out.println(person.personInfo());
        // }

        /*
         * Q)
         * - ManagerDTO 상속관계를 마무리하고
         * - OopApp 코드에서 ManagerDTO 객체를 생성하여
         * - 배열에 담고
         * - 정보를 출력하는 코드를 구현
         */
        PersonDTO manager = ManagerDTO.builder()
                .name("김혜림")
                .age(20)
                .address("서울")
                .dept("교육운영팀")
                .build();

        // perAry[2] = manager;

        // for(int idx = 0; idx < perAry.length; idx++) {
        // PersonDTO person = perAry[idx];
        // if(person == null){
        // break;
        // }
        // System.out.println(person.personInfo());
        // }

        // System.out.println(">>>> enhanced loop ~ overriding");
        // for(PersonDTO person : perAry) {
        // if(person == null){
        // break;
        // }
        // // 부모타입으로 유일하게 자식 구성요소에 접근하는 방법?
        // // overriding
        // System.out.println(person.personInfo());
        // }

        // case 03.
        // 매개변수의 다형성

        OopService service = new OopService();

        service.setAry(student);
        service.setAry(teacher);
    }
}
