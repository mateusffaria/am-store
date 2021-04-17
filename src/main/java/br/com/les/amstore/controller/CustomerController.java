package br.com.les.amstore.controller;


import br.com.les.amstore.domain.Address;
import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.domain.Document;
import br.com.les.amstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomersService customers;

    @Autowired
    private ICustomerTypeService customerTypes;

    @Autowired
    private IDocumentTypeService documentTypes;

    @GetMapping("/new")
    public ModelAndView newCostumer(Customer customer) {
        ModelAndView mv = new ModelAndView("/customers/newCustumer");
        customer.setCustomerType(customerTypes.findById(Long.parseLong("1")));

        mv.addObject(customer);

        return mv;
    }

    @PostMapping("/new")
    public ModelAndView createCostumer(@Valid Customer customer, BindingResult result, RedirectAttributes attributes) {
        if(!customer.hasPasswordSet())
            result.addError(new ObjectError("customer", "Senha é obrigatória"));

        if(result.hasErrors()){
            return newCostumer(customer);
        }

        customer.setCustomerType(customerTypes.findById(Long.parseLong("1")));

        customers.saveAndFlush(customer);

        ModelAndView mv = new ModelAndView("redirect:/customer/edit/" + customer.getId() + "");

        attributes.addFlashAttribute("message", "Usuário criado com sucesso!");

        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCustomer(@PathVariable("id") Customer customer) {
        ModelAndView mv = new ModelAndView("/customers/newCustumer");

        mv.addObject("customerTypes", customerTypes.findAll());
        mv.addObject(customer);

        return mv;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateCustomer(@Valid Customer customer, BindingResult result, RedirectAttributes attributes) {
        customer.setEncryptedPassword(customers.findById(customer.getId()).getEncryptedPassword());

        if(!customer.hasPasswordSet())
            result.addError(new ObjectError("customer", "Senha é obrigatória"));

        if(result.hasErrors()){
            return editCustomer(customer);
        }

        customer.setCustomerType(customerTypes.findById(Long.parseLong("1")));

        customers.saveAndFlush(customer);

        ModelAndView mv = new ModelAndView("redirect:/customer/edit/" + customer.getId());

        mv.addObject(customer);

        attributes.addFlashAttribute("message", "Usuário atualizado com sucesso!");

        return mv;
    }

    @GetMapping("/{id}/checkout")
    public ModelAndView getCheckout(@PathVariable("id") Customer customer) {
        ModelAndView mv = new ModelAndView("customers/checkout");
        mv.addObject(customer);
        mv.addObject("documentTypes", documentTypes.findAll());

        return mv;
    }

    @GetMapping("/{id}/my-orders")
    public ModelAndView getOrders(@PathVariable("id") Customer customer) {
        ModelAndView mv = new ModelAndView("customers/myOrders");
        mv.addObject(customer);

        return mv;
    }
}
