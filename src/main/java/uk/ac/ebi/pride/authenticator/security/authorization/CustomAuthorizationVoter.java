package uk.ac.ebi.pride.authenticator.security.authorization;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import uk.ac.ebi.tsc.aap.client.model.Domain;
import uk.ac.ebi.tsc.aap.client.model.User;
import uk.ac.ebi.tsc.aap.client.security.UserAuthentication;

import java.security.Principal;
import java.util.Collection;
import java.util.Set;

public class CustomAuthorizationVoter implements AccessDecisionVoter {
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    //the domain object from JWT token doesn't contain desc and ref parameters
    private Domain prideDomain = new Domain("self.pride",/*"EBI PRIDE project"*/null,/*"dom-58592754-3fe9-47cf-aa09-60233d771d0b"*/null);

    @Override
    public int vote(Authentication authentication, Object object, Collection collection) {
        try{
            User currentUser = (User) (authentication).getDetails();
            Set<Domain> domainSet = currentUser.getDomains();
            if(domainSet.contains(prideDomain)){
                return ACCESS_GRANTED;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return ACCESS_DENIED;
    }

    @Override
    public boolean supports(Class clazz) {
        return true;
    }
}
