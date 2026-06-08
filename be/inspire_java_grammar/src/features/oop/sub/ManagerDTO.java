package features.oop.sub;

import features.oop.sup.PersonDTO;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
public class ManagerDTO extends PersonDTO{

    private String dept;

    public String managerInfo() {
        return super.personInfo()+",\t dept = "+dept;
    }
    @Override
    public String personInfo() {
        return super.personInfo()+",\t dept = "+dept;
    }

}
