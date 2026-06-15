package features.blog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    // File DB - Save
    // blogs 객체가 담고 있는 모든 요소를 Serializable 이용해서 파일에 저장
    public void save() {
        System.out.println(">>>> blog service insert");
        String path = "./blog.txt";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)))) {
            oos.writeObject(dao.getBlogs());
            System.out.println(">>>> success ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    // File DB - Get
    public void load() {
        System.out.println(">>>> blog service insert");
        String path = "./blog.txt";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)))) {
            List<BlogResponseDTO> blogs = (List<BlogResponseDTO>)ois.readObject();
            System.out.println(">>>> success ");
            dao.setBlogs(blogs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int delete(int id) {
        System.out.println(">>>> blog service delete");
        return dao.deleteRow(id);
    }
    @Override
    public BlogResponseDTO read(int id) {
        System.out.println(">>>> blog service read");
        // case 1
        // Optional<BlogResponseDTO> result = dao.readRow(id);
        // if( result.isPresent() ) {
        //     return result.get();
        // }
        // return null;

        // case 2
        // return dao.readRow(id).orElse(null);

        // case 3
        return dao.readRow(id)
            .orElseThrow(() -> new RuntimeException());
    }
}
