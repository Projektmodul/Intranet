package com.example.application.backend.entity;

public class UsersRoles {
    private int id;
    private int roleId;
    private int userId;

    public UsersRoles() {

    }

    public UsersRoles(int id, int roleId, int userId) {
        this.id = id;
        this.roleId = roleId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
