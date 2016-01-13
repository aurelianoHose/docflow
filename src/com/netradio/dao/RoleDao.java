package com.netradio.dao;

import java.util.List;

import com.netradio.entity.Role;

public interface RoleDao extends Dao<Role> {

    List<Role> findAll();

    List<Role> findByUserId(Long id);

    void addUserRole(Long uid, Long rid);

    void deleteUserRoles(Long uid);

}
