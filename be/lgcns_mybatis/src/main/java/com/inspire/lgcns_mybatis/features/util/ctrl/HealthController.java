package com.inspire.lgcns_mybatis.features.util.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//user -> http:// serverip:port/health
@RequestMapping("/health")
public class HealthController {
  
  @GetMapping("/alive")
  public String check() {
    System.out.println(">>>> debug health controller check");
    return "alive";
  }
}
