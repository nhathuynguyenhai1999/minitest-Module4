package cg.codegym.minitest.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ComputerPrinciple implements UserDetails {

    private static final long serialVersionUID = 1L;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public ComputerPrinciple(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static ComputerPrinciple build(Computer computer) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (computer.getType() != null) {
            authorities.add(new SimpleGrantedAuthority(computer.getType().getName()));
        }

        return new ComputerPrinciple(
                computer.getName(),
                computer.getCode(),
                authorities
        );
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
