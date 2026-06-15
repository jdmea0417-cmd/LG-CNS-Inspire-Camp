package features.blog.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void setBlogs(List<BlogResponseDTO> blogs) {
        this.blogs = blogs;
    }
    public List<BlogResponseDTO> getBlogs() {
        return this.blogs;
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

    // D(삭제)
    public int deleteRow(int id) {
        System.out.println(">>>> blog dao deleteRow");
        // blogs = blogs.stream()
        //     .filter(blog -> blog.getId() != id)
        //     .toList(); //-> front 최적화 코드
        boolean isFlag = blogs.removeIf(blog -> blog.getId() == id);
        return (isFlag) ? 1 : 0;
    }

    public Optional<BlogResponseDTO> readRow(int id) {
        System.out.println(">>>> blog dao readRow");
        // findFirst() : 순서가 보장되는 첫번째
        // findAny() : 순서가 보장되지 않는(병렬처리) 가장 빠른 결과
        Optional<BlogResponseDTO> result = 
            blogs.stream()
                .filter( blog -> blog.getId() == id )
                .findAny();
        return result;
    }
}
