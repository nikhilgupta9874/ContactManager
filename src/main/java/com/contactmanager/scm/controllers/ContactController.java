package com.contactmanager.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.contactmanager.scm.entities.Contact;
import com.contactmanager.scm.entities.User;
import com.contactmanager.scm.forms.ContactForm;
import com.contactmanager.scm.helpers.AppConstants;
import com.contactmanager.scm.helpers.Helper;
import com.contactmanager.scm.helpers.Message;
import com.contactmanager.scm.helpers.MessageType;
import com.contactmanager.scm.services.ContactService;
import com.contactmanager.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public String addContact(Model model)
    {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result,
     Authentication authentication, HttpSession httpSession)
    {

        if(result.hasErrors()){

            return "user/add_contact";
        }

        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNo());
        contact.setDescription(contactForm.getDescription());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setFavourite(contactForm.isFavourite());
        contact.setUser(user);
        contactService.save(contact);
        httpSession.setAttribute("message", Message.builder().content("Contact Added")
        .type(MessageType.green)
        .build());       
        return "redirect:/user/contacts/add";
    }


    @RequestMapping
    public String viewContacts(@RequestParam(value="page", defaultValue="0") int page,
        @RequestParam(value="size", defaultValue=AppConstants.SIZE + "") int size,
        @RequestParam(value="sortBy", defaultValue="name") String sortBy,
        @RequestParam(value="dir", defaultValue="asc") String dir,
        Model model, Authentication authentication){
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        Page<Contact> pageContact = contactService.getByUser(user, page, size, sortBy, dir);
        
        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize", AppConstants.SIZE);
        return "user/contacts";
    }



    
    @RequestMapping("/search")
    public String searchHandler(@RequestParam("field") String field,
    @RequestParam("keyword") String value,
    @RequestParam(value="size", defaultValue=AppConstants.SIZE + "") int size, 
    @RequestParam(value="page", defaultValue = "0") int page,
    @RequestParam(value="sortBy", defaultValue = "name") String sortBy,
    @RequestParam(value="direction", defaultValue = "asc") String direction,
    Model model, Authentication authentication){

        var user = userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));



        Page<Contact> pageContact = null;
        if(field.equalsIgnoreCase("name"))
        {
            pageContact = contactService.searchByName(value, size, page, sortBy, direction, user);
        }
        else if(field.equalsIgnoreCase("email"))
        {
            pageContact = contactService.searchByEmail(value, size, page, sortBy, direction, user);
        }
        else if(field.equalsIgnoreCase("phone"))
        {
            pageContact = contactService.searchByPhone(value, size, page, sortBy, direction, user);
        }
        model.addAttribute("pageContact", pageContact);
        return "user/search";
    }
}
