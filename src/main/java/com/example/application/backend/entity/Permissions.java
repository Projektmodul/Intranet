package com.example.application.backend.entity;

public class Permissions {
    private int permission_id;
    private String permission_name;
    private int role_id;

    public Permissions() {

    }

    public Permissions(int permission_id, String permission_name, int role_id) {
        this.permission_id = permission_id;
        this.permission_name = permission_name;
        this.role_id = role_id;
    }

    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}
