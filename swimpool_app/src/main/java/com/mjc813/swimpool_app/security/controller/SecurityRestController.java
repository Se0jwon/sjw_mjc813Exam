package com.mjc813.swimpool_app.security.controller;

import com.mjc813.swimpool_app.common.MyString;
import com.mjc813.swimpool_app.security.service.AES256EncDec;
import com.mjc813.swimpool_app.security.service.LoginService;
import com.mjc813.swimpool_app.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class SecurityRestController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/api/v1/findandchangepassword")
    public ResponseEntity<Boolean> findandchangepassword(@RequestBody UserDto userDto) {
        try {
            if (userDto == null || userDto.getId() <= 0 || userDto.getCheckFindValid().isEmpty() ) {
                return ResponseEntity.badRequest().body(false);
            }
            UserDto find = this.loginService.findById(userDto.getId());
            if (find == null) {
                return ResponseEntity.notFound().build();
            }
            if ( find.getCheckFindValid().isEmpty() ) {
                return ResponseEntity.notFound().build();
            }
            if ( !userDto.getCheckFindValid().equals(find.getCheckFindValid()) ) {
                return ResponseEntity.badRequest().body(false);
            }
            this.loginService.changePassword(userDto.getId(), userDto.getPassword());
            return ResponseEntity.ok().body(true);
        } catch (Throwable e) {
            log.error(e.toString());
            return ResponseEntity.status(500).body(false);
        }
    }
}
