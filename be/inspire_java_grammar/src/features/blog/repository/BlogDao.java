package features.blog.repository;

import java.util.List;

import features.blog.domain.dto.BlogResponseDTO;

/*
DAO(Data Access Object)
- rdbms와 작업을 전담하는 클래스로 영속성레이어에 위치함.
- 입력(C), 읽기(R), 수정(U), 삭제(D) : CRUD
- Structure Query Language (SQL) : DDL, DML, DCL, SelectQuery
*/
public class BlogDao {
    private List<BlogResponseDTO> blogs;

    public BlogDao() {
        blogs = List.of(
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
                        .email("lim").viewCnt(50).build());
        System.out.println("\n>>>>filter : 조건검색(조회수가 30 이상인 정보만 추출");
    }

    // R(전체 검색)
    // 추후 데이터베이스와 통신을 통해서 전달받은 데이터를 xxxxDTO 객체로 만들고 List 담는 역할
    public List<BlogResponseDTO> selectRow() {
        System.out.println(">>>> blog dao selectRow");
        return blogs;
    }
}
