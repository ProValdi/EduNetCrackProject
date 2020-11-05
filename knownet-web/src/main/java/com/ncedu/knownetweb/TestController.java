package com.ncedu.knownetweb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {

    String html = "Hello, World!";

    @GetMapping("/test")
    public String getTestString() {
        return html;
    }

    @GetMapping("/test1")
    public String getOtherString() {
        return "MIPT";
    }

}
