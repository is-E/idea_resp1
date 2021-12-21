package com.hxs.dao;

import com.hxs.domain.Menu;
import com.hxs.domain.Menuvo;
import com.hxs.domain.Role_menu_relation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    //查询所有父子菜单信息
    public List<Menu> findMenuListByPid(@Param("pid") Integer pid);

    //根据角色ID查询关联菜单ID
    public List<Integer> findMenuByRoleId(@Param("rid") Integer rid);

    //为角色分配菜单
    public void roleContextMenu(Role_menu_relation roleMenuRelation);

    //通过角色id清空菜单
    public void deleteRoleContextMenu(@Param("rid") Integer rid);

    //通过角色id删除角色
    public void deleteRole(@Param("id") Integer id);

    //查询所有菜单信息
    public List<Menu> findAllMenu();

    //通过id查询菜单
    public Menu findMenuById(@Param("id") Integer id);

}
