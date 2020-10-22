package com.ncedu.eduprojectweb;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class.getName());

    public TestController() {
        int a = 10;

        logger.info("a = {}", a);
        logger.trace("a1 = {}", a);
        logger.debug("a2 = {}", a);
        logger.info("a3 = {}", a);
        logger.warn("a4 = {}", a);
        logger.error("a5 = {}", a);
        // как консоль сделать
    }

    String html = "hello madafuck";

    // we can return html file
    @GetMapping("/test")
    public String getTestString() {
        return html;
    }

    @GetMapping("/test1")
    public String getOtherString() {
        return "fuckoff";
    }

}
