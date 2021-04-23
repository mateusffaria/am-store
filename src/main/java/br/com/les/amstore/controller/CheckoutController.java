package br.com.les.amstore.controller;

import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.service.ICartService;
import br.com.les.amstore.service.ICustomersService;
import br.com.les.amstore.service.IDocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/customer/{id}/checkout")
public class CheckoutController {
    @Autowired
    private ICustomersService customers;

    @Autowired
    private IDocumentTypeService documentTypes;

    @Autowired
    private ICartService cartService;

    @GetMapping("")
    public ModelAndView getCheckout(@PathVariable("id") Customer customer) {
        ModelAndView mv = new ModelAndView("customers/checkout");
        mv.addObject(customer);
        mv.addObject("documentTypes", documentTypes.findAll());

        customers.isCurrentUserLoggedIn(customer.getId(), mv);
        return mv;
    }

    @PostMapping("")
    public ModelAndView addCheckoutItem(@PathVariable("id") Customer customer,
                                        Long idGame,
                                        Integer amount) {
        if(!customers.isCurrentUserLoggedIn(customer.getId()))
            return new ModelAndView("redirect:/");

        cartService.addCartItem(customer, idGame, amount);

        ModelAndView mv = new ModelAndView("customers/checkout");
        mv.addObject(customer);
        mv.addObject("documentTypes", documentTypes.findAll());


        return mv;
    }
}
