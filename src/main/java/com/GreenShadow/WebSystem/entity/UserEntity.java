package com.GreenShadow.WebSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity(name = "user")
public class UserEntity implements SuperEntity, UserDetails {

    @Id
    private String userId;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        /*return ""*/return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        /*return UserDetails.super.isAccountNonExpired();*/
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; /*return UserDetails.super.isAccountNonLocked();*/
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        /*return UserDetails.super.isCredentialsNonExpired();*/
    }

    @Override
    public boolean isEnabled() {
        return true;
        /*return UserDetails.super.isEnabled();*/
    }

//    @OneToOne
//    @JoinColumn(name = "personalId", referencedColumnName = "id")
//    private StaffEntity staff;



}
