package com.ecomsec.config;

import com.ecomsec.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Bean
        PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
        private CustomUserDetailsService userDetailsService;


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/auth/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                    //    .antMatchers(HttpMethod.POST, "/api/auth/signup").hasRole("ROLE_SUPER")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .sessionManagement()
                    .maximumSessions(1) // Allow only one session per user
                    .and().and()
                    .httpBasic();
        }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Override
        @Bean  //you can only use bean annotation in config classes
        protected UserDetailsService userDetailsService() {
            UserDetails user1 = User.builder().username("pankaj").password(passwordEncoder()
                    .encode("password")).roles("USER").build();
            UserDetails user2 = User.builder().username("admin").password(passwordEncoder()
                    .encode("admin")).roles("ADMIN").build();
            return new InMemoryUserDetailsManager(user1, user2);
        }



    }
