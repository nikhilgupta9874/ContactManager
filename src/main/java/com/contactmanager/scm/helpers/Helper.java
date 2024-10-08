package com.contactmanager.scm.helpers;



import org.springframework.security.core.Authentication;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication)
    {
        // AuthenticationPrincipal principal = (AuthenticationPrincipal)authentication.getPrincipal();

        if(authentication instanceof OAuth2AuthenticatedPrincipal)
        {
            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            if(clientId.equalsIgnoreCase("google"))
            {
                System.out.println("Getting email from google");
            }
            return "";
        }
        else
        {
            return authentication.getName();
        }
        
    }
}
