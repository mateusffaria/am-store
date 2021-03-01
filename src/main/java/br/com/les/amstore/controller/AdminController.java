package br.com.les.amstore.controller;

import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.domain.Document;
import br.com.les.amstore.domain.DocumentType;
import br.com.les.amstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
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

        List<Document> docs = new ArrayList<Document>();

        List<DocumentType> docTypes = documentTypes.findAll();

        for(int i = 0; i < docTypes.size(); i++) {
            Document doc = new Document();
            doc.setDocumentType(docTypes.get(i));
            docs.add(doc);
        }

        customer.setDocuments(docs);

        mv.addObject(customer);
        mv.addObject("documentTypes", docTypes);
        mv.addObject("customerTypes", customerTypes.findAll());
//        mv.addObject("documents", docs);

        return mv;
    }

    @PostMapping("/customer/new")
    public ModelAndView createCostumer(Customer customer) {
        ModelAndView mv = new ModelAndView("/admin/newCustumer");

        mv.addObject("customer", customer);
        mv.addObject("documentTypes", documentTypes.findAll());
        mv.addObject("addressTypes", addressTypes.findAll());
        mv.addObject("customerTypes", customerTypes.findAll());

        System.out.println(customer.getCustomerType().getId());
        System.out.println(customer.getEmail());
        System.out.println(customer.getPassword());
        System.out.println(customer.getName());
        System.out.println(customer.getTelephone());
        System.out.println(customer.getDocuments().get(0));
        System.out.println(customer.getCreatedAt());

        for(int i = 0; i<customer.getDocuments().size(); i++) {
            if(null == customer.getDocuments().get(i).getCode() || customer.getDocuments().get(i).getCode() == "") {
                customer.getDocuments().remove(i);
            } else {
                customer.getDocuments().get(i).setPerson(customer);
            }
        }

        System.out.println(customer.getDocuments().size());

        customers.saveAndFlush(customer);
        documents.saveAll(customer.getDocuments());
        return mv;
    }
}
