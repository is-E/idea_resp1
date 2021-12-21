package com.hxs.comtroller;

import com.hxs.domain.Menu;
import com.hxs.domain.Menuvo;
import com.hxs.domain.ResponseResult;
import com.hxs.domain.Role;
import com.hxs.service.MenuService;
import com.hxs.service.RoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {
        List<Role> list = roleService.findAllRole(role);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        return result;
    }

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> list = menuService.findMenuListByPid(-1);
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList", list);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;
    }

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(@RequestParam Integer roleId) {
        List<Integer> list = menuService.findMenuByRoleId(roleId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        return result;
    }

    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody Menuvo menuvo) {
        menuService.roleContextMenu(menuvo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
        return result;

    }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id) {
        menuService.deleteRole(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
        return result;
    }
}
