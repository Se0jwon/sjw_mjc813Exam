package com.mjc813.band_jpa.band;

import com.mjc813.band_jpa.band.service.BandRepository;
import com.mjc813.band_jpa.band.vo.BandEntity;
import com.mjc813.band_jpa.common.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/band")
public class BandRestController {
    @Autowired
    private BandRepository bandRepository;

    @PostMapping("")
    public ResponseEntity<ResponseDto> insert(@Validated @RequestBody BandEntity obj) {
        try {
            this.bandRepository.save(obj);
            return ResponseEntity.status(200).body(
                    ResponseDto.builder().code(10000).message("OK").resData(obj).build()
            );
        } catch (Throwable e) {
            log.error(e.toString());
            return ResponseEntity.status(500).body(
                    ResponseDto.builder().code(99999).message("ERROR").resData(obj).build()
            );
        }
    }
}
