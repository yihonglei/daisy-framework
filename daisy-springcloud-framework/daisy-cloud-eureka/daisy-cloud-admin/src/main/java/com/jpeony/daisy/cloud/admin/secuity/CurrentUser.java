package com.jpeony.daisy.cloud.admin.secuity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUser extends User {
    public CurrentUser(String username, String password, Long id, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        setId(id);
    }
    public CurrentUser(String username, String password, Long id,String name, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        setId(id);
        setName(name);
    }

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
