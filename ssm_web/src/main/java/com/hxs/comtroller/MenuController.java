package com.hxs.comtroller;

import com.github.pagehelper.PageInfo;
import com.hxs.domain.Menu;
import com.hxs.domain.ResponseResult;
import com.hxs.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(Integer currentPage, Integer pageSize) {
        PageInfo<Menu> info = menuService.findAllMenu(currentPage, pageSize);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", info);
        return result;
    }

    //回显菜单信息
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id) {
        if (id == -1) {
            List<Menu> list = menuService.findMenuListByPid(-1);
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", null);
            map.put("parentMenuList", list);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;
        } else {
            List<Menu> list = menuService.findMenuListByPid(-1);
            Menu menu = menuService.findMenuById(id);
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", menu);
            map.put("parentMenuList", list);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;
        }

    }
}
