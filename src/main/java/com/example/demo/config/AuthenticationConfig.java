package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.admin.username}") private String adminUsername;
    @Value("${security.admin.password}") private String adminPassword;
    @Value("${security.admin.role}") private String adminRole;

    @Value("${security.general.username}") private String generalUsername;
    @Value("${security.general.password}") private String generalPassword;
    @Value("${security.general.role}") private String generalRole;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(adminUsername).password(encoder.encode(adminPassword)).authorities(adminRole)
                .and()
                .withUser(generalUsername).password(encoder.encode(generalPassword)).authorities(generalRole);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/persons").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/persons/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/persons/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/persons/**").permitAll()
                .anyRequest()
                .authenticated()
                .and().httpBasic();

    }

  /*  @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/


}
