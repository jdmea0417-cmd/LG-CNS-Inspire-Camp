package features.blog.service;

import java.util.List;

import features.blog.domain.dto.BlogRequestDTO;
import features.blog.domain.dto.BlogResponseDTO;

public interface BlogService {
    public List<BlogResponseDTO> list();
    public int insert(BlogRequestDTO request);
    public void save();
    public void load();
    public int delete(int id);
    public BlogResponseDTO read(int id);
}
