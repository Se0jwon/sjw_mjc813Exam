package com.mjc813.swimpool_app.user.service;

import com.mjc813.swimpool_app.security.dto.LoginDto;
import com.mjc813.swimpool_app.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface UserMapper {
    void insert(UserDto dto);
    void update(UserDto dto);
    void updateChkFindValid(UserDto dto);
    void delete(Long id);
    List<UserDto> findAll();
    UserDto findById(Long id);

    Long countByWhere(String name, String username, Pageable pageable);
    List<UserDto> findByWhere(String name, String username, Pageable pageable);

    UserDto findByUsername(String username);
}
