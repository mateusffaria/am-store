package br.com.les.amstore.controller;

import br.com.les.amstore.domain.*;
import br.com.les.amstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ICustomersService customers;

    @Autowired
    private ICustomerTypeService customerTypes;

    @Autowired
    private IDocumentService documents;

    @Autowired
    private IDocumentTypeService documentTypes;

    @Autowired
    private IPersonService people;

    @Autowired
    private IStateService states;

    @Autowired
    private IAddressService addresses;

    @Autowired
    private IAddressTypeService addressTypes;

    @Autowired
    private ICityService cities;


    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/admin/index");
        return mv;
    }

    @GetMapping("/customer/list")
    public ModelAndView listCustomer() {
        ModelAndView mv = new ModelAndView("/admin/listCustomer");
        mv.addObject("customers", customers.findAll());
        Customer cus = new Customer();
        return mv;
    }

    @GetMapping("/customer/new")
    public ModelAndView newCostumer(Customer customer) {
        ModelAndView mv = new ModelAndView("/admin/newCustumer");

        mv.addObject(customer);
        mv.addObject("customerTypes", customerTypes.findAll());

        return mv;
    }

    @PostMapping("/customer/new")
    public ModelAndView createCostumer(@Valid Customer customer, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/admin/customer/list");

        if(result.hasErrors()){
            return newCostumer(customer);
        }

        mv.addObject("customer", customer);
        mv.addObject("customerTypes", customerTypes.findAll());

        attributes.addFlashAttribute("message", "Usuário criado com sucesso!");

        customers.saveAndFlush(customer);
        return mv;
    }

    @GetMapping("/customer/edit/{id}")
    public ModelAndView editCustomer(@PathVariable("id") Customer customer) {
        ModelAndView mv = new ModelAndView("/admin/newCustumer");

        mv.addObject("customerTypes", customerTypes.findAll());
        mv.addObject(customer);

        return mv;
    }

    @PostMapping("/customer/edit/{id}")
    public ModelAndView updateCustomer(@Valid Customer customer, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/admin/customer/list");

        if(result.hasErrors()){
            return editCustomer(customer);
        }

        mv.addObject("customerTypes", customerTypes.findAll());
        mv.addObject(customer);

        attributes.addFlashAttribute("message", "Usuário atualizado com sucesso!");

        customers.saveAndFlush(customer);
        return mv;
    }

    @GetMapping("/customer/edit/{id}/documents")
    public ModelAndView customerDocuments(@PathVariable("id") Customer customer) {
        ModelAndView mv = new ModelAndView("/admin/listCustumerDocuments");

        mv.addObject(customer);
        return mv;
    }

    @GetMapping("/customer/edit/{id}/documents/new")
    public ModelAndView newCustomerDocuments(@PathVariable("id") Customer customer, Document document) {
        ModelAndView mv = new ModelAndView("/admin/newDocument");

        mv.addObject("documentTypes", documentTypes.findAll());
        mv.addObject(customer);
        mv.addObject(document);

        return mv;
    }

    @PostMapping("/customer/edit/{id}/documents/new")
    public ModelAndView createCustomerDocument(@Valid Document document, Customer customer, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/admin/customer/edit/" + customer.getId() + "/documents/");

        if(result.hasErrors()){
            return newCustomerDocuments(customer, document);
        }

        document.setPerson(customer);

        mv.addObject("documentTypes", documentTypes.findAll());
        mv.addObject(customer);
        mv.addObject(document);

        attributes.addFlashAttribute("message", "Documento criado com sucesso!");

        documents.saveAndFlush(document);
        return mv;
    }

    @GetMapping("/customer/edit/{id}/documents/{id_doc}/edit")
    public ModelAndView editDocument(@PathVariable("id") Customer customer, @PathVariable("id_doc") Document document) {
        ModelAndView mv = new ModelAndView("/admin/newDocument");

        mv.addObject("documentTypes", documentTypes.findAll());
        mv.addObject(customer);
        mv.addObject(document);

        return mv;
    }

    @GetMapping("/customer/edit/{id}/addresses")
    public ModelAndView listAddress(@PathVariable("id") Customer customer, Address address) {
        ModelAndView mv = new ModelAndView("/admin/listAddresses");

        mv.addObject(customer);
        mv.addObject(address);

        return mv;
    }

    @GetMapping("/customer/edit/{id}/addresses/new")
    public ModelAndView newAddress(@PathVariable("id") Customer customer, Address address) {
        ModelAndView mv = new ModelAndView("/admin/newAddress");

        mv.addObject("addressesTypes", addressTypes.findAll());
        mv.addObject("states", states.findAll());
        mv.addObject(customer);
        mv.addObject(address);

        return mv;
    }

    @PostMapping("/customer/edit/{id}/addresses/new")
    public ModelAndView createCustomerDocument(@PathVariable("id") Customer customer, @Valid Address address, BindingResult result, RedirectAttributes attributes) {
//        ModelAndView mv = new ModelAndView("redirect:/admin/listAddresses");
        ModelAndView mv = new ModelAndView("redirect:/admin/customer/edit/" + customer.getId() + "/addresses/new");

        if(result.hasErrors() || null == customer.getId()){
            System.err.println("Deu erro");
            return newAddress(customer, address);
        }

        customer = customers.findById(customer.getId());
        address.setCustomer(customer);

        mv.addObject(customer);
        mv.addObject(address);

        attributes.addFlashAttribute("message", "Endereço criado com sucesso!");

        addresses.saveAndFlush(address);

        return mv;
    }

    @GetMapping("/customer/edit/{id}/addresses/{address_id}/edit")
    public ModelAndView editAddress(@PathVariable("id") Customer customer, @PathVariable("address_id") Address address) {
        ModelAndView mv = new ModelAndView("/admin/newAddress");

        mv.addObject("addressesTypes", addressTypes.findAll());
        mv.addObject("states", states.findAll());
        mv.addObject(customer);
        mv.addObject(address);

        return mv;
    }

    @PostMapping(value = "/customer/edit/{customer_id}/addresses/{id}/edit")
    public ModelAndView updateAddress(@PathVariable(value = "customer_id") Customer customer, @Valid Address address, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/admin/customer/edit/" + customer.getId() + "/addresses/" + address.getId() + "/edit");

        System.err.println("Endereço: " + address.getId());
        System.err.println("Customer: " + customer.getId());
        if(result.hasErrors() || null == customer.getId()){
            System.err.println("Deu erro");
            return newAddress(customer, address);
        }

        customer = customers.findById(customer.getId());

        address.setCustomer(customer);

        mv.addObject(customer);
        mv.addObject(address);

        attributes.addFlashAttribute("message", "Endereço atualizado com sucesso!");

        addresses.saveAndFlush(address);
        return mv;
    }
}
