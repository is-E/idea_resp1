package com.hxs.dao;

import com.hxs.domain.Role;

import java.util.List;

public interface RoleMapper {
    //查询所有角色&条件查询
    public List<Role> findAllRole(Role role);

}
