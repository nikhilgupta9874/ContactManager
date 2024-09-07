package com.contactmanager.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class PageController {
    @RequestMapping("/home")
    public String home(Model model)
    {
        System.out.println("Home page handler");
        model.addAttribute("name", "Nikhil");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("This is about page");
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("This is service page");
        return "service";
    }

    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    @GetMapping("/register")
    public String register() {
        return new String("register");
    }
    
}
