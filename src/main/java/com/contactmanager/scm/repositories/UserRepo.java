package com.contactmanager.scm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactmanager.scm.entities.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, String>{

    Optional<User> findByEmail(String email);

}
