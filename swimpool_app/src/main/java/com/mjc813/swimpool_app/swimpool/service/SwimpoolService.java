package com.mjc813.swimpool_app.swimpool.service;

import com.mjc813.swimpool_app.swimpool.dto.SwimpoolDto;
import com.mjc813.swimpool_app.swimpool.dto.SwimpoolSearchingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwimpoolService {
    @Autowired
    private SwimpoolMapper swimpoolMapper;

    public List<SwimpoolDto> findAll() {
        return this.swimpoolMapper.findAll();
    }

    public SwimpoolDto findById(Integer id) {
        return this.swimpoolMapper.findById(id);
    }

    public SwimpoolDto insert(SwimpoolDto dto) {
        this.swimpoolMapper.insert(dto);
        return dto;
    }

    public SwimpoolDto update(SwimpoolDto dto) {
        this.swimpoolMapper.update(dto);
        return dto;
    }

    public void delete(Integer id) {
        this.swimpoolMapper.delete(id);
    }


    public Integer countByWhere(SwimpoolSearchingDto dto) {
        return this.swimpoolMapper.countByWhere(dto);
    }

    public List<SwimpoolDto> findByWhere(SwimpoolSearchingDto dto) {
        return this.swimpoolMapper.findByWhere(dto);
    }
}
