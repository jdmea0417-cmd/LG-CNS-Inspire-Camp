import features.user.domain.UserRequestDTO;
import features.user.domain.UserResponseDTO;
import features.user.service.UserService;

public class AryApp {
    public static void main(String[] args) {
        int[] ary = new int[10];
        ary[0] = 'A';
        System.out.println(ary[0]);
        for (int idx = 0; idx < ary.length; idx++) {
            System.out.println(ary[idx]);
        }
        System.out.println(">>>>>>>>>>>>>>> enhanced loop");
        for (int data : ary) {
            System.out.println(data);
        }

        UserRequestDTO[] userAry = new UserRequestDTO[10]; // 배열 객체 생성
        UserRequestDTO user = UserRequestDTO.builder()
                .email("jslim9413@naver.com")
                .password("1234")
                .name("섭섭해")
                .build();
        
        for (int idx = 0; idx < ary.length; idx++) {
            if(userAry[idx] == null) {
                break;
            }
            System.out.println(userAry[idx].getEmail());
        }
        System.out.println(">>>>>>>>>>>>>>> enhanced loop");
        for (UserRequestDTO data : userAry) {
            if(data == null) {
                break;
            }
            System.out.println(data.getEmail());
        }
        /////////////////////////////////////////////////////////////////////
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> service ");
        UserService service = new UserService();
        UserResponseDTO[] result = service.userListService();

        for(UserResponseDTO data : result) {
            System.out.println(data);
        }

        
        boolean flag = service.userInsert(user);
        System.out.println(flag);

    }
}
