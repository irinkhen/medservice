package com.med.medservice.model;

public enum Permissions {
    READ("read"),
    WRITE("write"),
    DELETE("delete");

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
