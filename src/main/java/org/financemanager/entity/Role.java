package org.financemanager.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.CATEGORIES_READ, Permission.TRANSACTIONS_READ, Permission.TRANSACTIONS_WRITE, Permission.REPORTS_READ)),
    ADMIN(Set.of(Permission.CATEGORIES_READ, Permission.CATEGORIES_WRITE, Permission.TRANSACTIONS_READ, Permission.TRANSACTIONS_WRITE, Permission.REPORTS_READ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
