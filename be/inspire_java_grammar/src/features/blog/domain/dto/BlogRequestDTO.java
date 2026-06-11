package features.blog.domain.dto;

import features.blog.domain.entity.BlogEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class BlogRequestDTO {

    // 해당 블로그를 식별할 수 있는 기본키(primary Key)
    private String title, content, email;

    public static BlogEntity toEntity(BlogRequestDTO request) {
        return BlogEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .email(request.getEmail())
                .build();
    }

}
