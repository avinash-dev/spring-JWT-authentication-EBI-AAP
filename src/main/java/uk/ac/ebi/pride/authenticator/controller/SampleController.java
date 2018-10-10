package uk.ac.ebi.pride.authenticator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SampleController {

    @Value("${testVal}")
    String testVal;

    @ResponseBody
    @RequestMapping(path="/msg",method = RequestMethod.GET)
    public String getMsg(){
        return "HW! "+testVal;
    }

    @ResponseBody
    @RequestMapping(path="/test",method = RequestMethod.GET)
    public String getBigTestMsg(Principal principal){
        if(principal!=null)
            return "Test"+principal.getName();
        else
            return "Test but no principal";
    }

    @ResponseBody
    @RequestMapping(path="/authmsg",method = RequestMethod.GET)
    public String getAuthenticatedMsg(Principal principal){
        return "HW! "+principal.getName();
    }

    @ResponseBody
    @RequestMapping(path="/myauthorities",method = RequestMethod.GET)
    public List<String> getBigTestMsg(Authentication authentication){
        List<String> auths = new ArrayList<String>();
        authentication.getAuthorities().stream().forEach(x -> auths.add(x.getAuthority()));
        return auths;
    }

    @ResponseBody
    @RequestMapping(path="/forusers",method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_self.pride')")
    public String getForUsersMsg(Principal principal){
        return "Role USER:"+principal.toString();
    }
}
