package com.example.grungeshop.web;

import com.example.grungeshop.model.DTO.LoginDTO;
import com.example.grungeshop.model.DTO.UserRegisterDTO;
import com.example.grungeshop.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userRegisterDTO")
    private UserRegisterDTO initRegistrationDTO() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("loginDTO")
    public LoginDTO loginDTO() {
        return new LoginDTO();
    }

    @GetMapping("/register")
    public String register() {
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors() || !this.userService.register(userRegisterDTO)) {
            redirectAttributes.addFlashAttribute("userRegisterDTO", userRegisterDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterDTO", bindingResult);

            return "redirect:/register";
        }

        return "redirect:/login";

    }

    @GetMapping("/login")
    public String login() {
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDTO loginDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginDTO", bindingResult);

            return "redirect:/login";
        }

        if (!this.userService.login(loginDTO)) {
            redirectAttributes.addFlashAttribute("loginDTO", loginDTO);

            return "redirect:/login";

        }

        return "redirect:/home";

    }

    @GetMapping("/logout")
    public String logout() {
        this.userService.logout();

        return "redirect:/";
    }
}
