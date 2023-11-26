package vn.edu.iuh.fit.lab01.services;

import vn.edu.iuh.fit.lab01.entities.Role;
import vn.edu.iuh.fit.lab01.repositories.RoleRepository;

import java.util.ArrayList;

public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(){
        System.out.println("ok");
        this.roleRepository = new RoleRepository();
    }

    public ArrayList<Role> getAllRoleActive(){
        return roleRepository.getAllRoleActive();
    }

    public boolean add(Role role){
        return roleRepository.add(role);
    }

    public Role get(String roleId){
        return roleRepository.get(roleId);
    }
}
