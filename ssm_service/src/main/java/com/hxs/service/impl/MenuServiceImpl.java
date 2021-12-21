package com.hxs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxs.dao.MenuMapper;
import com.hxs.domain.Menu;
import com.hxs.domain.Menuvo;
import com.hxs.domain.Role_menu_relation;
import com.hxs.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findMenuListByPid(Integer pid) {
        return menuMapper.findMenuListByPid(pid);
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer rid) {
        return menuMapper.findMenuByRoleId(rid);
    }

    @Override
    public void roleContextMenu(Menuvo menuvo) {
        menuMapper.deleteRoleContextMenu(menuvo.getRoleId());
        for (Integer menuId : menuvo.getMenuIdList()) {
            Role_menu_relation roleMenuRelation = new Role_menu_relation();
            roleMenuRelation.setMenuId(menuId);
            roleMenuRelation.setRoleId(menuvo.getRoleId());
            Date date = new Date();
            roleMenuRelation.setCreatedTime(date);
            roleMenuRelation.setUpdatedTime(date);
            roleMenuRelation.setCreatedBy("system");
            roleMenuRelation.setUpdatedby("system");
            menuMapper.roleContextMenu(roleMenuRelation);
        }


    }

    @Override
    public void deleteRoleContextMenu(Integer rid) {
        menuMapper.deleteRoleContextMenu(rid);
    }

    @Override
    public void deleteRole(Integer id) {
        menuMapper.deleteRoleContextMenu(id);
        menuMapper.deleteRole(id);
    }

    @Override
    public PageInfo<Menu> findAllMenu(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Menu> list = menuMapper.findAllMenu();
        PageInfo<Menu> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public Menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);
    }
}
