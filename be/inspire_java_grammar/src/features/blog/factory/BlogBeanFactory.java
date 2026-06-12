package features.blog.factory;

import java.util.HashMap;
import java.util.Map;

import features.blog.ctrl.BlogInsertController;
import features.blog.ctrl.BlogListController;
import features.blog.ctrl.BlogSaveController;
import features.blog.service.BlogService;
import features.blog.service.BlogServiceImpl;

// factory pattern, singleton pattern
// controller 객체를 생성해서
// 사용자의 endpoint로 Controller 객체를 바인딩하는 역할
public class BlogBeanFactory {

    private Map<String, Object> map;
    private static BlogBeanFactory instance;
    // 인스턴스를 하나로 유지하면서 각각의 ctrl 할당하여 같은 서비스객체를 사용하도록
    private BlogService service;

    private BlogBeanFactory() {
        map = new HashMap<>();
        service = new BlogServiceImpl();

        // 추후 추가되는 각각의 기능을 구현하는 xxxxController 등록
        map.put("list.inspire", new BlogListController(service));
        map.put("insert.inspire", new BlogInsertController(service));
        map.put("save.inspire", new BlogSaveController(service));
    }

    public static BlogBeanFactory getInstance() {
        if(instance == null) {
            instance = new BlogBeanFactory();
        }
        return instance;
    }

    public Object getBean(String endpoint) {
        return map.get(endpoint);
    }
}
