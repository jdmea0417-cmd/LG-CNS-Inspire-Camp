package features.blog.ctrl;

import java.util.List;

import features.blog.domain.dto.BlogResponseDTO;
import features.blog.service.BlogService;
import features.blog.service.BlogServiceImpl;

public class BlogSaveController {
      private BlogService service;
    public BlogSaveController() {
        service = new BlogServiceImpl();
    }
    public BlogSaveController(BlogService service) {
        this.service = service;
    }

    // view 응답하는 역할
    public void save() {
        System.out.println(">>>> blog save ctrl list()");
        service.save();
    }
}
