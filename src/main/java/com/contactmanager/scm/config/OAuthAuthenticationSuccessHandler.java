package com.contactmanager.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.contactmanager.scm.entities.Providers;
import com.contactmanager.scm.entities.User;
import com.contactmanager.scm.helpers.AppConstants;
import com.contactmanager.scm.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepo userRepo;

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        
        logger.info("OAuthAuthenticationSuccessHandler");
        // response.sendRedirect("/home");

        DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();
        String name = user.getAttribute("name").toString();
        String email = user.getAttribute("email").toString();
        User user1 = new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setUserId(UUID.randomUUID().toString());
        user1.setProvider(Providers.GOOGLE);
        user1.setEnabled(true);
        user1.setEmailVerified(true);
        user1.setProviderUserId(user.getName());
        user1.setRoleList(List.of(AppConstants.ROLE_USER));

        User user2 = userRepo.findByEmail(email).orElse(null);
        if(user2 == null)
        {
            userRepo.save(user1);
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");


    }

}
