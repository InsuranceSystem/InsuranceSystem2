package aplus.insurancesystem2.common.config;

import aplus.insurancesystem2.domain.security.handler.AplusAccessDeniedHandler;
import aplus.insurancesystem2.domain.security.handler.AplusAuthenticationFailureHandler;
import aplus.insurancesystem2.domain.security.handler.AplusAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SimpleUrlAuthenticationSuccessHandler aplusLoginSuccessHandler() {
        return new AplusAuthenticationSuccessHandler();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler aplusLoginFailureHandler() {
        return new AplusAuthenticationFailureHandler();
    }

    @Bean
    public AccessDeniedHandler aplusAccessDeniedHandler() {
        return new AplusAccessDeniedHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers( "/join", "/verify/id/**", "/authorize/update").permitAll()
//                        .requestMatchers("/customer/**").hasRole("CUSTOMER")
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
//                )
//                .formLogin((login) -> login
//                        .loginPage("/로그인")
//                        .defaultSuccessUrl("/")
//                        .successHandler(aplusLoginSuccessHandler())
//                        .failureHandler(aplusLoginFailureHandler())
//                        .permitAll()
//                )
//                .logout((logout) -> logout
//                        .logoutUrl("/로그아웃")
//                        .logoutSuccessUrl("/로그아웃성공후이동") // 필요?
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                )
//                .exceptionHandling((exception) -> exception
//                        .accessDeniedHandler(aplusAccessDeniedHandler())
//                )
//                .sessionManagement((session) -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                        .invalidSessionUrl("/세션유효X")
//                        .maximumSessions(1)
//                        .expiredUrl("/세션만료후")
                );
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/보안검사X1", "/보안검사X2");
    }
}
