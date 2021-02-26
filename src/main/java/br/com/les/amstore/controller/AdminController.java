package br.com.les.amstore.controller;

import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        mv.addObject("documentTypes", documentTypes.findAll());
        mv.addObject("addressTypes", addressTypes.findAll());
        mv.addObject("customerTypes", customerTypes.findAll());
        return mv;
    }

    @PostMapping("/customer/new")
    public ModelAndView createCostumer(Customer customer) {
        ModelAndView mv = new ModelAndView("/admin/newCustumer");
        return mv;
    }
}
