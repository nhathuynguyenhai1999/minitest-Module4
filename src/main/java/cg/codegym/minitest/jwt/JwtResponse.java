package cg.codegym.minitest.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String name;
    private String code;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(Collection<? extends GrantedAuthority> authorities, Long id, String token, String type, String name, String code) {
        this.authorities = authorities;
        this.id = id;
        this.token = token;
        this.type = type;
        this.name = name;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
