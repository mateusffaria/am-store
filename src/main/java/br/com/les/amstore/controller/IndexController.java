package br.com.les.amstore.controller;

import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.service.ICustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    ICustomersService customerService;

    @RequestMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/index");

        Customer customer = customerService.currentUserLoggedIn();

        if(null != customer)
            mv.addObject(customer);

        return mv;
    }

    @RequestMapping("login")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }
}
