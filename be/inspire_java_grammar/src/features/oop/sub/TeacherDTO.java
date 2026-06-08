package features.oop.sub;

import features.oop.sup.PersonDTO;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
public class TeacherDTO extends PersonDTO {

    private String subject;

    public String teacherInfo() {
        return super.personInfo()+",\t subject = "+subject;
    }
    @Override
    public String personInfo() {
        return super.personInfo()+",\t subject = "+subject;
    }
}
