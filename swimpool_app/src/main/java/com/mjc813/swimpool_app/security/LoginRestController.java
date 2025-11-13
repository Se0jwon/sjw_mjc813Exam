package com.mjc813.swimpool_app.security;

import com.mjc813.swimpool_app.common.ResponseDto;
import com.mjc813.swimpool_app.common.ResponseEnum;
import com.mjc813.swimpool_app.security.dto.LoginDto;
import com.mjc813.swimpool_app.security.service.LoginService;
import com.mjc813.swimpool_app.user.dto.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("")
public class LoginRestController {
    @Autowired
    private LoginService loginService;

    private ResponseDto getResponse(ResponseEnum resEnum, Object data) {
        return ResponseDto.builder()
                .code(resEnum.getCode())
                .responseEnum(resEnum)
                .data(data)
                .build();
    }

    @PostMapping("/api/v1/cookie_rest/login")
    public ResponseEntity<ResponseDto> cookieLogin(
            @RequestBody LoginDto loginDto
            , HttpServletResponse response
    ) {
        try {
            ResponseDto responseDto = this.loginService.login(loginDto);
            if ( responseDto.getResponseEnum() != ResponseEnum.Success ) {
                return ResponseEntity.status(401).body(responseDto);
            }
            Cookie ck = new Cookie("mjc813", ((UserDto)responseDto.getData()).getId().toString());
            ck.setHttpOnly(true);
            ck.setSecure(true);
            ck.setMaxAge( 60 * 3 * 1 * 1 );
            ck.setPath("/");
            response.addCookie(ck);
            return ResponseEntity.ok().body(responseDto);
        } catch (Throwable e) {
            log.error(e.toString());
            return ResponseEntity.status(500).body(
                    this.getResponse(ResponseEnum.AuthenticationFail, null)
            );
        }
    }

    @PostMapping("/api/v1/session_rest/login")
    public ResponseEntity<ResponseDto> sessionLogin(@RequestBody LoginDto loginDto
            , HttpSession session
    ) {
        try {
            ResponseDto responseDto = this.loginService.login(loginDto);
            if ( responseDto.getResponseEnum() != ResponseEnum.Success ) {
                return ResponseEntity.status(401).body(responseDto);
            }
            session.setAttribute("mjc813", ((UserDto)responseDto.getData()).getId());
            return ResponseEntity.ok().body(responseDto);
        } catch (Throwable e) {
            log.error(e.toString());
            return ResponseEntity.status(500).body(
                    this.getResponse(ResponseEnum.AuthenticationFail, null)
            );
        }
    }

    @PostMapping("/api/v1/spring_rest/login")
    public ResponseEntity<ResponseDto> springLogin(@RequestBody LoginDto loginDto
            , HttpSession session
    ) {
        try {
            ResponseDto responseDto = this.loginService.login(loginDto);
            if ( responseDto.getResponseEnum() != ResponseEnum.Success ) {
                return ResponseEntity.status(401).body(responseDto);
            }
            session.setAttribute("mjc813", ((UserDto)responseDto.getData()).getId());
            return ResponseEntity.ok().body(responseDto);
        } catch (Throwable e) {
            log.error(e.toString());
            return ResponseEntity.status(500).body(
                    this.getResponse(ResponseEnum.AuthenticationFail, null)
            );
        }
    }

    @PostMapping("/api/v1/rest/logout")
    public ResponseEntity<ResponseDto> logout(HttpServletResponse response
        , HttpSession session
    ) {
        try {
            Cookie ck = new Cookie("mjc813", "");
            ck.setHttpOnly(true);
            ck.setSecure(true);
            ck.setMaxAge( 0 );
            ck.setPath("/");
            response.addCookie(ck);
            session.invalidate();
            return ResponseEntity.ok().body(
                    ResponseDto.builder().responseEnum(ResponseEnum.Success).code(ResponseEnum.Success.getCode()).data(true).build()
            );
        } catch (Throwable e) {
            log.error(e.toString());
            return ResponseEntity.status(500).body(
                    this.getResponse(ResponseEnum.AuthenticationFail, null)
            );
        }
    }

    @PostMapping("/api/v1/changepassword")
    public ResponseEntity<ResponseDto> changepassword(
            @RequestBody UserDto dto
    ) {
        try {
            this.loginService.changePassword(dto.getId(), dto.getPassword());
            return ResponseEntity.ok().body(
                    ResponseDto.builder().responseEnum(ResponseEnum.Success).code(ResponseEnum.Success.getCode()).data(true).build()
            );
        } catch (Throwable e) {
            log.error(e.toString());
            return ResponseEntity.status(500).body(
                    this.getResponse(ResponseEnum.AuthenticationFail, null)
            );
        }
    }
}
