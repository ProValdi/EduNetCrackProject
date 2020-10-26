package com.ncedu.eduproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/vladimir")
public class ProkhorovController {

    private static final String defaul = "Input format is comma-separated list: ?list=kek,lol,cheburek";

    @GetMapping("/task1")
    public List<String> task1(@RequestParam(value = "list", defaultValue = defaul) List<String> list) {
        if (list.isEmpty()) {
            return new ArrayList<String>() {{add(defaul);}};
        } else
            return list.stream().filter(item -> item.charAt(0) == 'a' && item.length() == 3).collect(Collectors.toList());
    }

    @GetMapping("/task2")
    public String task2(@RequestParam(value = "list", defaultValue = defaul) List<Integer> list) {
        if (list.isEmpty()) {
            return defaul;
        } else
        return list.stream().map(s -> s % 2 == 0 ? "e" + s : "o" + s).collect(Collectors.joining(","));
    }


}