/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.empleados.minicontrolempleados;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 *
 * @author a_cle
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails user1= User
                .withUsername("Agustin")
                .password("$2a$10$WRW37Mpd/F.jWEMcurAzKeVChl01iEkSP1oMd1xwHe3v03u2awJMa")
                .roles("ADMIN")
                .build();
        
         UserDetails user2= User
                .withUsername("User")
                .password("$2a$10$WRW37Mpd/F.jWEMcurAzKeVChl01iEkSP1oMd1xwHe3v03u2awJMa")
                .roles("USER")
                .build();
         
         return new InMemoryUserDetailsManager(user1,user2);
   }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/formulario/*", "/eliminar/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout().permitAll();
    }
    
}
