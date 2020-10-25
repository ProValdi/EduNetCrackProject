package com.ncedu.eduprojecthwfilippov;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/filippov")
public class FilippovController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }


    @GetMapping("/hw1/task1")
    public List<String> hw1Task1(@RequestParam(name = "inputStrings") List<String> inputStrings) {
        return inputStrings.stream().filter(str -> Pattern.matches("a[a-zA-Z]{2}",str)).collect(Collectors.toList());
    }


    @GetMapping("/hw1/task2")
    public String hw1Task2(@RequestParam(name = "inputIntegers") List<Integer> inputIntegers) {
        return inputIntegers.stream()
                .map(num -> ((num % 2 == 0) ? "e" : "o") + num)
                .collect(Collectors.joining(","));
    }

}
