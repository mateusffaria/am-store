package br.com.les.amstore.controller;

import br.com.les.amstore.domain.Coupon;
import br.com.les.amstore.service.ICustomersService;
import br.com.les.amstore.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class CouponController {
    @Autowired
    private IGenericService<Coupon> couponService;

    @Autowired
    private ICustomersService customers;

    @GetMapping("/coupons")
    public ModelAndView listCoupons() {
        ModelAndView mv = new ModelAndView("/admin/listCoupons");

        mv.addObject("coupons", couponService.findAll());
        mv.addObject("admin", customers.currentUserLoggedIn());

        return mv;
    }

    @GetMapping("/coupons/new")
    public ModelAndView newCoupons(Coupon coupon) {
        ModelAndView mv = new ModelAndView("/admin/newCoupom");
        mv.addObject(coupon);
        mv.addObject("admin", customers.currentUserLoggedIn());

        return mv;
    }

    @PostMapping("/coupons/new")
    public ModelAndView createCoupons(@Valid Coupon coupon, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()){
            return newCoupons(coupon);
        }

        couponService.saveAndFlush(coupon);

        ModelAndView mv = new ModelAndView("redirect:/admin/coupons");
        mv.addObject(coupon);
        mv.addObject("admin", customers.currentUserLoggedIn());
        attributes.addFlashAttribute("message", "Cupom criado com sucesso");

        return mv;
    }

}
