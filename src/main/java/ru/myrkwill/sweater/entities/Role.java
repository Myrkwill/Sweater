package ru.myrkwill.sweater.entities;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Mark Nagibin
 */
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
