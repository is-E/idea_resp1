package com.hxs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hxs.dao.UserMapper;
import com.hxs.domain.*;
import com.hxs.service.UserService;
import com.hxs.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findAllUserByPage(Uservo uservo) {
        PageHelper.startPage(uservo.getCurrentPage(), uservo.getPageSize());
        List<User> list = userMapper.findAllUserByPage(uservo);
        PageInfo<User> info = new PageInfo<>(list);
        return info;

    }

    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.login(user);
        if (user1 != null && Md5.verify(user.getPassword(), "hxs", user1.getPassword())) {
            return user1;
        }
        return null;
    }

    @Override
    public List<Role> findUserRoleById(Integer id) {
        return userMapper.findUserRoleById(id);
    }

    @Override
    public void userContextRole(Uservo uservo) {
        userMapper.deleteUserContextRole(uservo.getUserId());
        for (Integer roleId : uservo.getRoleIdList()) {
            User_Role_relation urr = new User_Role_relation();
            urr.setRoleId(roleId);
            urr.setUserId(uservo.getUserId());
            Date date = new Date();
            urr.setCreatedTime(date);
            urr.setUpdatedTime(date);
            urr.setCreatedBy("system");
            urr.setUpdatedBy("system");
            userMapper.userContextRole(urr);
        }
    }

    @Override
    public ResponseResult getUserPermission(Integer userid) {
        //1.获取当前用户拥有的角色
        List<Role> list = userMapper.findUserRoleById(userid);
        //2.获取角色id，单独保存到list集合中
        ArrayList<Integer> rids = new ArrayList<>();
        for (Role role : list) {
            rids.add(role.getId());
        }
        //3.通过角色id查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(rids);

        //4.查询父菜单关联的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> childrenMenu = userMapper.findChildrenMenuByPid(menu.getId());
            menu.setMenuList(childrenMenu);
        }

        //5.获取资源信息
        List<Resource> resources = userMapper.findResourceByRoleId(rids);

        Map<String, Object> map = new HashMap<>();
        map.put("menuList", parentMenu);
        map.put("resourceList", resources);
        return new ResponseResult(true, 200, "响应成功", map);
    }
}
