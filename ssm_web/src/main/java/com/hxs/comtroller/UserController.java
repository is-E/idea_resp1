package com.hxs.comtroller;

import com.github.pagehelper.PageInfo;
import com.hxs.domain.ResponseResult;
import com.hxs.domain.Role;
import com.hxs.domain.User;
import com.hxs.domain.Uservo;
import com.hxs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody Uservo uservo) {
        PageInfo<User> info = userService.findAllUserByPage(uservo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", info);
        return result;
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);
        if (user1 != null) {
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token", access_token);
            session.setAttribute("id", user1.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("access_token", access_token);
            map.put("id", user1.getId());
            ResponseResult result = new ResponseResult(true, 200, "登入成功", map);
            return result;
        } else {
            return new ResponseResult(true, 400, "用户名或密码错误", null);
        }
    }

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id) {
        List<Role> list = userService.findUserRoleById(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", list);
        return result;
    }

    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody Uservo uservo) {
        userService.userContextRole(uservo);
        return new ResponseResult(true, 200, "响应成功", null);
    }

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {
        //获取请求头中的token
        String header_token = request.getHeader("Authorization");
        String access_token = (String) request.getSession().getAttribute("access_token");
        if (header_token.equalsIgnoreCase(access_token)) {
            Integer id = (Integer) request.getSession().getAttribute("id");
            ResponseResult result = userService.getUserPermission(id);
            return result;
        }else{
            return new ResponseResult(false,400,"未找到资源",null);
        }
    }
}
