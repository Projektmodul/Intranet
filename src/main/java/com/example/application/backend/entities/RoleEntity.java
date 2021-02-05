package com.example.application.backend.entities;

import javax.persistence.*;

/**
 * This is a basic role class.
 *
 * @author  Jessica Reistel, Laura Neuendorf and Sabrine Gamdou
 * @version 4.0
 * @since   21-12-2020
 * @lastUpdated 01.02.2021 from Jessica Reistel, Monika Martius
 */

@Entity(name ="roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="role_id")
    private int roleId;

    private String roleName;

    public RoleEntity(){

    }

    public RoleEntity(int roleId, String roleName){
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
