package features.oop.service;

import features.oop.sub.ManagerDTO;
import features.oop.sub.StudentDTO;
import features.oop.sub.TeacherDTO;
import features.oop.sup.PersonDTO;
import features.oop.util.Flag;

/*
- 배열관리
- 해당배열에는 xxxxDTO 담을 것
- search, delete, insert, update 요청을 처리
*/
public class OopService {
    private PersonDTO[] ary;
    private int idx;

    public OopService() {
        ary = new PersonDTO[10];
        idx = 0;
    }

    public void setAry(PersonDTO person) {
        ary[idx++] = person;
    }

    /*
     * Q)
     * flag : 1 -> student, 2 -> teacher, 3 -> manager
     * params : flag, name, age, address, comm(ssn, subject, jept)
     * 생성된 객체를 setAry() 메서드 호출을 통해 관리
     */
    public void makePerson(Flag flag, String name, int age, String address, String comm) {
        System.out.println("Debug >>>> enum Flag : " + flag);
        System.out.println("Debug >>>> enum Flag getxxxx : " + flag.getFlag());

        // switch (flag.getFlag()) {
        //     case 1:
        //         PersonDTO stu = StudentDTO.builder()
        //                 .name(name)
        //                 .age(age)
        //                 .address(address)
        //                 .ssn(comm)
        //                 .build();
        //         setAry(stu);
        //         break;
        //     case 2:
        //         PersonDTO tea = TeacherDTO.builder()
        //                 .name(name)
        //                 .age(age)
        //                 .address(address)
        //                 .subject(comm)
        //                 .build();
        //         setAry(tea);
        //         break;
        //     case 3:
        //         PersonDTO man = ManagerDTO.builder()
        //                 .name(name)
        //                 .age(age)
        //                 .address(address)
        //                 .dept(comm)
        //                 .build();
        //         setAry(man);
        //         break;
        //     default:
        //         System.err.println("input error");
        //         break;
        // }
        PersonDTO per = (flag.getFlag() == 1) ? StudentDTO.builder()
                        .name(name).age(age).address(address).ssn(comm)
                        .build() 
                        : (flag.getFlag() == 2) ? TeacherDTO.builder()
                        .name(name).age(age).address(address).subject(comm)
                        .build() 
                        : (flag.getFlag() == 3) ? ManagerDTO.builder()
                        .name(name).age(age).address(address).dept(comm)
                        .build() 
                        : null;
        setAry(per);
    }

    public PersonDTO [] getAry() {
        return ary;
    }

    public PersonDTO findPerson(String name) {
        PersonDTO person = null;
        for(PersonDTO data : ary) {
            if( data == null) {
                break;
            } else if(data.getName().equals(name)) {
                person = data;
                break;
            }
        }
        return person;
    }
}
