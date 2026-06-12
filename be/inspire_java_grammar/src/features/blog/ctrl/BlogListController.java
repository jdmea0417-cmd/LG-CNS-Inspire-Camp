package features.blog.ctrl;

import java.util.List;

import features.blog.domain.dto.BlogResponseDTO;
import features.blog.service.BlogService;
import features.blog.service.BlogServiceImpl;

public class BlogListController {
    
    private BlogService service;
    public BlogListController() {
        service = new BlogServiceImpl();
    }
    public BlogListController(BlogService service) {
        this.service = service;
    }

    // view 응답하는 역할
    public List<BlogResponseDTO> list() {
        System.out.println(">>>> blog list ctrl list()");
        return service.list();
    }
}
