package br.com.les.amstore.controller;

import br.com.les.amstore.domain.Coupon;
import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.domain.Order;
import br.com.les.amstore.domain.Status;
import br.com.les.amstore.dto.CouponDTO;
import br.com.les.amstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

}
