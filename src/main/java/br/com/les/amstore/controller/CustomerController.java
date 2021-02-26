package br.com.les.amstore.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @RequestMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/customers/create");
        return mv;
    }
}
