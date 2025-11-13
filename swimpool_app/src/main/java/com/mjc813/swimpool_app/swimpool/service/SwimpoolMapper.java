package com.mjc813.swimpool_app.swimpool.service;

import com.mjc813.swimpool_app.swimpool.dto.SwimpoolDto;
import com.mjc813.swimpool_app.swimpool.dto.SwimpoolSearchingDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SwimpoolMapper {
    public List<SwimpoolDto> findAll();
    public SwimpoolDto findById(Integer id);
    public void insert(SwimpoolDto dto);
    public void update(SwimpoolDto dto);
    public void delete(Integer id);

    public Integer countByWhere(SwimpoolSearchingDto dto);
    public List<SwimpoolDto> findByWhere(SwimpoolSearchingDto dto);
}
