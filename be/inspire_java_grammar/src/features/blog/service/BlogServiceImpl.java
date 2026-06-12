package features.blog.service;

import java.util.List;

import features.blog.domain.dto.BlogRequestDTO;
import features.blog.domain.dto.BlogResponseDTO;
import features.blog.repository.BlogDao;

public class BlogServiceImpl implements BlogService {

    private BlogDao dao;
    public BlogServiceImpl() {
        dao = new BlogDao();
    }
    @Override
    public List<BlogResponseDTO> list() {
        System.out.println(">>>> blog service list");
        return dao.selectRow();
    }
    @Override
    public int insert(BlogRequestDTO request) {
        System.out.println(">>>> blog service insert");
        return dao.insertRow(request);
    }
    @Override
    public void save() {
        System.out.println(">>>> blog service insert");
        dao.SaveRow();
    }
}
