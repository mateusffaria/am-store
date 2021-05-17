package br.com.les.amstore.controller;

import br.com.les.amstore.domain.*;
import br.com.les.amstore.facade.FacadeImpl;
import br.com.les.amstore.repository.*;
import br.com.les.amstore.service.ICustomersService;
import br.com.les.amstore.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

    @Autowired
    IStockService stockService;

    @Autowired
    private ICustomersService customers;

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

        mv.addObject("admin", customers.currentUserLoggedIn());
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView createGame(@Valid Game game, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()){
            return newGame(game);
        }

        FacadeImpl facade = new FacadeImpl(games, platforms, genders, languages, publishers);

        game.setAmountAvailable(game.getAmount());

        facade.save(game);

        ModelAndView mv = new ModelAndView("redirect:/admin/games/list");

        attributes.addFlashAttribute("message","Jogo " + game.getTitle() + " Criado com sucesso");

        return mv;
    }

    @GetMapping("/list")
    public ModelAndView listGames() {
        FacadeImpl facade = new FacadeImpl(games, platforms, genders, languages, publishers);

        ModelAndView mv = new ModelAndView("/admin/listGames");

        List<Game> games = (List<Game>)(List<?>) facade.read(new Game());

        mv.addObject("games", games);
        mv.addObject("admin", customers.currentUserLoggedIn());
        return mv;
    }

    @GetMapping("/{idGame}/edit")
    public ModelAndView editGame(@PathVariable("idGame") Game game) {
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
        mv.addObject("admin", customers.currentUserLoggedIn());
        return mv;
    }

    @PostMapping("/{idGame}/edit")
    public ModelAndView updateGame(@PathVariable("idGame") Integer idGame, @Valid Game game, BindingResult result, RedirectAttributes attributes) {
        FacadeImpl facade = new FacadeImpl(games, platforms, genders, languages, publishers);


        game.setId(Long.parseLong(idGame.toString()));

        Game gameSaved = (Game) facade.readOne(game);
        game.setAmountAvailable(gameSaved.getAmountAvailable());

        String save = facade.save(game);

        if(save.length() != 0)
            result.addError(new ObjectError("error", save));

        if(result.hasErrors()){
            return editGame(game);
        }

        attributes.addFlashAttribute("message","Jogo " + game.getTitle() + " Atualizado com sucesso");

        ModelAndView mv = new ModelAndView("redirect:/admin/games/list");

        return mv;
    }

    @GetMapping("/stock")
    public ModelAndView newStock(Stock stock) {
        ModelAndView mv = new ModelAndView("/admin/newStock");

        mv.addObject("stock", stock);
        mv.addObject("games", games.findAll());
        mv.addObject("admin", customers.currentUserLoggedIn());
        return mv;
    }

    @PostMapping("/stock")
    public ModelAndView createStock(@Valid Stock stock, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors())
            return newStock(stock);

        stockService.saveAndFlush(stock);

        attributes.addFlashAttribute("message", "Endereço atualizado com sucesso!");

        return new ModelAndView("redirect:/admin/games/list");
    }
}
