package com.example.Task.Management.Configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigration {

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()

                // public endpoints
                .authorizeHttpRequests()
                .requestMatchers("/user/addUser","/admin/addAdmin")
                .permitAll()

                // user endpoints
                .requestMatchers("/user/addTask","/user/getTasks","/user/updateTask","/user/removeTask/taskid/{taskid}")
                .hasAnyRole("USER","ADMIN")

                //admin endpoints
                .requestMatchers("/admin/getAllUsersWithAssignedTasks","/admin/getAllTasks")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService getUserDetailsService(){

        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider (){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        return daoAuthenticationProvider;
    }
}
