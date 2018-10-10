package uk.ac.ebi.pride.authenticator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uk.ac.ebi.tsc.aap.client.security.StatelessAuthenticationEntryPoint;
import uk.ac.ebi.tsc.aap.client.security.StatelessAuthenticationFilter;
import uk.ac.ebi.pride.authenticator.service.TokenWithManagedDomainsService;

/**
 * Configuration creates additional Filter Chain, triggered for modifying HTTP methods (POST, PUT, PATCH, DELETE)
 * under /profiles/**.
 * Config has higher order than {@link WebSecurityConfig}, so runs first, and {@link WebSecurityConfig} runs as a fallback.
 * Replaces the filter calling TokenAuthenticationService with a filter calling TokenWithManagedDomainsService,
 * which makes an external call to Domains microservice and adds info about domains,
 * that an authenticated user manages, to an {@link org.springframework.security.core.Authentication} object.
 * The filter chain gets triggered for both User and Domain Profile methods, though currently only Domain Profiles
 * need enhanced authentication information.
 *
 * @author aniewielska
 * @since 15/03/2018
 */
/*@Order(1)
@Configuration*/
public class ModifyingMethodsHttpSecurityConfig extends WebSecurityConfigurerAdapter {

    /*private final StatelessAuthenticationEntryPoint unauthorizedHandler;
    private final TokenWithManagedDomainsService tokenAuthenticationService;

    public ModifyingMethodsHttpSecurityConfig(StatelessAuthenticationEntryPoint unauthorizedHandler, TokenWithManagedDomainsService tokenAuthenticationService) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    private StatelessAuthenticationFilter statelessAuthenticationFilterBean() {
        return new StatelessAuthenticationFilter(this.tokenAuthenticationService);
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .requestMatchers()
                .antMatchers(HttpMethod.POST, "/profiles/**")
                .antMatchers(HttpMethod.PUT, "/profiles/**")
                .antMatchers(HttpMethod.PATCH, "/profiles/**")
                .antMatchers(HttpMethod.DELETE, "/profiles/**")
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .anyRequest().authenticated();

        // Custom JWT based security filter
        httpSecurity.addFilterBefore(statelessAuthenticationFilterBean(),
                UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }*/
}