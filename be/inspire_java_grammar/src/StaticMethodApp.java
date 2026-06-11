import java.util.List;
import java.util.stream.Collectors;

import features.blog.domain.dto.BlogRequestDTO;
import features.blog.domain.dto.BlogResponseDTO;
import features.blog.domain.entity.BlogEntity;

public class StaticMethodApp {
    public static void main(String[] args) {
        System.out.println(">>>> 사용자의 요청정보를 xxxRequestDTO에 담아서 -> xxxxEntity 변환");
        BlogRequestDTO request = BlogRequestDTO.builder()
                .title("패턴")
                .content("정적메서드 구현")
                .email("jslim9413@naver.com")
                .build();

        BlogEntity entity = BlogRequestDTO.toEntity(request);
        System.out.println(entity);

        System.out.println("\n>>>> 사용자의 응답정보를 xxxxEntity에 담아서 ->  xxxRequestDTO변환");
        BlogEntity responseEntity = BlogEntity.builder()
                .id(1)
                .title("휴강안내")
                .content("내일 휴강합니다")
                .email("xxx@gmail.com")
                .viewCnt(25)
                .build();

        BlogResponseDTO response = BlogEntity.toDTO(responseEntity);
        System.out.println(response);

        System.out.println("\n>>>> 사용자 응답정보가 여러개일 때");
        System.out.println("Q) stream api 이용해서 entity -> response dto");
        List<BlogEntity> entityLst = List.of(responseEntity, responseEntity);
        // case 01.
        entityLst.stream()
                .map(BlogEntity::toDTO)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // case 02.
        // entityLst.stream()
        // .map(BlogResponseDTO::new)
        // .toList();

        // case 03.
        // entityLst.stream()
        // .map(blog -> {
        //     BlogResponseDTO.builder()
        //     .id(null),build();
        // })
        // .toList();
    }
}
