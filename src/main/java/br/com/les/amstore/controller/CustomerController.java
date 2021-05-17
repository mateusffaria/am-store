package br.com.les.amstore.controller;


import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.domain.Devolution;
import br.com.les.amstore.domain.Order;
import br.com.les.amstore.service.ICustomerTypeService;
import br.com.les.amstore.service.ICustomersService;
import br.com.les.amstore.service.IDevolutionService;
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
@RequestMapping("/customer")
public abstract class CustomerController implements IDevolutionService {
    @Autowired
    private ICustomersService customers;

    @Autowired
    private ICustomerTypeService customerTypes;

    @Autowired
    private IDevolutionService devolutionService;

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

        customers.isCurrentUserLoggedIn(customer.getId(), mv);

        mv.addObject("customerTypes", customerTypes.findAll());
        mv.addObject(customer);

        return mv;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateCustomer(@Valid Customer customer, BindingResult result, RedirectAttributes attributes) {
        customer.setEncryptedPassword(customers.findById(customer.getId()).getEncryptedPassword());

        if(!customers.isCurrentUserLoggedIn(customer.getId()))
            result.addError(new ObjectError("customer", "Você não está autorizado a executar essa ação"));

        if(!customer.hasPasswordSet())
            result.addError(new ObjectError("customer", "Senha é obrigatória"));

        if(result.hasErrors()){
            return editCustomer(customer);
        }

        customer.setCustomerType(customerTypes.findById(Long.parseLong("1")));

        customers.saveAndFlush(customer);

        ModelAndView mv = new ModelAndView("redirect:/customer/edit/" + customer.getId());

        customers.isCurrentUserLoggedIn(customer.getId(), mv);

        mv.addObject(customer);

        attributes.addFlashAttribute("message", "Usuário atualizado com sucesso!");

        return mv;
    }

    @GetMapping("/{id}/my-orders")
    public ModelAndView getOrders(@PathVariable("id") Customer customer) {
        if(!customers.isCurrentUserLoggedIn(customer.getId()))
            return new ModelAndView("redirect:/");

        ModelAndView mv = new ModelAndView("customers/myOrders");
        mv.addObject(customer);
        mv.addObject("devolution", new Devolution());

        return mv;
    }

    @PostMapping("/{id}/my-orders")
    public ModelAndView devolutionRequest(@PathVariable("id") Customer customer, Devolution devolution) {
        if(!customers.isCurrentUserLoggedIn(customer.getId()))
            return new ModelAndView("redirect:/");

        devolutionService.sendDevolutionRequest(devolution);

        return getOrders(customer);
    }
}
