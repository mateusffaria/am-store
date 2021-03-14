package br.com.les.amstore.controller;

import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.service.ICustomerTypeService;
import br.com.les.amstore.service.ICustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ICustomersService customers;

    @Autowired
    private ICustomerTypeService customerTypes;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/admin/index");
        return mv;
    }

    @DeleteMapping("/customer/list")
    public ModelAndView deleteCustomer(@RequestParam String id, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/admin/customer/list");

        Customer customer = customers.findById(Long.parseLong(id));
        System.err.println(customer.getId());
        customer.delete();

        attributes.addFlashAttribute("message", "Usuário excluído com sucesso!");
        customers.saveAndFlush(customer);

        return mv;
    }

    @GetMapping("/customer/list")
    public ModelAndView listCustomer() {
        ModelAndView mv = new ModelAndView("/admin/listCustomer");
        mv.addObject("customers", customers.findAll());
        mv.addObject("customerTypes", customerTypes.findAll());
        return mv;
    }

    @GetMapping("/games/new")
    public ModelAndView newGame() {
        ModelAndView mv = new ModelAndView("/admin/newGame");

        return mv;
    }

    @GetMapping("/games/list")
    public ModelAndView listGames() {
        ModelAndView mv = new ModelAndView("/admin/listGames");

        return mv;
    }

    @GetMapping("/order_return/list")
    public ModelAndView listOrdersReturned() {
        ModelAndView mv = new ModelAndView("/admin/listReturnedOrders");

        return mv;
    }
}
