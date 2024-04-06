package com.dinsaren.hrmanagementsystemapplication.configurations;

import com.dinsaren.hrmanagementsystemapplication.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSpringSecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;

    private final LogoutSuccessHandlerConfig logoutSuccessHandler;

    private final AuthenticationSuccessHandler successHandler;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    public WebSpringSecurityConfig(UserDetailsServiceImpl userDetailsService, LogoutSuccessHandlerConfig logoutSuccessHandler, AuthenticationSuccessHandler successHandler, AuthenticationEntryPoint authenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.successHandler = successHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((authorize) -> authorize.requestMatchers("/", "/about", "/policy", "/contact", "/image/**", "/menu", "/values", "/special", "/register/**").permitAll().anyRequest().authenticated()).formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login").usernameParameter("phoneNumber").passwordParameter("password").successHandler(successHandler).failureUrl("/login?status=1").permitAll()).logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessHandler(logoutSuccessHandler).permitAll()).exceptionHandling(except -> except.authenticationEntryPoint(authenticationEntryPoint).accessDeniedPage("/accessdenied")

        );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/js/**", "/css/**", "/lang/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
