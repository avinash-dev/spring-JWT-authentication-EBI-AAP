package uk.ac.ebi.pride.authenticator.service;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import uk.ac.ebi.tsc.aap.client.model.Domain;
import uk.ac.ebi.tsc.aap.client.model.User;
//import uk.ac.ebi.tsc.aap.client.repo.DomainService;
import uk.ac.ebi.tsc.aap.client.security.TokenAuthenticationService;
import uk.ac.ebi.tsc.aap.client.security.TokenHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

import lombok.extern.slf4j.Slf4j;


//@Component
@Slf4j
public class TokenWithManagedDomainsService /*extends TokenAuthenticationService*/ {


    /*private DomainService domainService;

    public TokenWithManagedDomainsService(TokenHandler tokenHandler, DomainService domainService) {
        super(tokenHandler);
        this.domainService = domainService;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        Authentication authentication = super.getAuthentication(request);
        if (authentication == null) {
            return null;
        }
        try {
            User loggedIn = (User) authentication.getDetails();
            Collection<Domain> managedDomains = this.domainService.getMyManagementDomains(getTokenFromRequest(request));
            //managed domains are added to domains list with a name "aap.admin.(managedDomainReference), that corresponds
            //both the actual entity model used in Domains microservice and the convention used in domains microservice AuthZ
            //
            managedDomains.stream().forEach(domain -> domain.setDomainName("aap.admin." + domain.getDomainReference()));
            loggedIn.getDomains().addAll(managedDomains);
        } catch (Exception e) {
            //Poor-man's Circuit Breaker: in case of any error, we let the method run anyway without information about managed domains
            log.warn("Exception '{}' while retrieving managed domains", e.getMessage());
        }
        return authentication;
    }

    *//**
     * Extracts auth token from request
     *//*
    public String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];
    }*/
}