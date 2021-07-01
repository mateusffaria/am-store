package br.com.les.amstore.controller;

import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.domain.Game;
import br.com.les.amstore.service.ICustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/games")
public class GamesController {

    @Autowired
    ICustomersService customersService;

    @GetMapping("/")
    public ModelAndView games() {
        ModelAndView mv = new ModelAndView("games/games");

        setCurrentUserLoggedIn(mv);

        return mv;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView gameDetail(@PathVariable("id") Game game) {
        ModelAndView mv = new ModelAndView("games/gameDetail");
        mv.addObject(game);

        setCurrentUserLoggedIn(mv);

        return mv;
    }

    public void setCurrentUserLoggedIn(ModelAndView mv){
        Customer customer = customersService.currentUserLoggedIn();

        if(null != customer)
            mv.addObject(customer);
    }
}
