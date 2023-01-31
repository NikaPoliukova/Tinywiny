package com.example.tinywiny.controller;

import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class OrderController {
  private final OrderService orderService;
  //КОГДА ДЕЛАЕТСЯ ЗАКАЗ ЧИСЛО НА СКЛАДЕ ДОЛЖНО УМЕНЬШАТЬСЯ
/*




  //for ADMIN
  @GetMapping()
  public String filterOrdersByPage(final Model model,
                                   @RequestParam(name = "status", required = false) String status,
                                   @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
    Page<Order> page = orderService.filterOrders(status, pageNumber - 1, pageSize);
    List<Order> orders = page.getContent();
    model.addAttribute("currentPage", pageNumber);
    model.addAttribute("totalItems", page.getTotalElements());
    model.addAttribute("totalPages", page.getTotalPages());
    model.addAttribute("orders", orders);
    model.addAttribute("pageSize", pageSize);
    return "page-all-orders";
  }

  @PostMapping("/update-status")
  public String updateOrderStatus(long orderNumber, String status) {
    orderService.updateOrderStatus(orderNumber, status);
    return "order";
  }*/
}
