package org.financemanager.entity;

public enum Permission {
    CATEGORIES_READ("categories:read"),
    CATEGORIES_WRITE("categories:write"),
    TRANSACTIONS_READ("transactions:read"),
    TRANSACTIONS_WRITE("transactions:write"),
    REPORTS_READ("reports:read");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
