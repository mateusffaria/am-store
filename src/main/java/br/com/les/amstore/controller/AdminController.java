package br.com.les.amstore.controller;

import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.domain.Document;
import br.com.les.amstore.domain.DocumentType;
import br.com.les.amstore.domain.Person;
import br.com.les.amstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

        return mv;
    }

    @PostMapping("/customer/new")
    public ModelAndView createCostumer(@Valid Customer customer, BindingResult result, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("/admin/newCustumer");

        mv.addObject("customer", customer);
        mv.addObject("documentTypes", documentTypes.findAll());
        mv.addObject("addressTypes", addressTypes.findAll());
        mv.addObject("customerTypes", customerTypes.findAll());

        if(result.hasErrors()){
            for(ObjectError r : result.getAllErrors()) {
                System.err.println("Nome do obj: " + r.getObjectName());
                System.err.println("Código erro: " + r.getCode());
                System.err.println("Mensagem do erro: " + r.getDefaultMessage());
            }

            return newCostumer(customer);
        }

        if(null != customer.getDocuments())
            customer.getDocuments().forEach(document -> document.setPerson(customer));

        customers.saveAndFlush(customer);
        documents.saveAll(customer.getDocuments());
        return mv;
    }
}
