package test;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AnnotationDTO {
    private String title;
    private String content;
    private String email;
}
