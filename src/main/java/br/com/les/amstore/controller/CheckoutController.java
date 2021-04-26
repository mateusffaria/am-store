package br.com.les.amstore.controller;

import br.com.les.amstore.domain.*;
import br.com.les.amstore.dto.CouponDTO;
import br.com.les.amstore.dto.CreditCardDTO;
import br.com.les.amstore.service.*;
import br.com.les.amstore.utils.correiostools.Fretenator;
import br.com.les.amstore.utils.correiostools.FretenatorResult;
import br.com.les.amstore.utils.correiostools.FretenatorResultItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


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

    @Autowired
    private IGenericService<CreditCard> creditCardService;

    @Autowired
    private IGenericService<Banner> bannerService;

    @GetMapping("")
    public ModelAndView getCheckout(@PathVariable("id") Customer customer) {
        ModelAndView mv = new ModelAndView("customers/checkout");
        mv.addObject(customer);
        mv.addObject("total", customer.getCart().getItemList().stream().mapToDouble(i -> i.getGame().getPrice() * i.getAmount().doubleValue()).sum());
        mv.addObject("documentTypes", documentTypes.findAll());
        mv.addObject("order", new Order());
        mv.addObject("creditCard", new CreditCard());
        mv.addObject("banners", bannerService.findAll());

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

        ModelAndView mv = new ModelAndView("redirect:/customer/" + customer.getId() + "/checkout");
        mv.addObject(customer);
        mv.addObject("documentTypes", documentTypes.findAll());


        return mv;
    }

    @DeleteMapping("/removeitem")
    public ModelAndView removeCheckoutItem(@PathVariable("id") Customer customer,
                                        Long idItem) {

        if(!customers.isCurrentUserLoggedIn(customer.getId()))
            return new ModelAndView("redirect:/");

        cartService.removeCartItem(customer, idItem);

        ModelAndView mv = new ModelAndView("redirect:/customer/" + customer.getId() + "/checkout");
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

    @PostMapping("/createcreditcard")
    public ResponseEntity createCard(@PathVariable("id") Customer customer, @Valid CreditCard creditCard,
                                     BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        creditCard.setCustomer(customer);

        CreditCard creditCardResponse = creditCardService.saveAndFlush(creditCard);

        return ResponseEntity.ok(new CreditCardDTO(creditCardResponse.getId(),
                creditCardResponse.getNumber().substring(creditCardResponse.getNumber().length() - 4)));
    }
}
