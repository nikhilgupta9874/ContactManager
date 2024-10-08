package com.contactmanager.scm.services;

import java.util.List;

import org.springframework.data.domain.Page;


import com.contactmanager.scm.entities.Contact;
import com.contactmanager.scm.entities.User;

public interface ContactService {

    Contact save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAll();

    Contact getById(String id);

    void delete(String id);

    List<Contact> search(String name, String email, String phoneNo);

    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user, int page, int size, String sortBy, String dir);
}