package com.med.medservice.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.med.medservice.model.Permissions.DELETE;
import static com.med.medservice.model.Permissions.READ;
import static com.med.medservice.model.Permissions.UPDATE;
import static com.med.medservice.model.Permissions.WRITE;

public enum Role {
    USER(Set.of(READ, WRITE)),
    ADMIN(Set.of(READ, WRITE, UPDATE, DELETE));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
