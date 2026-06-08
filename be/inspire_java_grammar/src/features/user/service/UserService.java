package features.user.service;

import features.user.dao.UserDao;
import features.user.domain.UserRequestDTO;
import features.user.domain.UserResponseDTO;

public class UserService {

    // spring framework : Dependency Injection(의존성 주입)
    private UserDao dao;
    public UserService() {
        dao = new UserDao();
    }
    /////////////////////////////////////////////////////
    
    public UserResponseDTO[] userListService() {
        System.out.println("debug >>>> user service called");
        return dao.userList();
    }

    public boolean userInsert(UserRequestDTO user) {
        System.out.println("debug >>>> user service userInsert called");
        return dao.userInsertRow(user);
    }
}
