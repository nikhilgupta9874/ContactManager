package com.contactmanager.scm.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String userId;
    @Column(name = "user_name", nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Column(length = 1000)
    private String about;

    @Column(length = 1000)
    private String profilePic;
    private String phoneNo;
    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    @Enumerated(EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    
}
