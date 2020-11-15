package com.tccspring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Hello(HttpServletRequest request) {
        request.setAttribute("Hello","Hello Le Anh Duc");
        return "hello";
    }

    @RequestMapping(value = "/home/student", method = RequestMethod.GET)
    public String student(HttpServletRequest request) {
        request.setAttribute("Hello","Hello Le Anh Duc");
        return "student";
    }
}
