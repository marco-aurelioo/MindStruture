package com.tiozao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http
                .csrf()
                .disable()

                .authorizeRequests()
                    .antMatchers(
                        "/cadastro**",
                        "/static/**",
                        "/css/**",
                        "/js/**",
                        "/img/**",
                        "/h2/*",
                        "/vendor/**",
                        "/webjars/**",
                        "/admin/**", "/favicon.ico",
                        "/resources/**", "/auth/**", "/signin/**", "/signup/**", "/disconnect/facebook"
                    )
                    .permitAll()

                .anyRequest()
                    .authenticated()

                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/usuario",true)
                    .permitAll()
                .and()
                    .logout().permitAll();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
   }
}
