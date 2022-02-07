package com.med.medservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.med.medservice.model.Role.ADMIN;
import static com.med.medservice.model.Role.USER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

/**
 * SecurityConfig
 * <p>
 * </p>
 *
 * @author Platonov Alexey
 * @since 18.11.2021
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            //super.configure(http);
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers(POST, "/api/v1/doctor").hasAnyRole(ADMIN.name(), USER.name())
                    .antMatchers(GET, "/api/v1/doctor").hasAnyRole(ADMIN.name())
                    .antMatchers(GET, "/api/v1/doctor/**").hasAnyRole(ADMIN.name(), USER.name())
                    .antMatchers(PUT, "/api/v1/doctor/**").hasAnyRole(ADMIN.name(), USER.name())
                    .antMatchers(DELETE, "/api/v1/doctor/**").hasAnyRole(ADMIN.name())
                    .antMatchers(GET, "/api/v1//certificate/**").hasAnyRole(ADMIN.name())
                    .antMatchers(GET, "/api/v1//certificate").hasAnyRole(ADMIN.name(), USER.name())
                    .antMatchers(POST, "/api/v1//certificate").hasAnyRole(ADMIN.name(), USER.name())
                    .antMatchers(PUT, "/api/v1//certificate/**").hasAnyRole(ADMIN.name(), USER.name())
                    .antMatchers(DELETE, "/api/v1//certificate/**").hasAnyRole(ADMIN.name())
                    .antMatchers("/auth").authenticated()
                    .and().httpBasic()
                    .and().sessionManagement().disable();

//        http.csrf().disable()
//            .authorizeRequests().anyRequest().authenticated()
//            .and().httpBasic()
//            .and().sessionManagement().disable();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("p@55w0Rd"))
                        .roles(ADMIN.name())
                        .build(),
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("p@55w0Rd"))
                        .roles(USER.name())
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


/*    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
            User.withDefaultPasswordEncoder()
                .username("admin")
                .password("p@55w0Rd")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }*/
}
