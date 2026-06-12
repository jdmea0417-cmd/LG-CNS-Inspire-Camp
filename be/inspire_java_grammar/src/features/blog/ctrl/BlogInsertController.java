package features.blog.ctrl;

import features.blog.domain.dto.BlogRequestDTO;
import features.blog.service.BlogService;

public class BlogInsertController {
    
    private BlogService service;
    public BlogInsertController() {
    }
    public BlogInsertController(BlogService service) {
        this.service = service;
    }

    public int insert(BlogRequestDTO request) {
        System.out.println(">>>> blog insert ctrl insert() ");
        return service.insert(request);
    }
}
