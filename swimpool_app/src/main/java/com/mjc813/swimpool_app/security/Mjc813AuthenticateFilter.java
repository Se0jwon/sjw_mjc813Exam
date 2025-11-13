package com.mjc813.swimpool_app.security;


import com.mjc813.swimpool_app.user.dto.UserDto;
import com.mjc813.swimpool_app.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class Mjc813AuthenticateFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {

        try {
            Object obj = request.getSession().getAttribute("mjc813");
            if (obj == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String userId = obj.toString();
            if (userId != null) {
                UserDto find = this.userService.findById(Long.parseLong(userId));
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                find,
                                null,
                                find.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Throwable e) {
            log.error(e.toString());
        }
        filterChain.doFilter(request, response);
    }
}
