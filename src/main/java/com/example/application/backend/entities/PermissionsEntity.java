package com.example.application.backend.entities;

public class PermissionsEntity {
    private int permissionId;
    private String permissionName;
    private int roleId;

    public PermissionsEntity() {

    }

    public PermissionsEntity(int permissionId, String permissionName, int roleId) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
