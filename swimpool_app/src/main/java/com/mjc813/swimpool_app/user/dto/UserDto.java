package com.mjc813.swimpool_app.user.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements UserDetails {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String role;
    private Integer maxLock;
    private String checkFindValid;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(this.getRole()));
        return list;
    }
}
