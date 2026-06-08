package features.oop.service;

import features.oop.sub.StudentDTO;
import features.oop.sub.TeacherDTO;
import features.oop.sup.PersonDTO;

/*
- 배열관리
- 해당배열에는 xxxxDTO 담을 것
- search, delete, insert, update 요청을 처리
*/
public class OopService {
    private PersonDTO [] ary;
    private int idx;

    public OopService() {
        ary = new PersonDTO[10];
        idx = 0;
    }

    public void setAry(PersonDTO person) {
        ary[idx++] = person;
    }
}
