import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import features.blog.domain.dto.BlogResponseDTO;

public class BlogStreamApp {
    public static void main(String[] args) {
        System.out.println(">>>> 샘플 더미 데이터 생성");

        List<BlogResponseDTO> list = List.of(
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

        list.stream()
                .filter(blog -> blog.getViewCnt() >= 30)
                .forEach(blog -> System.out.println(blog));

        System.out.println("\n>>>>map : 타입변환 용도");
        list.stream()
                .filter(blog -> blog.getViewCnt() >= 30)
                .map(BlogResponseDTO::getEmail)
                .forEach(System.out::println);

        System.out.println("\n>>>>collect, toList : 리스트 변환 ");
        List<BlogResponseDTO> result = list.stream()
                .filter(blog -> blog.getEmail().equals("lim"))
                // .collect(Collectors.toList());
                .toList();
        result.forEach(System.out::println);

        System.out.println("\n>>>>grouping : 작성자별 그룹");
        Map<String, List<BlogResponseDTO>> map = list.stream()
                .collect(Collectors.groupingBy(BlogResponseDTO::getEmail));

        map.get("lim").stream()
                .forEach(System.out::println);

        System.out.println("\n>>>> Q) 조회수의 평균 확인하고 싶다면? ");
        double avg = list.stream()
                .mapToInt(BlogResponseDTO::getViewCnt)
                .average()
                .orElse(0);
        System.out.println(avg);

        System.out.println("\n>>>> Q) email 추출하는데 중복된 데이터는 1번만 출력하고 싶다면?");
        list.stream()
                .map(BlogResponseDTO::getEmail)
                .distinct()
                .forEach(System.out::println);

        System.out.println("\n>>>> Q) paging 느낌의 정렬 또는 limit");
        list.stream()
                .sorted(Comparator.comparing(BlogResponseDTO::getViewCnt).reversed())
                .limit(3)
                .forEach(System.out::println);

        System.out.println("\n>>>> Q) anyMatch(존재여부 판단), allMatch(검증)");
        boolean isExist = list.stream()
            .anyMatch(blog -> blog.getEmail().equals("lim"));
        System.out.println(isExist);

        boolean isUp = list.stream()
            .allMatch(blog -> blog.getViewCnt() >= 20); // list 전체가 해당 조건 충족해야함
        System.out.println(isUp);
    }

}
