//package com.mjc813.swimpool_app.security;
//
//import com.mjc813.swimpool_app.user.dto.UserDto;
//import com.mjc813.swimpool_app.user.service.UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.CookieValue;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.SessionAttribute;
//
//
//@Slf4j
//@ControllerAdvice
//public class Mjc813ControllerAdvice {
//    @Autowired
//    private UserService userService;
//
//    @ModelAttribute
//    public void ControllerCheking(HttpServletRequest request
//            , HttpServletResponse response
//            , Model model
//            , @CookieValue(name = "mjc813", required = false) String idStr
//            , @SessionAttribute(name = "mjc813", required = false) Long idLong
//    ) {
//        log.debug("ControllerCheking...{}", request.getRequestURI());
//        if ( idStr == null && idLong == null) {
//            return;
//        }
//        long id = idStr != null ? Long.parseLong(idStr) : idLong;
//        UserDto find = this.userService.findById(id);
//        if ( find == null ) {
//            model.addAttribute("loginUser", null);
//        }
//        model.addAttribute("loginUser", find);
//    }
//}
