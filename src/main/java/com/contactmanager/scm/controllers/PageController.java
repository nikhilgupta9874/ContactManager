package com.contactmanager.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.contactmanager.scm.entities.User;
import com.contactmanager.scm.forms.UserForm;
import com.contactmanager.scm.helpers.Message;
import com.contactmanager.scm.helpers.MessageType;
import com.contactmanager.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;





@Controller
public class PageController {

    private UserService userService;
    public PageController(UserService userService)
    {
        this.userService = userService;
    }

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
    public String register(Model model) {
        UserForm userForm = new UserForm();
        // userForm.setName("Nikhil");
        // userForm.setPhoneNo("9779118093");
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @RequestMapping(value="/do-register", method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNo(userForm.getPhoneNo())
        // .build();

        if(rBindingResult.hasErrors())
        {
            return "register";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNo(userForm.getPhoneNo());
        userService.saveUser(user);
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message", message);
        return "redirect:/register";
    }
    
}
