package com.bakir.patients_mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    return httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(registry->{
  registry.requestMatchers("/","/register/**").permitAll();
        registry.requestMatchers("/admin/**").hasRole("ADMIN");
        registry.requestMatchers("/user/**").hasRole("USER");
        registry.anyRequest().authenticated();
    }).formLogin(httpSecurityFormLoginConfigurer -> {
        httpSecurityFormLoginConfigurer.
                loginPage("/login")
//                successHandler( new AuthenticationSuccessHandler())
                .permitAll();
            })//AbstractAuthenticationFilterConfigurer::permitAll
    .build();
}
//From memory
//@Bean
//    public UserDetailsService userDetailsService() {
//    UserDetails normaleUser= User.builder()
//            .username("sc")
//            .password("$2a$12$jrc4HdX7JYxD50uJJfdc1.COxUTJU55E1KcoWeQUBCxW.HA2DRjZu")
//            .roles("USER")
//            .build();
//    UserDetails adminUser= User.builder()
//            .username("admin")
//            .password("$2a$12$wlai/Jo4VufNNesGbBd7/.u7ZV8hDJYUaK0e1.MJ08dPwL/VUdzrK")
//            .roles("ADMIN","USER")
//            .build();
//    return new InMemoryUserDetailsManager(normaleUser, adminUser);
//}

    @Bean
    public UserDetailsService userDetailsService() {
return myUserDetailsService;
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(myUserDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
    }
@Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
}
