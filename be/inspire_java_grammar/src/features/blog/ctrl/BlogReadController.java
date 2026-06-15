package features.blog.ctrl;

import features.blog.domain.dto.BlogResponseDTO;
import features.blog.service.BlogService;
import features.blog.service.BlogServiceImpl;

public class BlogReadController {
      private BlogService service;
    public BlogReadController() {
      service = new BlogServiceImpl();
    }
    public BlogReadController(BlogService service) {
      this.service = service;
    }
    public BlogResponseDTO read(int id) {
        System.out.println(">>>> blog read ctrl read()");
        return service.read(id);
    }
}
