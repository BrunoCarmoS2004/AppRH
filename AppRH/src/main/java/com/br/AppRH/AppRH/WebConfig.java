package com.br.AppRH.AppRH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfiguration{
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder)throws Exception{
        //O NOOP QUER DIZER Q VC NÃO ESTA CRIPTOGRAFANDO A SENHA
        //Quando fizer o login com o usuario não vai levar isso em conta
        //ESTÃO SEPARADOS, MAS É COMO SE FOSSE NA MESMA LINHA
        builder
        .inMemoryAuthentication()
        .withUser("bruno").password("{noop}bruno").roles("USER")
        .and()
        .withUser("root").password("{noop}root").roles("ADMIN");
    }
   protected SecurityFilterChain  configure(HttpSecurity http) throws Exception{
    http.security()
                .antMatchers("/topicos").permitAll()
                .antMatchers("/topicos/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
    return http.build();
   }
}
