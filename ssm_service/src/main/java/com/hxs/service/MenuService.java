package com.hxs.service;

import com.github.pagehelper.PageInfo;
import com.hxs.domain.Menu;
import com.hxs.domain.Menuvo;

import java.util.List;

public interface MenuService {
    public List<Menu> findMenuListByPid(Integer pid);

    public List<Integer> findMenuByRoleId(Integer rid);

    public void roleContextMenu(Menuvo menuvo);

    public void deleteRoleContextMenu(Integer rid);

    public void deleteRole(Integer id);

    public PageInfo<Menu> findAllMenu(Integer currentPage, Integer pageSize);

    public Menu findMenuById(Integer id);
}
