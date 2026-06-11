package features.blog.domain.entity;

import features.blog.domain.dto.BlogResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
추후 ORM(JPA)
xxxxEntity == Table(rdbms에서 데이터를 관리하는 객체)
*/
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BlogEntity {
    // 해당 블로그를 식별할 수 있는 기본키(primary key)
    private Integer id;
    private String title, content, email;
    //////////////////////////////////////////////
    private Integer viewCnt;

    public static BlogResponseDTO toDTO(BlogEntity entity) {
        return BlogResponseDTO.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .email(entity.getEmail())
            .viewCnt(entity.getViewCnt())
            .build();
    }
}

