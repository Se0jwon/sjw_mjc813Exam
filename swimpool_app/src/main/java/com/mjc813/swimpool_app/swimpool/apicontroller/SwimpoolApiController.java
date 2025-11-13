package com.mjc813.swimpool_app.swimpool.apicontroller;

import com.mjc813.swimpool_app.common.ResponseDto;
import com.mjc813.swimpool_app.common.ResponseEnum;
import com.mjc813.swimpool_app.common.ResponseListDto;
import com.mjc813.swimpool_app.swimpool.dto.SwimpoolDto;
import com.mjc813.swimpool_app.swimpool.dto.SwimpoolSearchingDto;
import com.mjc813.swimpool_app.swimpool.service.SwimpoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/swimpool")
public class SwimpoolApiController {
    @Autowired
    private SwimpoolService swimpoolService;

    private ResponseDto getResponse(ResponseEnum resEnum, Object data) {
        return ResponseDto.builder()
                .code(resEnum.getCode())
                .responseEnum(resEnum)
                .data(data)
                .build();
    }

    // 데이터 목록을 출력하는 RestFull API
    @GetMapping("/list")
    public ResponseEntity<ResponseDto> list() {
        try {
            List<SwimpoolDto> list = this.swimpoolService.findAll();  // DB 에서 데이터 목록을 검색해서 리턴해야 한다.
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
    public ResponseEntity<ResponseDto> getOne(@PathVariable Integer id) {
        try {
            SwimpoolDto one = this.swimpoolService.findById(id);   // id 번호로 DB 에서 찾아서 리턴해야 한다.
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

    // 데이터 1개를 삽입하는 RestFull API
    @PostMapping("")
    public ResponseEntity<ResponseDto> insert(@Validated @RequestBody SwimpoolDto dto) {
        try {
            SwimpoolDto result = this.swimpoolService.insert(dto);   // DB 에 insert 쿼리 실행 결과를 리턴한다.
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
    public ResponseEntity<ResponseDto> update(@PathVariable("id") Integer id
            , @RequestBody SwimpoolDto dto) {
        try {
            dto.setId(id);
            SwimpoolDto result = this.swimpoolService.update(dto);   // DB 에 update 쿼리 실행 결과를 리턴한다.
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
    public ResponseEntity<ResponseDto> delete(@PathVariable("id") Integer id) {
        try {
            this.swimpoolService.delete(id);   // DB 에 update 쿼리 실행 결과를 리턴한다.
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

    @PostMapping("/find")
    public ResponseEntity<ResponseDto> findByWhere(@RequestBody SwimpoolSearchingDto dto) {
        try {
            Integer count = this.swimpoolService.countByWhere(dto);
            dto.getResultList().setTotalRow(count);
            List<SwimpoolDto> list = this.swimpoolService.findByWhere(dto);
            ResponseListDto result = ResponseListDto.builder()
                    .curPage(dto.getResultList().getCurPage())
                    .rowsPage(dto.getResultList().getRowsPage())
                    .totalRow(dto.getResultList().getTotalRow())
                    .data(list)
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
