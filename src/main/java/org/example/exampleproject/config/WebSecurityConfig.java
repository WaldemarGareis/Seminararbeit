package org.example.exampleproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication().

		withUser("user").password("password").roles("USER").and().

		withUser("admin").password("nimda").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.

		authorizeRequests().antMatchers("/").permitAll().and().

		authorizeRequests().antMatchers("/admin").hasRole("ADMIN").and().

		authorizeRequests().antMatchers("/user").hasAnyRole("USER", "ADMIN").

		anyRequest().authenticated().and().

		formLogin().loginPage("/login").permitAll().and().

		logout().permitAll();
    }

}