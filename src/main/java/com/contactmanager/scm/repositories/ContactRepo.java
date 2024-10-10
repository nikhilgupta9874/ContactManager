package com.contactmanager.scm.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.contactmanager.scm.entities.Contact;
import com.contactmanager.scm.entities.User;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String>{

    Page<Contact> findByUser(User user, Pageable pageable);

    @Query("select c from Contact c where c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);

    Page<Contact> findByUserAndNameContaining(User user, String name, Pageable pageable);
    Page<Contact> findByUserAndEmailContaining(User user, String email, Pageable pageable);
    Page<Contact> findByUserAndPhoneNumberContaining(User user, String phoneNumber, Pageable pageable);

}
