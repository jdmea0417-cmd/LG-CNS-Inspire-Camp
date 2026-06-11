package features.blog.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class BlogResponseDTO {
    // 해당 블로그를 식별할 수 있는 기본키(primary key)
    private Integer id;
    private String title, content, email;
    //////////////////////////////////////////////
    private Integer viewCnt;
}
