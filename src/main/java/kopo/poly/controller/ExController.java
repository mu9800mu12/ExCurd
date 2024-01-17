package kopo.poly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class ExController {
    
    
    @GetMapping("/Ex")
        public String main() throws Exception {
        
        log.info(this.getClass().getName() + "Ex컨트롤러 실행");

        return "/Ex";

        }
}
