package com.example.application.backend.services.roles;

import com.example.application.backend.entities.RoleEntity;
import com.example.application.backend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DESCRIPTION
 *
 * @author  Monika Martius and Jessica Reistel
 * @version 1.0
 * @since   01.02.2021
 */

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public RoleEntity findById(int roleId){
        return getRoleRepository().findByRoleId(roleId);
    }

    public RoleRepository getRoleRepository(){
        return roleRepository;
    }
}
