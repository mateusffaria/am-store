package br.com.les.amstore.controller;

import br.com.les.amstore.domain.Devolution;
import br.com.les.amstore.domain.Order;
import br.com.les.amstore.domain.Status;
import br.com.les.amstore.service.ICustomersService;
import br.com.les.amstore.service.IDevolutionService;
import br.com.les.amstore.service.IGenericService;
import br.com.les.amstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    private IGenericService<Order> orderService;

    @Autowired
    private IOrderService orderServiceExclusive;

    @Autowired
    private IGenericService<Status> statusService;

    @Autowired
    private IDevolutionService devolutionService;

    @Autowired
    private ICustomersService customers;

    @GetMapping("/order_return/list")
    public ModelAndView listOrdersReturned() {
        ModelAndView mv = new ModelAndView("/admin/listReturnedOrders");

        mv.addObject("devolutions", devolutionService.findAll());

        mv.addObject("admin", customers.currentUserLoggedIn());

        return mv;
    }

    @GetMapping("/order_return/{id}/edit")
    public ModelAndView editOrderReturned(@PathVariable("id") Devolution devolution) {
        ModelAndView mv = new ModelAndView("/admin/updateOrderReturned");

        mv.addObject("admin", customers.currentUserLoggedIn());
        mv.addObject(devolution);

        return mv;
    }

    @PostMapping("/order_return/{id}/edit")
    public ModelAndView postOrderReturned(Devolution devolution, Double valueWallet) {
        devolutionService.updateDevolutionRequest(devolution, valueWallet);

        return listOrdersReturned();
    }

    @GetMapping("/orders")
    public ModelAndView listOrders() {
        ModelAndView mv = new ModelAndView("/admin/listOrders");
        mv.addObject("orders", orderService.findAll());
        mv.addObject("statuses", statusService.findAll());
        mv.addObject("admin", customers.currentUserLoggedIn());

        return mv;
    }

    @PostMapping("/orders/changestatusorder")
    public ResponseEntity changeStatusOrder(@RequestParam("statusId") Long statusId, Long orderId) {
        Order order = orderService.findById(orderId);
        Status status = statusService.findById(statusId);

        if(order != null && status != null)
            order.setStatus(status);

        orderServiceExclusive.updateOrder(order);

        return ResponseEntity.ok("alterado com sucesso");
    }

}
