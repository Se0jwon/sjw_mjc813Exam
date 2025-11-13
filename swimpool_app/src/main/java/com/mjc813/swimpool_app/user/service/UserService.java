package com.mjc813.swimpool_app.user.service;

import com.mjc813.swimpool_app.security.service.AES256EncDec;
import com.mjc813.swimpool_app.swimpool.dto.SwimpoolSearchingDto;
import com.mjc813.swimpool_app.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AES256EncDec encDec;

    public List<UserDto> findAll() {
        return this.userMapper.findAll();
    }

    public UserDto findById(Long id) {
        UserDto find = this.userMapper.findById(id);
        find.setPhoneNumber(this.encDec.decrypt_AES(find.getPhoneNumber()));
        find.setEmail(this.encDec.decrypt_AES(find.getEmail()));
        return find;
    }

    public UserDto insert(UserDto dto) {
        dto.setPassword(this.passwordEncoder.encode(dto.getPassword()));
        dto.setPhoneNumber(this.encDec.encrypt_AES(dto.getPhoneNumber()));
        dto.setEmail(this.encDec.encrypt_AES(dto.getEmail()));
        this.userMapper.insert(dto);
        return dto;
    }

    public UserDto update(UserDto dto) {
        dto.setPhoneNumber(this.encDec.encrypt_AES(dto.getPhoneNumber()));
        dto.setEmail(this.encDec.encrypt_AES(dto.getEmail()));
        this.userMapper.update(dto);
        return dto;
    }

    public UserDto updateChkFindValid(UserDto dto) {
        this.userMapper.updateChkFindValid(dto);
        return dto;
    }

    public void delete(Long id) {
        this.userMapper.delete(id);
    }

    public Long countByWhere(String name, String username, Pageable dto) {
        return this.userMapper.countByWhere(name, username, dto);
    }

    public List<UserDto> findByWhere(String name, String username, Pageable dto) {
        return this.userMapper.findByWhere(name, username, dto);
    }

    public UserDto findByUsername(String username) {
        return this.userMapper.findByUsername(username);
    }
}
