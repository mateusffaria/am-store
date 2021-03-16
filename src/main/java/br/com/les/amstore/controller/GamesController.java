package br.com.les.amstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/games")
public class GamesController {

    @GetMapping("/")
    public ModelAndView games() {
        return new ModelAndView("games/games");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView gameDetail() {
        return new ModelAndView("games/gameDetail");
    }
}
