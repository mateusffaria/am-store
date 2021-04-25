package br.com.les.amstore.controller;

import br.com.les.amstore.domain.Coupon;
import br.com.les.amstore.domain.Customer;
import br.com.les.amstore.domain.Order;
import br.com.les.amstore.domain.State;
import br.com.les.amstore.dto.AddressDTO;
import br.com.les.amstore.dto.CouponDTO;
import br.com.les.amstore.service.*;
import br.com.les.amstore.utils.correiostools.Fretenator;
import br.com.les.amstore.utils.correiostools.FretenatorResult;
import br.com.les.amstore.utils.correiostools.FretenatorResultItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/customer/{id}/checkout")
public class CheckoutController {
    @Autowired
    private ICustomersService customers;

    @Autowired
    private IDocumentTypeService documentTypes;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IGenericService<Order> orderService;

    @Autowired
    private ICouponService couponService;

    @GetMapping("")
    public ModelAndView getCheckout(@PathVariable("id") Customer customer) {
        ModelAndView mv = new ModelAndView("customers/checkout");
        mv.addObject(customer);
        mv.addObject("total", customer.getCart().getItemList().stream().mapToDouble(i -> i.getGame().getPrice() * i.getAmount().doubleValue()).sum());
        mv.addObject("documentTypes", documentTypes.findAll());
        mv.addObject("order", new Order());

        customers.isCurrentUserLoggedIn(customer.getId(), mv);
        return mv;
    }

    @PostMapping("/additem")
    public ModelAndView addCheckoutItem(@PathVariable("id") Customer customer,
                                        Long idGame,
                                        Integer amount) {
        if(!customers.isCurrentUserLoggedIn(customer.getId()))
            return new ModelAndView("redirect:/");

        cartService.addCartItem(customer, idGame, amount);

        ModelAndView mv = new ModelAndView("customers/checkout");
        mv.addObject(customer);
        mv.addObject("documentTypes", documentTypes.findAll());


        return mv;
    }

    @PostMapping("")
    public ModelAndView finishCheckout(@PathVariable("id") Customer customer, @Valid Order order) {
        ModelAndView mv = new ModelAndView("redirect:/customer/" + customer.getId() + "/my-orders");
        order.setCustomer(customer);

        orderService.saveAndFlush(order);
        System.out.println(customer);
        System.out.println(order);

        return mv;
    }

    @GetMapping("/calculatetax")
    public ResponseEntity calculateTax(@RequestParam("postalcode") String postalCode) {

        Fretenator fretenator = new Fretenator();
        fretenator.codServico("41106");
        fretenator.codFormato(1);
        fretenator.altura(11d);
        fretenator.largura(12d);
        fretenator.comprimento(16d);
        fretenator.peso("0,450");
        fretenator.cepOrigem("01010-010");
        fretenator.cepDestino(postalCode);
        FretenatorResult result = fretenator.result();
        FretenatorResultItem servico = result.getServico(41106);


        if(servico.getErro())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(servico.getValor());
    }

    @GetMapping("/findcoupon")
    public ResponseEntity findCoupon(@RequestParam("code") String code) {
        Coupon coupon = couponService.findByCodeAndAmountGreaterThan(code, 0);

        if(null == coupon)
            return ResponseEntity.notFound().build();

        CouponDTO couponDTO = new CouponDTO(coupon.getCode(), coupon.getValue(), coupon.getId());

        return ResponseEntity.ok(couponDTO);
    }
}
