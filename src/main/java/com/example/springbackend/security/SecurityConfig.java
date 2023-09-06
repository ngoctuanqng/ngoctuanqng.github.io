package com.example.springbackend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

   @Bean
   public UserDetailsManager userDetailsManager(DataSource dataSource) {

       JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);

       theUserDetailsManager.
               setUsersByUsernameQuery("select username, password, enabled from user_info where username = ?");

       theUserDetailsManager.
               setAuthoritiesByUsernameQuery("select username, authority from user_info where username = ?");

       return theUserDetailsManager;
   }

   @Bean
   public SecurityFilterChain filterConfig(HttpSecurity http) throws Exception {
       http.authorizeHttpRequests(configurer ->
               configurer
                       .requestMatchers("/").permitAll()
//                     .requestMatchers("/**").permitAll()
                       .requestMatchers("/css/**").permitAll()
                       .requestMatchers("/js/**").permitAll()
                       .requestMatchers("/img/**").permitAll()
                       .requestMatchers("/showSignUpPage").permitAll()
                       .requestMatchers("/subscribe").permitAll()
                       .requestMatchers("/user/signUpToken").permitAll()
                       .requestMatchers("/signUpTokenConfirm").permitAll()
                       .requestMatchers(HttpMethod.GET, "/showInfoPage").hasAnyRole("USER", "EMPLOYEE", "MANAGER", "ADMIN")
                       .requestMatchers(HttpMethod.GET, "/findByUserLoggedIn").hasAnyRole("USER", "EMPLOYEE", "MANAGER", "ADMIN")
                       .requestMatchers(HttpMethod.GET, "/showTransferPage").hasAnyRole("USER", "EMPLOYEE", "MANAGER", "ADMIN")
                       .requestMatchers(HttpMethod.POST, "/user/transferMoney").hasAnyRole("USER", "EMPLOYEE", "MANAGER", "ADMIN")
                       .requestMatchers(HttpMethod.GET, "/showPaymentPage").hasAnyRole("USER", "EMPLOYEE", "MANAGER", "ADMIN")
                       .requestMatchers(HttpMethod.POST, "/user/payTheBill").hasAnyRole("USER", "EMPLOYEE", "MANAGER", "ADMIN")
                       .requestMatchers(HttpMethod.GET, "/showChangingPassword").hasAnyRole("USER", "EMPLOYEE", "MANAGER", "ADMIN")
                       .requestMatchers(HttpMethod.POST, "/user/changePassword").hasAnyRole("USER", "EMPLOYEE", "MANAGER", "ADMIN")
                       .requestMatchers(HttpMethod.GET, "/showTransactionHistory").hasAnyRole("USER", "EMPLOYEE", "MANAGER", "ADMIN")
                       .requestMatchers(HttpMethod.GET, "/admin/listOfUsers").hasRole("ADMIN")








                       .requestMatchers(HttpMethod.GET, "/").hasAnyRole("USER", "MANAGER", "ADMIN")
                       .requestMatchers(HttpMethod.GET, "/").hasAnyRole("USER", "MANAGER", "ADMIN")
                       .requestMatchers(HttpMethod.GET, "/user").hasRole("MANAGER")
                       .requestMatchers(HttpMethod.GET, "/user/**").hasRole("MANAGER")
                       .requestMatchers(HttpMethod.PUT, "/user").hasAnyRole("ADMIN", "MANAGER")
                       .requestMatchers(HttpMethod.POST, "/user/update").hasAnyRole("ADMIN", "MANAGER")
                       .requestMatchers(HttpMethod.POST, "/addTodo").hasAnyRole("ADMIN", "MANAGER")
                       .requestMatchers(HttpMethod.DELETE, "/user/findById/**").hasRole("ADMIN")
                       .requestMatchers(HttpMethod.DELETE, "/user/findByUsername/**").hasRole("ADMIN")
                       .requestMatchers(HttpMethod.DELETE, "/user/delete/userId/**").hasRole("ADMIN")
                       .requestMatchers(HttpMethod.DELETE, "/user/delete/userName").hasAnyRole("ADMIN", "MANAGER")
                       .requestMatchers(HttpMethod.POST, "/user/transferMoney").hasRole("MANAGER")
                       .requestMatchers(HttpMethod.POST, "/user/delete/{username}").hasAnyRole("ADMIN", "MANAGER")
                       .requestMatchers(HttpMethod.DELETE, "/user/delete/**").hasAnyRole("ADMIN", "MANAGER")
                       .requestMatchers(HttpMethod.GET, "/user/delete/**").hasAnyRole("ADMIN", "MANAGER")
                       .requestMatchers(HttpMethod.PUT, "/user/delete/**").hasAnyRole("ADMIN", "MANAGER")
                       .requestMatchers(HttpMethod.POST, "/user/delete/**").hasAnyRole("ADMIN", "MANAGER")
                       .requestMatchers(HttpMethod.POST, "/user/showUpdateUserForm").hasAnyRole("ADMIN", "MANAGER")
                       .requestMatchers(HttpMethod.POST, "/user/update").hasAnyRole("ADMIN", "MANAGER")

               )
               .formLogin(form ->
                       form
                           .loginPage("/showMyLoginPage")
                           .loginProcessingUrl("/authenticateTheUser")
                           .defaultSuccessUrl("/", true)
                           .permitAll()
               )
               .logout(logout ->
                        logout
                            .logoutSuccessUrl("/")
                            .permitAll());

       // use HTTP Basic authentication
//        http.httpBasic();

//         use HTTP Basic authentication
       http.httpBasic(Customizer.withDefaults());

//         disable Cross Site Request Forgery (CSRF)
//         in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
       http.csrf(csrf -> csrf.disable());

       return http.build();
   }

//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }





}
