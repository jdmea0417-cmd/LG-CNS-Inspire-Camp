package features.blog.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import features.blog.domain.dto.BlogRequestDTO;
import features.blog.domain.dto.BlogResponseDTO;

/*
DAO(Data Access Object)
- xxxxxMapper.java -> mybatis
- xxxxxRepository.java -> 

- rdbms와 작업을 전담하는 클래스로 영속성레이어에 위치함.
- 입력(C), 읽기(R), 수정(U), 삭제(D) : CRUD
- Structure Query Language (SQL) : DDL, DML, DCL, SelectQuery
*/
public class BlogDao {
    private List<BlogResponseDTO> blogs;

    public BlogDao() {
        // 수정 가능 형태
        blogs = new ArrayList<>(List.of(
                BlogResponseDTO.builder()
                        .id(1).title("react").content("state")
                        .email("lim").viewCnt(10).build(),
                BlogResponseDTO.builder()
                        .id(2).title("java").content("oop")
                        .email("kim").viewCnt(20).build(),
                BlogResponseDTO.builder()
                        .id(3).title("spring").content("jpa")
                        .email("lee").viewCnt(30).build(),
                BlogResponseDTO.builder()
                        .id(4).title("docker").content("devops")
                        .email("park").viewCnt(40).build(),
                BlogResponseDTO.builder()
                        .id(5).title("msa").content("service")
                        .email("lim").viewCnt(50).build()));
    }

    // R(전체 검색)
    // 추후 데이터베이스와 통신을 통해서 전달받은 데이터를 xxxxDTO 객체로 만들고 List 담는 역할
    public List<BlogResponseDTO> selectRow() {
        System.out.println(">>>> blog dao selectRow");
        return blogs;
    }

    // C(입력)
    public int insertRow(BlogRequestDTO request) {
        System.out.println(">>>> blog dao insertRow");
        boolean isFlag = true;

        // 입력된 데이터를 List에 담는 구현
        // request -> response 변환하는 정적 메서드 패턴
        BlogResponseDTO response = BlogResponseDTO.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .email(request.getEmail())
                .build();
        blogs.add(response);

        return isFlag ? 1 : 0;
    }

    // File DB - Save
    // blogs 객체가 담고 있는 모든 요소를 Serializable 이용해서 파일에 저장
    public void SaveRow() {
        System.out.println(">>>> blog dao saveRow");
        String path = "./blog.txt";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)))) {
            oos.writeObject(blogs);
            System.out.println(">>>> success ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // File DB - Get
    public void loadRow() {
        System.out.println(">>>> blog dao loadRow");
        String path = "./blog.txt";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)))) {
            blogs = (List<BlogResponseDTO>)ois.readObject();
            System.out.println(">>>> success ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
