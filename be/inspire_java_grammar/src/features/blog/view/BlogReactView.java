package features.blog.view;

import java.util.List;
import java.util.Scanner;

import features.blog.domain.dto.BlogResponseDTO;
import features.blog.facade.FrontController;

public class BlogReactView {

    /*
     * Quiz)
     * - 반복을 통해서 계속 보여져야 함
     * - Scanner 객체를 이용해서 사용자 콘솔로부터 메뉴번호를 입력받을 것(Enum x)
     * 
     * - 예외처리)
     * 사용자는 숫자를 입력해야하는데 악의적으로 문자를 입력했을 경우 예외처리를 통해서 다시 메뉴를 보여준다.
     * 
     * - 해당 메뉴번호가 입력되었을 경우 조건처리를 통해서 정의된 각각의 메서드를 호출
     * - 99번이 입력되면 프로그램은 종료
     */
    // landing page 화면구성
    private Scanner scan;
    private int insert;
    private FrontController front;
    private String endPoint;

    public BlogReactView() {
        scan = new Scanner(System.in);
        insert = 0;
        front = new FrontController();
        endPoint = null;
    }

    public void mainMenu() {
        endPoint = "file.inspire";
        front.FileSaveLoad(endPoint, "load");
        while (true) {
            try {
                System.out.println("\n>>>> Inspire Camp Blog Ver(1.0)");
                System.out.println("1. 전체검색");
                System.out.println("2. 게시글 상세보기");
                System.out.println("3. 입력폼으로 이동");
                System.out.println("4. 수정폼으로 이동");
                System.out.println("5. 삭제하기");
                System.out.println("6. 게시글 작성자로 글 검색");
                System.out.println("99. 프로그램 종료");
                System.out.print("\n메뉴 선택 : ");
                insert = Integer.parseInt(scan.nextLine());
                switch (insert) {
                    case 1:
                        list();
                        break;
                    case 2:
                        read();
                        break;
                    case 3:
                        insert();
                        break;
                    case 4:
                        update();
                        break;
                    case 5:
                        delete();
                        break;
                    case 6:
                        search();
                        break;
                    case 99:
                        System.out.print(">>>> 작업된 내용을 저장하고 종료하시겠습니까?(y/n) : ");
                        String yn = scan.nextLine();
                        if (yn.equalsIgnoreCase("y")) {
                            String endPoint = "file.inspire";
                            front.FileSaveLoad(endPoint, "save");
                            System.exit(1);
                        } else {
                            System.exit(1);
                        }
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("에러 발생");
                e.printStackTrace();
            }

        }
    }

    // 서버통신을 통해서 전달 받은 데이터를 출력하는 역할
    public void list() {
        System.out.println("\n리스트 호출");
        endPoint = "list.inspire";
        List<BlogResponseDTO> list = front.list(endPoint);
        list.stream()
                .forEach(System.out::println);
    }

    // 상세페이지 정보를 출력하는 역할
    public void read() {
        System.out.println("\n리드 호출");

        endPoint = "read.inspire";
        System.out.print("키(id) : ");
        int id = Integer.parseInt(scan.nextLine());

        BlogResponseDTO response = front.read(endPoint, id);
        System.out.println((response != null ? response : "정보 없음"));
    }

    // 블로그 입력을 담당하는 역할
    public void insert() {
        System.out.println();
        System.out.println(">>>> 블로그 입력 폼<<<<");
        System.out.println();

        endPoint = "insert.inspire";
        System.out.print("제목 : ");
        String title = scan.nextLine();
        System.out.print("내용 : ");
        String content = scan.nextLine();
        System.out.print("이메일 : ");
        String email = scan.nextLine();

        int isFlag = front.insert(endPoint, title, content, email);
        System.out.println((isFlag == 1) ? "입력성공" : "입력실패");

    }

    // 블로그 삭제를 담당하는 역할
    public void delete() {
        System.out.println("\n삭제 호출");
        endPoint = "delete.inspire";
        System.out.print("삭제할 항목 id : ");
        int id = Integer.parseInt(scan.nextLine());
        int isFlag = front.delete(endPoint, id);
        if (isFlag == 1) {
            System.out.printf("id = %d 삭제 완료\n", id);
        } else {
            System.out.printf("id = %d 삭제 실패\n", id);
        }
    }

    // 블로그 수정을 담당하는 역할
    public void update() {
        System.out.println("\n수정 호출");
    }

    // 블로그 검색을 담당하는 역할
    public void search() {
        System.out.println("\n검색 호출");
    }
}
