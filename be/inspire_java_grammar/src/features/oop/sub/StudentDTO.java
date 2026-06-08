package features.oop.sub;

import features.oop.sup.PersonDTO;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
// access modifier
// private - default - protected - public
public class StudentDTO extends PersonDTO {

    private String ssn;

    public String studentInfo() {
        return super.personInfo()+",\t ssn = "+ssn;
    }
    @Override
    public String personInfo() {
        return super.personInfo()+",\t ssn = "+ssn;
    }
}
