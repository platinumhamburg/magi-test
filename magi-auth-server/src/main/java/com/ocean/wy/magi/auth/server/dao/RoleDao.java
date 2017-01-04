package com.ocean.wy.magi.auth.server.dao;

import java.util.List;

import com.ocean.wy.magi.auth.server.entity.Role;

/**
 * <p>User: Ocean.wy
 * <p>Date: 16-1-28
 * <p>Version: 1.0
 */
public interface RoleDao {

    public Role createRole(Role role);
    public Role updateRole(Role role);
    public void deleteRole(Long roleId);

    public Role findOne(Long roleId);
    public List<Role> findAll();
}
