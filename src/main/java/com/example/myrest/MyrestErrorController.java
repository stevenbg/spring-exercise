package com.example.myrest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyrestErrorController implements ErrorController {

    @ResponseBody
    @RequestMapping("/error")
    public String handleError() {
        return "broccoli";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
