package aplus.insurancesystem2.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import aplus.insurancesystem2.common.security.AplusAccessDeniedHandler;
import aplus.insurancesystem2.common.security.AplusAuthenticationFailureHandler;
import aplus.insurancesystem2.common.security.AplusAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SimpleUrlAuthenticationSuccessHandler aplusLoginSuccessHandler() {
        return new AplusAuthenticationSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler aplusAccessDeniedHandler() {
        return new AplusAccessDeniedHandler();
    }

    @Bean
    public AuthenticationFailureHandler aplusLoginFailureHandler() {
        return new AplusAuthenticationFailureHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/customer/**").hasRole("CUSTOMER")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/", "/보험조회").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin((login) -> login
//                        .loginPage("/asdasdsd")
                        .loginProcessingUrl("/login")
                        .usernameParameter("loginId")
                        .passwordParameter("password")
                        .successHandler(aplusLoginSuccessHandler())
                        .failureHandler(aplusLoginFailureHandler())
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/보안검사X1", "/보안검사X2");
    }
}
