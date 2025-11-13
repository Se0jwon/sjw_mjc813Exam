//package com.mjc813.swimpool_app.security;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Slf4j
//@Component
//public class SameSiteFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request
//            , HttpServletResponse response
//            , FilterChain filterChain) throws ServletException, IOException {
//
//        log.debug("doFilterInternal...");
//        filterChain.doFilter(request, response);
//    }
//}
