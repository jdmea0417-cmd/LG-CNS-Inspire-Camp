package features.blog.ctrl;

import features.blog.service.BlogService;
import features.blog.service.BlogServiceImpl;

public class BlogFileController {
    private BlogService service;
    public BlogFileController() {
        service = new BlogServiceImpl();
    }
    public BlogFileController(BlogService service) {
        this.service = service;
    }

    // view 응답하는 역할
    public void save() {
        System.out.println(">>>> blog save ctrl save()");
        service.save();
    }
    
    public void load() {
        System.out.println(">>>> blog load ctrl load()");
        service.load();
    }
}
