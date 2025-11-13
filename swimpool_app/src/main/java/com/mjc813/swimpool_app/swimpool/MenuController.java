package com.mjc813.swimpool_app.swimpool;

import com.mjc813.swimpool_app.user.dto.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

//    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/swimpool")
    public String swimpoolMenu(HttpServletRequest request
                               , HttpServletResponse response
                               , HttpSession session
    ) {
        Cookie[] cookies = request.getCookies();
        for ( Cookie cookie : cookies ) {
            System.out.println("read Cookie : " + cookie.getValue());
        }
        HttpSession httpSession = request.getSession();
        System.out.println("read HttpSession getMaxInactiveInterval : " + httpSession.getMaxInactiveInterval());
        System.out.println("read HttpSession : " + httpSession.getAttribute("mysession"));
        System.out.println("read httpSession : " + httpSession.getAttribute("mysession2"));
        System.out.println("read HttpSession : " + session.getAttribute("mysession"));
        System.out.println("read httpSession : " + session.getAttribute("mysession2"));
        return "/swimpool/list";
    }

    @GetMapping("/makecookie")
    public String makecookie(HttpServletRequest request
            , HttpServletResponse response
            , HttpSession session
    ) {
        Cookie ck = new Cookie("mycookie", "Teacher");
        ck.setHttpOnly(true);
        ck.setSecure(true);
        ck.setMaxAge( 60 * 3 * 1 * 1 );
        ck.setPath("/");
        response.addCookie(ck);
        return "redirect:/";
    }

    @GetMapping("/deletecookie")
    public String deletecookie(HttpServletRequest request
            , HttpServletResponse response
            , HttpSession session
    ) {
        Cookie ck = new Cookie("mycookie", "");
        ck.setHttpOnly(true);
        ck.setSecure(true);
        ck.setMaxAge( 0 );
        ck.setPath("/");
        response.addCookie(ck);
        return "redirect:/";
    }

    @GetMapping("/makesession")
    public String makesession(HttpServletRequest request
            , HttpServletResponse response
            , HttpSession session
    ) {
        HttpSession httpSession = request.getSession();
        session.setAttribute("mysession", "Teacher");
        httpSession.setAttribute("mysession2", "Teacher2");
        return "redirect:/";
    }

    @GetMapping("/deletesession")
    public String deletesession(HttpServletRequest request
            , HttpServletResponse response
            , HttpSession session
    ) {
        session.invalidate();
        //HttpSession httpSession = request.getSession();
        //httpSession.invalidate();
        return "redirect:/";
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user")
    public String userMenu(HttpServletRequest request
            , HttpServletResponse response
            , HttpSession session
            , Model model
    ) {
//        if ( model.getAttribute("loginUser") == null ) {
//            return "redirect:/";
//        }
        return "/user/userlist";
    }

    @GetMapping("/loginpage")
    public String loginMenu(HttpServletRequest request
            , HttpServletResponse response
            , HttpSession session
            , Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ( authentication == null ) {
            model.addAttribute("loginUser", null);
        }
        if ( authentication.getPrincipal() instanceof UserDto ) {
            model.addAttribute("loginUser", ((UserDto) authentication.getPrincipal()));
        } else {
            model.addAttribute("loginUser", null);
        }
        return "/login/loginpage";
    }

    @GetMapping("/loginpage/findpasswd")
    public String findPasswdMenu() {
        return "/login/findpasswd";
    }
}
