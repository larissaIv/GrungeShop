package com.example.grungeshop.web;

import com.example.grungeshop.model.DTO.OrderDTO;
import com.example.grungeshop.model.DTO.ProductDTO;
import com.example.grungeshop.model.entities.OrderEntity;
import com.example.grungeshop.services.OrderService;
import com.example.grungeshop.services.ProductService;
import com.example.grungeshop.services.UserService;
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
    private final ProductService productService;
    private final UserService userService;

    public HomeController(OrderService orderService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    @ModelAttribute("productDTO")
    public ProductDTO initProductDTO() {
        return new ProductDTO();
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

    @GetMapping("/index")
    public String home(Model model) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        List<OrderEntity> allProducts = this.productService.getAllProducts();

        model.addAttribute("allProducts", allProducts);

        return "index";
    }

    @GetMapping("/products/add")
    public String addProduct() {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        return "add-products";
    }

    @PostMapping("/products/add")
    public String products(@Valid ProductDTO productDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }


        if (bindingResult.hasErrors() || !this.productService.buyProducts(productDTO)) {
            redirectAttributes.addFlashAttribute("productDTO", productDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productDTO", bindingResult);

            return "redirect:/products/add";
        }

        return "redirect:/";
    }
}