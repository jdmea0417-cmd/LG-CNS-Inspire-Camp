import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import features.blog.domain.dto.BlogResponseDTO;

public class IOStreamApp {
    /*
     * input output stream - java.io.*
     * stream ? 데이터가 지나다니는 통로
     * - 단방향으로 흐른다.
     * 
     * 1byte - byte 2byte - char
     * xxxxxxxStream xxxxxxxWriter/xxxxxxxReader
     */
    public static void main(String[] args) {
        // System.out.print(">>>> io stream 이용한 데이터 입출력 : ");
        // try {
        // int input = System.in.read();
        // System.out.println((char)input);
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

        // char stream
        // System.out.println(">>>> io stream 이용한 데이터 입출력 : ");
        // BufferedReader br = new BufferedReader(
        // new InputStreamReader(System.in));
        // String input = null;
        // try {
        // input = br.readLine();
        // } catch (Exception e) {
        // e.printStackTrace();
        // } finally {
        // try {
        // if(br != null) {
        // br.close();
        // }
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // }
        // System.out.println(input);

        // System.out.println(">>>> file input / output ");
        // String data = "autocloseable";
        // String path = "./test.txt";
        // FileWriter writer = null;
        // BufferedWriter bw = null;
        // File file = new File(path);
        // try {
        // writer = new FileWriter(file);
        // bw = new BufferedWriter(writer);
        // bw.write(data);
        // } catch (IOException e) {
        // e.printStackTrace();
        // } finally {
        // try {
        // if(bw != null) {
        // bw.close();
        // }
        // } catch(Exception e) {
        // e.printStackTrace();
        // }
        // }

        // autocloseable -> finally 필요 x
        // System.out.println(">>>> file input / output ");
        // String data = "autocloseable";
        // String path = "./test.txt";
        // try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path))))
        // {
        // bw.write(data);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        // try (BufferedReader br = new BufferedReader(new FileReader(new File(path))))
        // {
        // System.out.println(br.readLine());
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        List<BlogResponseDTO> blogs = List.of(
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

        System.out.println(">>>> 객체직렬화를 이용한 파일저장");
        // test <- FileOutputStream <- ObjectOutputStream <- oos.writeObject()
        // ( 직렬화는 1byte )
        String path = "./object.txt";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)))) {
            oos.writeObject(blogs);
            System.out.println(">>>> success ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)))) {
            List<BlogResponseDTO> blogs_r = (List<BlogResponseDTO>)ois.readObject();
            blogs_r.stream()
                .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
