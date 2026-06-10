import java.util.Scanner;

import features.blog.view.BlogReactView;

public class BlogApp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean isRunning = true;
        int insert = 0;
        while (isRunning) {
            BlogReactView view = new BlogReactView();
            view.mainMenu();
            System.out.print("메뉴 선택 : ");
            try {
                insert = scan.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
                scan.nextLine();
            }
            switch (insert) {
                case 1:
                    view.list();
                    break;
                case 2:
                    view.read();
                    break;
                case 3:
                    view.insert();
                    break;
                case 4:
                    view.delete();
                    break;
                case 5:
                    view.update();
                    break;
                case 6:
                    view.search();
                    break;
                case 99:
                    isRunning = false;
                    break;
                default:
                    break;

            }

        }
    }
}
