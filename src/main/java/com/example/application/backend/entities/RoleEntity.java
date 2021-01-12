package com.example.application.backend.entities;

import javax.persistence.*;
import java.util.List;

/**
 * This is a basic role class.
 *
 * @author  Jessica Reistel, Laura Neuendorf and Sabrine Gamdou
 * @version 3.0
 * @since   21-12-2020
 * @lastUpdated 05.01.2021
 */

@Entity(name ="roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="role_id")
    private int roleId;

    private String roleName;

   /* @ManyToMany
    @JoinTable( //this defines the relationship and the foreign key columns
            name = "permissions_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<PermissionsEntity> permissions;


    @ManyToMany(mappedBy = "roles")
    private List<UsersEntity> users;
*/
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

    /*public List<PermissionsEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionsEntity> permissions) {
        this.permissions = permissions;
    }

    public List<UsersEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UsersEntity> users) {
        this.users = users;
    }*/
}
