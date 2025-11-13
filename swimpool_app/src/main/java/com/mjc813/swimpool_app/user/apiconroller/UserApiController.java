package com.mjc813.swimpool_app.user.apiconroller;

import com.mjc813.swimpool_app.common.ResponseDto;
import com.mjc813.swimpool_app.common.ResponseEnum;
import com.mjc813.swimpool_app.common.ResponseListDto;
import com.mjc813.swimpool_app.user.dto.UserDto;
import com.mjc813.swimpool_app.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {
    @Autowired
    private UserService userService;

    private ResponseDto getResponse(ResponseEnum resEnum, Object data) {
        return ResponseDto.builder()
                .code(resEnum.getCode())
                .responseEnum(resEnum)
                .data(data)
                .build();
    }

    // 데이터 1개를 삽입하는 RestFull API
    @PostMapping("")
    public ResponseEntity<ResponseDto> insert(@Validated @RequestBody UserDto dto) {
        try {
            UserDto result = this.userService.insert(dto);   // DB 에 insert 쿼리 실행 결과를 리턴한다.
            return ResponseEntity.ok().body(
                    this.getResponse(ResponseEnum.Success, result)
//                    new ResponseDto(ResponseEnum.Success, "OK", result)
                    // ResponseEntity 는 서버의 응답을 json 형식으로 문자열로 응답합니다.
            );
        } catch (Throwable e) {
            log.error(e.toString());
            return ResponseEntity.status(500).body(
                    this.getResponse(ResponseEnum.InsertFail, null)
//                    new ResponseDto(ResponseEnum.InsertFail, "Error", e)
                    // ResponseEntity 는 서버의 응답을 json 형식으로 문자열로 응답합니다.
            );
        }
    }

    // 데이터 1개를 U 수정하는 RestFull API
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto> update(@PathVariable("id") Long id
            , @RequestBody UserDto dto) {
        try {
            dto.setId(id);
            UserDto result = this.userService.update(dto);   // DB 에 update 쿼리 실행 결과를 리턴한다.
            return ResponseEntity.ok().body(
                    this.getResponse(ResponseEnum.Success, result)
                    // ResponseEntity 는 서버의 응답을 json 형식으로 문자열로 응답합니다.
            );
        } catch (Throwable e) {
            log.error(e.toString());
            return ResponseEntity.status(500).body(
                    this.getResponse(ResponseEnum.UpdateFail, null)
//                    new ResponseDto(ResponseEnum.UpdateFail, "Error", e)
                    // ResponseEntity 는 서버의 응답을 json 형식으로 문자열로 응답합니다.
            );
        }
    }

    // 데이터 1개를 D 삭제하는 RestFull API
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") Long id) {
        try {
            this.userService.delete(id);   // DB 에 update 쿼리 실행 결과를 리턴한다.
            return ResponseEntity.ok().body(
                    this.getResponse(ResponseEnum.Success, true)
                    // ResponseEntity 는 서버의 응답을 json 형식으로 문자열로 응답합니다.
            );
        } catch (Throwable e) {
            log.error(e.toString());
            return ResponseEntity.status(500).body(
                    this.getResponse(ResponseEnum.DeleteFail, null)
//                    new ResponseDto(ResponseEnum.DeleteFail, "Error", e)
                    // ResponseEntity 는 서버의 응답을 json 형식으로 문자열로 응답합니다.
            );
        }
    }

    // 데이터 목록을 출력하는 RestFull API
    @GetMapping("/list")
    public ResponseEntity<ResponseDto> list() {
        try {
            List<UserDto> list = this.userService.findAll();  // DB 에서 데이터 목록을 검색해서 리턴해야 한다.
            return ResponseEntity.ok().body(
                    this.getResponse(ResponseEnum.Success, list)
//                    new ResponseDto(ResponseEnum.Success.getCode(), "OK", list)
                    // ResponseEntity 는 서버의 응답을 json 형식으로 문자열로 응답합니다.
            );
        } catch (Throwable e) {
            log.error(e.toString());
            return ResponseEntity.status(500).body(
                    this.getResponse(ResponseEnum.SelectFail, null)
//                    new ResponseDto(ResponseEnum.SelectFail, "Error", e)
                    // ResponseEntity 는 서버의 응답을 json 형식으로 문자열로 응답합니다.
            );
        }
    }

    // 데이터 1개를 출력하는 RestFull API
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getOne(@PathVariable Long id) {
        try {
            UserDto one = this.userService.findById(id);   // id 번호로 DB 에서 찾아서 리턴해야 한다.
            return ResponseEntity.ok().body(
                    this.getResponse(ResponseEnum.Success, one)
//                    new ResponseDto(ResponseEnum.Success, "OK", one)
                    // ResponseEntity 는 서버의 응답을 json 형식으로 문자열로 응답합니다.
            );
        } catch (Throwable e) {
            log.error(e.toString());
            return ResponseEntity.status(500).body(
                    this.getResponse(ResponseEnum.SelectFail, null)
//                    new ResponseDto(ResponseEnum.SelectFail, "Error", e)
                    // ResponseEntity 는 서버의 응답을 json 형식으로 문자열로 응답합니다.
            );
        }
    }

    @GetMapping("/find")
    public ResponseEntity<ResponseDto> findByWhere(
            @RequestParam String name
            , @RequestParam String username
            , @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , Model model
    ) {
        try {
//            if ( model.getAttribute("loginUser") == null ) {
//                return ResponseEntity.status(500).body(
//                        this.getResponse(ResponseEnum.AuthenticationFail, null)
//                );
//            }
            Long count = this.userService.countByWhere(name, username, pageable);
            List<UserDto> list = this.userService.findByWhere(name, username, pageable);
            PageImpl<UserDto> page = new PageImpl<>(list, pageable, count);
            ResponseListDto result = ResponseListDto.builder()
                    .data(page)
                    .build();
            return ResponseEntity.ok().body(
                this.getResponse(ResponseEnum.Success, result)
            );
        } catch (Throwable e) {
            log.error(e.toString());
            return ResponseEntity.status(500).body(
                    this.getResponse(ResponseEnum.SelectFail, null)
                    // ResponseEntity 는 서버의 응답을 json 형식으로 문자열로 응답합니다.
            );
        }
    }
}
