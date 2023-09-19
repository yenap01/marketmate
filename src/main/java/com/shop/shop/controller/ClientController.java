package com.shop.shop.controller;

import com.shop.shop.dto.ClientFormDto;
import com.shop.shop.entity.Client;
import com.shop.shop.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/clients")
@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/clients/new")
    public String clientForm(Model model) {
        model.addAttribute("ClientFormDto", new ClientFormDto());
        return "clients/ClientForm";
    }

    @PostMapping("/clients/new")
    public String clientForm(@Valid ClientFormDto clientFormDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "clients/ClientForm";
        }

        try {
            Client client = Client.createClient(clientFormDto, passwordEncoder);
            clientService.saveClient(client);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "clients/ClientForm";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginClient() {
        return "clients/ClientLoginForm";
    }

    @GetMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비빌번호를 확인해 주세요");
        return "clients/ClientLoginForm";
    }

}
