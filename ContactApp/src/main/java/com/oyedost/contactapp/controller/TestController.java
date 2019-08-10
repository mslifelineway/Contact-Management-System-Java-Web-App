
package com.oyedost.contactapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/test/hello")
    public String helloWorld(){
        return "hello"; //-- this hello is a jsp file, location: /WEB-INF/view/hello.jsp
    }
    
}
