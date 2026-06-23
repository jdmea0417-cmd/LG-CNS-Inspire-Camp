package com.inspire.spring_flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFlowApplication {
	// 서버 기동(AWS - tomcat:8080 - 포트변경가능)
	// ajr packing, java -jar xxxxx.jar
	public static void main(String[] args) {
		SpringApplication.run(SpringFlowApplication.class, args);
	}

}
