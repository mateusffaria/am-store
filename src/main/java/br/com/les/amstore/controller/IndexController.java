package br.com.les.amstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/index");
        return mv;
    }

    @RequestMapping("login")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }
}
