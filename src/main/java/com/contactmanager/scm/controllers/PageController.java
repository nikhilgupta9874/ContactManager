package com.contactmanager.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


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
}
