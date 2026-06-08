package features.oop.sup;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
public class PersonDTO {
    private String name;
    private int age;
    private String address;

    public String personInfo() {
        return "name = "+name+",\t age = "+age+",\t address = "+address;
    }
}
