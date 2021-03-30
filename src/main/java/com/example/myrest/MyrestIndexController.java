package com.example.myrest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyrestIndexController {

    @RequestMapping("/")
    @ResponseBody
    String index() {
        return "burger";
    }

}
