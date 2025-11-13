package com.mjc813.swimpool_app.security.controller;

import com.mjc813.swimpool_app.common.MyString;
import com.mjc813.swimpool_app.security.service.AES256EncDec;
import com.mjc813.swimpool_app.security.service.LoginService;
import com.mjc813.swimpool_app.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class SecurityWebController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private AES256EncDec encDec;

    @PostMapping("/loginpage/finduser")
    public String findUser(@ModelAttribute UserDto userDto) {
        UserDto find = null;
        try {
            if ( userDto == null || userDto.getUsername().isEmpty() || userDto.getName().isEmpty()
                    || userDto.getEmail().isEmpty()  || userDto.getPhoneNumber().isEmpty() ) {
                return "redirect:/loginpage/findpasswd";
            }
            find = (UserDto)this.loginService.loadUserByUsername(userDto.getUsername());
            if ( find == null ) {
                return "redirect:/loginpage/findpasswd";
            }
            find.setPhoneNumber(this.encDec.decrypt_AES(find.getPhoneNumber()));
            find.setEmail(this.encDec.decrypt_AES(find.getEmail()));
            if ( !userDto.getUsername().equals(find.getUsername())
                    || !userDto.getName().equals(find.getName())
                    || !userDto.getPhoneNumber().equals(find.getPhoneNumber())
                    || !userDto.getEmail().equals(find.getEmail())
            ) {
                return "redirect:/loginpage/findpasswd";
            }
            find.setCheckFindValid(MyString.getRandom(12));
            this.loginService.updateChkFindValid(find);
        } catch (Throwable t) {
            log.error(t.toString());
            return "redirect:/";
        }
        return "redirect:/loginpage/changepasswd?id=" + find.getId() + "&chk=" + find.getCheckFindValid();
    }

    @GetMapping("/loginpage/changepasswd")
    public String changePasswd(
            @RequestParam Long id
            , @RequestParam String chk
            , Model model
    ) {
        try {
            if (id == null || chk.isEmpty()) {
                return "redirect:/";
            }
            UserDto find = this.loginService.findById(id);
            if (find == null) {
                return "redirect:/";
            }
            if ( find.getCheckFindValid().isEmpty() ) {
                return "redirect:/";
            }
            if ( !chk.equals(find.getCheckFindValid()) ) {
                return "redirect:/";
            }
            model.addAttribute("find", find);
        } catch (Throwable e) {
            log.error(e.toString());
            return "redirect:/";
        }
        return "/login/changepasswd";
    }
}
