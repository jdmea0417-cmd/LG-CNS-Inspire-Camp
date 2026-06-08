package features.user.dao;

import features.user.domain.UserRequestDTO;
import features.user.domain.UserResponseDTO;

// Data Access Object?
// dbms 통신을 통해서 Structur Query Language (SQL) 작업을 전담하는 클래스
public class UserDao {
    
    // 변수 + 메서드 + 생성자
    public UserResponseDTO[] userList() {
        System.out.println("debug >>>> userdao userlist called");
        UserResponseDTO[] list = new UserResponseDTO[10];

        return list;
    }

    public boolean userInsertRow(UserRequestDTO user) {
        System.out.println("debug >>>> user service userInsertRow called");
        System.out.println("debug >>>> user dao user params : "+user);

        // SQL : insert inot table values(?,?,?,?)
        return true;
    }
}
