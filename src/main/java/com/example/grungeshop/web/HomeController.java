package com.example.grungeshop.web;

import com.example.grungeshop.model.DTO.OrderDTO;
import com.example.grungeshop.model.entities.OrderEntity;
import com.example.grungeshop.services.OrderService;
import com.example.grungeshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController {

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public HomeController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @ModelAttribute("orderDTO")
    public OrderDTO initOrderDTO() {
        return new OrderDTO();
    }

    @GetMapping("/")
    public String loggedOutIndex() {
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        List<OrderEntity> allProducts = this.orderService.getAllProducts();

        model.addAttribute("allProducts", allProducts);

        return "home";
    }

    @GetMapping("/products/add")
    public String addProduct() {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "add-products";
    }

    @PostMapping("/products/add")
    public String products(@Valid OrderDTO orderDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }


        if (bindingResult.hasErrors() || !this.orderService.buyProducts(orderDTO)) {
            redirectAttributes.addFlashAttribute("orderDTO", orderDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDTO", bindingResult);

            return "redirect:/products/add";
        }

        return "redirect:/home";
    }
}