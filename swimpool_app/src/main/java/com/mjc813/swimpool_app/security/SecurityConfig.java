package com.mjc813.swimpool_app.security;

import com.mjc813.swimpool_app.security.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordEncoder encoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
//                        req.anyRequest().permitAll()    // 이 서버는 클라이언트의 어떠한 요청이든지 허락해주세요.
                        req
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/js/**").permitAll()
                                .requestMatchers("/smbg/**").permitAll()
                                .requestMatchers("/loginpage/**").permitAll()
                                .requestMatchers("/api/v1/spring_mpa/**").permitAll()
                                .requestMatchers("/api/v1/session_mpa/**").permitAll()
                                .requestMatchers("/api/v1/cookie_mpa/**").permitAll()
                                .requestMatchers("/api/v1/spring_rest/**").permitAll()
                                .requestMatchers("/api/v1/session_rest/**").permitAll()
                                .requestMatchers("/api/v1/cookie_rest/**").permitAll()
                                .requestMatchers("/api/v1/findandchangepassword").permitAll()
                                .requestMatchers("/swimpool/**").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers("/api/v1/swimpool/**").hasAnyAuthority("USER", "ADMIN")
                                .requestMatchers("/user/**").hasAuthority("ADMIN")
                                .requestMatchers("/api/v1/user/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated()    // 이 서버는 클라이언트의 어떠한 요청이든지 인증을 하세요.
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(daoAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(loginService);
        authenticationProvider.setPasswordEncoder(encoder);
        return authenticationProvider;
    }

    @Bean
    public Mjc813AuthenticateFilter daoAuthTokenFilter() {
        return new Mjc813AuthenticateFilter();
    }
}
