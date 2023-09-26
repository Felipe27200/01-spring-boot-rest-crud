package com.luv2code.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Provide the mapping for the rest support
@RequestMapping("/test") // -> Is the prefix for the routes of this Controller
public class DemoRestController
{
    @GetMapping("/hello-world")
    public String sayHello()
    {
        return "Hello World";
    }
}
