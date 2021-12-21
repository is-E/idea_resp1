package com.hxs.dao;

import com.hxs.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    public List<User> findAllUserByPage(Uservo uservo);

    //根据用户名查询用户
    public User login(User user);


    //通过用户id清空中间表
    public void deleteUserContextRole(@Param("uid") Integer uid);

    //获取用户拥有的菜单权限
    public void userContextRole(User_Role_relation user_role_relation);

    //通过用户id获取关联的角色
    public List<Role> findUserRoleById(@Param("id") Integer id);

    //通过角色id获取所有的顶级菜单
    public List<Menu> findParentMenuByRoleId(List<Integer> list);

    //通过pid查询子菜单信息
    public List<Menu> findChildrenMenuByPid(@Param("pid") Integer pid);

    //获取用户拥有的资源权限信息
    public List<Resource> findResourceByRoleId(List<Integer> list);
}
