package com.example.application.backend.entities;


import javax.persistence.*;
import java.util.List;

/**
 * This is a basic permission class.
 *
 * @author  Jessica Reistel, Laura Neuendorf and Sabrine Gamdou
 * @version 3.0
 * @since   21-12-2020
 * @lastUpdated 05.01.2021
 */

@Entity(name ="permissions")
public class PermissionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="permission_id")
    private int permissionId;

   /* @Column(name ="permission_name")
    private String permissionName;

    @ManyToMany(mappedBy = "permissions")
    private List<RolesEntity> roles;*/

   /* public PermissionsEntity() {

    }

    public PermissionsEntity(int permissionId, String permissionName) {
        this.permissionId = permissionId;
        this.permissionName = permissionName;

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

    public List<RolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesEntity> roles) {
        this.roles = roles;
    }*/

}
