package br.com.les.amstore.controller;

import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.service.ICustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
            Customer customer = customerService.findByEmail(username);
            mv.addObject(customer);
        }

        return mv;
    }

    @RequestMapping("login")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }
}
