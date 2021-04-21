package br.com.les.amstore.controller;

import br.com.les.amstore.domain.*;
import br.com.les.amstore.facade.FacadeImpl;
import br.com.les.amstore.facade.IFacade;
import br.com.les.amstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/games")
public class AdminGamesController {
    @Autowired
    Games games;

    @Autowired
    Platforms platforms;

    @Autowired
    Genders genders;

    @Autowired
    Languages languages;

    @Autowired
    Publishers publishers;

    @GetMapping("/new")
    public ModelAndView newGame(Game game) {
        FacadeImpl facade = new FacadeImpl(games, platforms, genders, languages, publishers);

        List<DomainEntity> platformList;
        List<DomainEntity> genderList;
        List<DomainEntity> languageList;
        List<DomainEntity> publisherList;

        platformList = facade.read(new Platform());
        genderList = facade.read(new Gender());
        languageList = facade.read(new Language());
        publisherList = facade.read(new Publisher());

        ModelAndView mv = new ModelAndView("/admin/newGame");

        mv.addObject(game);
        mv.addObject(platformList);
        mv.addObject(genderList);
        mv.addObject(languageList);
        mv.addObject(publisherList);
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView createGame(@Valid Game game, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()){
            return newGame(game);
        }

        FacadeImpl facade = new FacadeImpl(games, platforms, genders, languages, publishers);

        facade.save(game);

        ModelAndView mv = new ModelAndView("redirect:/admin/games/list");

        return mv;
    }

    @GetMapping("/list")
    public ModelAndView listGames() {
        ModelAndView mv = new ModelAndView("/admin/listGames");

        return mv;
    }

}
