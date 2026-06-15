package features.blog.ctrl;

import features.blog.service.BlogService;
import features.blog.service.BlogServiceImpl;

public class BlogDeleteController {
    private BlogService service;
    public BlogDeleteController() {
        service = new BlogServiceImpl();
    }
    public BlogDeleteController(BlogService service) {
        this.service = service;
    }
    public int delete(int id) {
      System.out.println(">>>> blog delete ctrl delete() ");
      return service.delete(id);
    }
}
