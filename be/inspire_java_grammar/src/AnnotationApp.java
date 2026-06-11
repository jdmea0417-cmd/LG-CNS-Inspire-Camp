import features.blog.domain.dto.BlogRequestDTO;
import test.AnnotationDTO;

public class AnnotationApp {

    public static void main(String[] args) {
        // BlogRequesetDTO vs AnnotationDTO

        // new 연산자를 이용한 객체 생성
        // BlogRequestDTO blog = new BlogRequestDTO("목요일", "백엔드 시작", "jslim9413@naver.com");
        // System.out.println(blog.getTitle());
        // System.out.println("blog adress : "+blog);

        // // build 방식의 객체 생성
        // AnnotationDTO annotation = AnnotationDTO.builder()
        //         .email("email")
        //         .title("title")
        //         .content("content")
        //         .build();
        // System.out.println(annotation.getTitle());
        // System.out.println("annotation adress : "+annotation);
    }
}
