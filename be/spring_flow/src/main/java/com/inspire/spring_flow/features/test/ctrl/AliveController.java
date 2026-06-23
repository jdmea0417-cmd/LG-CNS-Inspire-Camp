package com.inspire.spring_flow.features.test.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
// user endpoint : // http://localhost:8000/check
@RequestMapping("/check")
public class AliveController {
  @GetMapping("/alive")
  public String alive() {
    System.out.println(">>>> debug user endpoint : /check/alive");
    System.out.println(">>>> debug alive controller alive()");  
    return "alive";
  }
  
}
