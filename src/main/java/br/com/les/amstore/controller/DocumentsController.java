package br.com.les.amstore.controller;

import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.domain.Document;
import br.com.les.amstore.service.ICustomersService;
import br.com.les.amstore.service.IDocumentService;
import br.com.les.amstore.service.IDocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer/edit/{id}/documents")
public class DocumentsController {
    @Autowired
    private IDocumentService documents;

    @Autowired
    private IDocumentTypeService documentTypes;

    @Autowired
    private ICustomersService customers;

    @GetMapping("")
    public ModelAndView customerDocuments(@PathVariable("id") Customer customer) {
        ModelAndView mv = new ModelAndView("/customers/listCustumerDocuments");
        customers.isCurrentUserLoggedIn(customer.getId(), mv);

        mv.addObject(customer);
        return mv;
    }

    @DeleteMapping("")
    public ModelAndView deleteDocument(@PathVariable("id") Customer customer, @RequestParam String id, @RequestParam String document_id, RedirectAttributes attributes) {
        if(!customers.isCurrentUserLoggedIn(customer.getId()))
            return new ModelAndView("redirect:/");

        Document document = documents.findById(Long.parseLong(document_id));
        document.delete();

        documents.saveAndFlush(document);

        ModelAndView mv = new ModelAndView("redirect:/customer/edit/" + id + "/documents");

        attributes.addFlashAttribute("message", "Documento excluído com sucesso!");

        return mv;
    }

    @GetMapping("/new")
    public ModelAndView newCustomerDocuments(@PathVariable("id") Customer customer, Document document) {
        ModelAndView mv = new ModelAndView("/customers/newDocument");

        customers.isCurrentUserLoggedIn(customer.getId(), mv);

        mv.addObject("documentTypes", documentTypes.findAll());
        mv.addObject(customer);
        mv.addObject(document);

        return mv;
    }

    @PostMapping("/new")
    public ModelAndView createCustomerDocument(@Valid Document document, BindingResult result, RedirectAttributes attributes, Customer customer) {
        if(!customers.isCurrentUserLoggedIn(customer.getId()))
            result.addError(new ObjectError("customer", "Você não está autorizado a executar essa ação"));

        if(result.hasErrors()){
            return newCustomerDocuments(customer, document);
        }

        document.setPerson(customer);
        documents.saveAndFlush(document);

        ModelAndView mv = new ModelAndView("redirect:/customer/edit/" + customer.getId() + "/documents");

        attributes.addFlashAttribute("message", "Documento criado com sucesso!");

        return mv;
    }


}
