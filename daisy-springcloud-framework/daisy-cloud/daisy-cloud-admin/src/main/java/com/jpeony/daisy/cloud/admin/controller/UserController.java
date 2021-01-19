package com.jpeony.daisy.cloud.admin.controller;

import com.jpeony.daisy.cloud.admin.domain.UserDO;
import com.jpeony.daisy.cloud.admin.dto.UserDTO;
import com.jpeony.daisy.cloud.admin.service.RoleService;
import com.jpeony.daisy.cloud.admin.service.UserService;
import com.jpeony.daisy.cloud.admin.utils.MD5Utils;
import com.jpeony.daisy.cloud.admin.utils.SecuityUtils;
import com.jpeony.daisy.cloud.common.annotation.Log;
import com.jpeony.daisy.cloud.common.context.FilterContextHandler;
import com.jpeony.daisy.cloud.common.dto.LoginUserDTO;
import com.jpeony.daisy.cloud.common.utils.PageUtils;
import com.jpeony.daisy.cloud.common.utils.Query;
import com.jpeony.daisy.cloud.common.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户信息
 */
@RequestMapping("/user")
@RestController
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    /**
     * 登录的当前用户，前台需要验证用户登录的页面可以调用此方法
     */
    @GetMapping("/currentUser")
    LoginUserDTO currentUser() {
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setUserId(SecuityUtils.getCurrentUser().getId().toString());
        loginUserDTO.setUsername(FilterContextHandler.getUsername());
        loginUserDTO.setName(SecuityUtils.getCurrentUser().getName());
        return loginUserDTO;
    }

    /**
     * 根据用户id获取用户
     */
    @GetMapping("{id}")
    R get(@PathVariable("id") Long id) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userService.get(id), userDTO);
        return R.ok().put("data", userDTO);
    }

    /**
     * 分页查询用户
     */
    @Log("获取用户列表")
    @GetMapping()
    R listByPage(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<UserDO> list = userService.list(query);
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserDO userDO : list) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userDO, userDTO);
            userDTOS.add(userDTO);
        }

        int total = userService.count(query);
        PageUtils pageUtil = new PageUtils(userDTOS, total);
        return R.ok().put("page", pageUtil);
    }

    /**
     * 增加用户
     */
    @PostMapping()
    R save(@RequestBody UserDO user) {
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        return R.operate(userService.save(user) > 0);
    }

    /**
     * 修改用户
     */
    @PutMapping()
    R update(@RequestBody UserDO user) {
        return R.operate(userService.update(user) > 0);
    }


    /**
     * 删除用户
     */
    @DeleteMapping()
    R remove(Long id) {
        return R.operate(userService.remove(id) > 0);
    }

    @PostMapping("/batchRemove")
    @ResponseBody
    R batchRemove(@RequestParam("ids[]") Long[] userIds) {
        int r = userService.batchremove(userIds);
        if (r > 0) {
            return R.ok();
        }
        return R.error();
    }

    @PostMapping("/exits")
    @ResponseBody
    boolean exits(@RequestParam Map<String, Object> params) {
        // 存在，不通过，false
        return !userService.exits(params);
    }

    @GetMapping("/tokenUser")
    public Principal user(Principal user) {
        return user;
    }
}
