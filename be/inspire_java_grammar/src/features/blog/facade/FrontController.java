package features.blog.facade;

import java.util.List;

import features.blog.ctrl.BlogInsertController;
import features.blog.ctrl.BlogListController;
import features.blog.ctrl.BlogSaveController;
import features.blog.domain.dto.BlogRequestDTO;
import features.blog.domain.dto.BlogResponseDTO;
import features.blog.factory.BlogBeanFactory;

// 시스템에 접근할 수 있는 단일 진입점으로 
// 모든 사용자의 요청(url, endpoint)을 받아서 xxxController와 연결시키는 역할
public class FrontController {

    private BlogBeanFactory factory;

    public FrontController() {
        factory = BlogBeanFactory.getInstance();
    }

    public List<BlogResponseDTO> list(String endPoint) {
        System.out.println(">>>> front controller endPoint : " + endPoint);
        BlogListController ctrl = (BlogListController) factory.getBean(endPoint);
        return ctrl.list();
    }

    public int insert(String endPoint, String title, String content, String email) {
        System.out.println(">>>> front controller endPoint : " + endPoint);
        /*
         * - 전달받은 파라미터로 endPoint 바인딩되는 컨트롤러 객체를 찾고
         * - 컨트롤러에 정의된 메서드를 호출하여 입력을 요청
         * - 단, 전달받은 title, content, email 을 DTO로 만들어서 전달
         */
        BlogInsertController ctrl = (BlogInsertController) factory.getBean(endPoint);
        BlogRequestDTO request = BlogRequestDTO.builder()
                .title(title)
                .content(content)
                .email(email)
                .build();
        return ctrl.insert(request);
    }

    public void saveToFile(String endPoint) {
        System.out.println(">>>> front controller endPoint : "+endPoint);
        BlogSaveController ctrl = (BlogSaveController)factory.getBean(endPoint);
        ctrl.save();
    }
}
