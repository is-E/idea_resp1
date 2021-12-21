package com.hxs.service;

import com.github.pagehelper.PageInfo;
import com.hxs.domain.ResponseResult;
import com.hxs.domain.Role;
import com.hxs.domain.User;
import com.hxs.domain.Uservo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    public PageInfo<User> findAllUserByPage(Uservo uservo);

    public User login(User user) throws Exception;

    public List<Role> findUserRoleById(Integer id);

    public void userContextRole(Uservo uservo);

    //获取用户权限，进行菜单动态暂时
    public ResponseResult getUserPermission(Integer userid);

}
