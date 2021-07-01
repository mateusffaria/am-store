package br.com.les.amstore.controller;

import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.domain.Game;
import br.com.les.amstore.service.ICustomersService;
import br.com.les.amstore.service.IGameService;
import br.com.les.amstore.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    ICustomersService customerService;

    @Autowired
    IGameService gameService;

    @RequestMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/index");

        Customer customer = customerService.currentUserLoggedIn();
        List<Game> games = gameService.findAllByActiveTrue();

        if(null != customer)
            mv.addObject(customer);

        mv.addObject("games", games);

        return mv;
    }

    @RequestMapping("login")
    public ModelAndView login() {
        return new ModelAndView("/login");
    }
}
