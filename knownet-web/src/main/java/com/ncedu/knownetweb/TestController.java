package com.ncedu.knownetweb;

import com.ncedu.knownetimpl.model.User;
import com.ncedu.knownetimpl.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    String html = "Hello, World!";


    @GetMapping("/test")
    public List<User> getTestString() {
        return userRepository.findAll();
    }

    @GetMapping("/test1")
    public String getOtherString() {
        return "MIPT";
    }

}
