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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ICustomersService customers;

    @Autowired
    private ICustomerTypeService customerTypes;

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/admin/index");

        Date inital;
        Date finalDate;

        inital = Date.from(LocalDateTime.now().minusMonths(3).toInstant(ZoneOffset.UTC));
        finalDate = Date.from(LocalDateTime.now().plusMonths(3).toInstant(ZoneOffset.UTC));

        List<HashMap<String, Double>> orders = orderService.findAllByCreatedAtBetween(inital, finalDate, 0);
        List<HashMap<String, Double>> cards = orderService.fillCardsIndex();

        mv.addObject("ordersFilteres", orders);
        mv.addObject("cards", cards);

        mv.addObject("admin", customers.currentUserLoggedIn());

        return mv;
    }

    @GetMapping("/filterdata")
    public @ResponseBody List<HashMap<String, Double>> getOrdersFiltered(
            String initialDateParam, String finalDateParam, Integer searchType) throws ParseException {
        Date inital;
        Date finalDate;

        inital = Date.from(LocalDateTime.now().minusMonths(3).toInstant(ZoneOffset.UTC));
        finalDate = Date.from(LocalDateTime.now().plusMonths(3).toInstant(ZoneOffset.UTC));

        if((null != initialDateParam && null != finalDateParam) && (initialDateParam.length() > 0 && finalDateParam.length() > 0)){
            inital = new SimpleDateFormat("yyyy-MM-dd").parse(initialDateParam);
            finalDate = new SimpleDateFormat("yyyy-MM-dd").parse(finalDateParam);
        }

        return orderService.findAllByCreatedAtBetween(inital, finalDate, searchType);
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
        mv.addObject("admin", customers.currentUserLoggedIn());
        mv.addObject("customers", customers.findAll());
        mv.addObject("customerTypes", customerTypes.findAll());
        return mv;
    }

}
