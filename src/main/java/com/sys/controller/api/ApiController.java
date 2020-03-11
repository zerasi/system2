package com.sys.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${api-url}")
public class ApiController {

    @RequestMapping(value="/getPage")
    public String getPage(String pageName){
        return pageName;
    }

}
