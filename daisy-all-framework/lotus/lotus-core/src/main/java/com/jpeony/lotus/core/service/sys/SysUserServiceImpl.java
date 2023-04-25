package com.jpeony.lotus.core.service.sys;

import com.jpeony.lotus.common.enums.ErrorCodeEnum;
import com.jpeony.lotus.common.exception.BizException;
import com.jpeony.lotus.common.utils.DateUtils;
import com.jpeony.lotus.core.mapper.SysUserMapper;
import com.jpeony.lotus.core.pojo.bo.HeaderBO;
import com.jpeony.lotus.core.pojo.bo.SysUserBO;
import com.jpeony.lotus.core.pojo.domain.SysRole;
import com.jpeony.lotus.core.pojo.domain.SysUser;
import com.jpeony.lotus.core.pojo.domain.SysUserRole;
import com.jpeony.lotus.core.pojo.dto.SysLoginDto;
import com.jpeony.lotus.core.pojo.dto.SysModifyPWDDto;
import com.jpeony.lotus.core.pojo.dto.SysUpdateUserStatusDto;
import com.jpeony.lotus.core.pojo.dto.SysUserDto;
import com.jpeony.lotus.core.service.SysRoleService;
import com.jpeony.lotus.core.service.SysUserRoleService;
import com.jpeony.lotus.core.service.SysUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    SysRoleService sysRoleService;

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    private SysUser getUserByUserName(String username) {
        return sysUserMapper.getUserByUserName(username);
    }

    @Override
    public SysUser getUserById(long uid) {
        return sysUserMapper.getUserById(uid);
    }

    @Override
    public SysUserBO doLogin(SysLoginDto sysLoginDto) throws Exception {
        String username = sysLoginDto.getUsername();
        String password = sysLoginDto.getPassword();

        // 校验用户是否存在
        SysUser sysUser = getUserByUserName(username);
        if (sysUser == null) {
            throw new BizException(ErrorCodeEnum.SYS_LOGIN_NO_USER.getCode(), ErrorCodeEnum.SYS_LOGIN_NO_USER.getMsg());
        }

        // 校验密码是否正确:注意校验规则 #TODO 后续需要完善
        String dPassword = sysUser.getPassword();
//        String passwordmd5 = CommonUtil.MD5_32(password+CommonUtil.MD5_32(sysUser.getSalt()));
        if (!dPassword.equals(password)) {
            throw new BizException(ErrorCodeEnum.SYS_LOGIN_PWD_ERROR.getCode(), ErrorCodeEnum.SYS_LOGIN_PWD_ERROR.getMsg());
        }

        // #TODO 登录成功需要生成token，存入到 redis，这里为了方便，不增加环境复杂度
        // 登录获取用户信息&权限信息
        SysUserBO sysUserBO = new SysUserBO();
        BeanUtils.copyProperties(sysUser, sysUserBO);

        return sysUserBO;
    }

    @Override
    public SysUserBO userinfo(String token) throws Exception {
        // 获取用户信息 TODO
        SysUser sysUser = sysUserMapper.getUserByToken(token);
        if (sysUser == null) {
            throw new BizException(ErrorCodeEnum.SYS_LOGIN_EXPIRE.getCode(), ErrorCodeEnum.SYS_LOGIN_EXPIRE.getMsg());
        }

        // 登录获取用户信息&权限信息
        SysUserBO sysUserBO = new SysUserBO();
        BeanUtils.copyProperties(sysUser, sysUserBO);

        long userId = sysUser.getId();
        List<SysUserRole> sysUserRoleList = sysUserRoleService.userRolesByUserId(userId);

        List<Object> roles = new ArrayList<>();
        for (SysUserRole sysUserRole : sysUserRoleList) {
            long roleId = sysUserRole.getRoleId();
            SysRole sysRole = sysRoleService.roleWithId(roleId);
            roles.add(sysRole.getName());
        }
        sysUserBO.setRoles(roles);

        return sysUserBO;
    }

    @Override
    public Boolean logout(HeaderBO headerBO) {
        return true;
    }

    /**
     * 用户管理-用户列表
     *
     * @param headerBO
     * @return
     */
    @Override
    public List<SysUserBO> getSysUsersList(HeaderBO headerBO) {
        List<SysUserBO> result = new ArrayList<>();
        List<SysUser> tmpList = sysUserMapper.usersList();
        for (SysUser sysUser : tmpList) {
            SysUserBO sysUserBO = new SysUserBO();
            BeanUtils.copyProperties(sysUser, sysUserBO);
            sysUserBO.setCreatedTime(DateUtils.formatString(sysUser.getCreatedAt(), DateUtils.TIME_DATE));
            ;
            sysUserBO.setUpdatedTime(DateUtils.formatString(sysUser.getUpdatedAt(), DateUtils.TIME_DATE));
            long created_by = sysUser.getCreatedBy();
            long updated_by = sysUser.getCreatedBy();

            SysUser sysUser1 = sysUserMapper.getUserById(created_by);
            SysUser sysUser2 = sysUserMapper.getUserById(updated_by);

            sysUserBO.setCreatedByUsername(sysUser1.getUsername());
            sysUserBO.setUpdatedByUsername(sysUser2.getUsername());

            long uid = sysUser.getId();
            List<SysUserRole> sysUserRoleList = sysUserRoleService.userRolesByUserId(uid);
            List<Object> roles = new ArrayList<>();
            for (SysUserRole sysUserRole : sysUserRoleList) {
                long role_id = sysUserRole.getRoleId();
                SysRole sysRole = sysRoleService.roleWithId(role_id);
                roles.add(sysRole);
            }
            sysUserBO.setRoles(roles);

            result.add(sysUserBO);
        }

        return result;
    }

    public SysUser autoAddSysUser(SysUser sysUser) {
        sysUserMapper.addSysUser(sysUser);

        return sysUserMapper.getUserByUserName(sysUser.getUsername());
    }

    @Override
    public SysUserBO addSysUser(HeaderBO headerBO, SysUserDto sysUserDto) throws Exception {
        SysUser sysUser = new SysUser();
        String token = headerBO.getToken();
        SysUser sysUser1 = sysUserMapper.getUserByToken(token);
        long created_by = sysUser1.getId();
        long updated_by = created_by;
        Date now = new Date();

        sysUser.setCreatedAt(now);
        sysUser.setCreatedBy(created_by);
        sysUser.setUpdatedAt(now);
        sysUser.setUpdatedBy(updated_by);

        String username = sysUserDto.getUsername();
        String name = sysUserDto.getName();
        String avatar = sysUserDto.getAvatar();
        String phone = sysUserDto.getPhone();
        sysUser.setUsername(username);
        sysUser.setName(name);
        sysUser.setAvatar(avatar);
        sysUser.setPhone(phone);

        // 校验此用户名是否存在
        SysUser sysUser2 = sysUserMapper.getUserByUserName(username);
        if (sysUser2 != null) {
            throw new BizException(ErrorCodeEnum.SYS_ADD_USER_EXIST.getCode(), ErrorCodeEnum.SYS_ADD_USER_EXIST.getMsg());
        }

        String attCode = RandomStringUtils.randomAlphanumeric(8);
        sysUser.setAttcode(attCode);
        sysUser.setStatus(1);
        sysUser.setPassword(username + "123");
        sysUser.setSalt("123456");
        sysUser.setToken(username + "-token");
        sysUserMapper.addSysUser(sysUser);

        // 获取用户信息 - result
        SysUser sysUser3 = sysUserMapper.getUserByUserName(username);
        String roleIds = sysUserDto.getRoleIds();
        String[] roles = roleIds.split(",");
        for (String roleIdStr : roles) {
            long roleId = Long.valueOf(roleIdStr);
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(sysUser3.getId());
            sysUserRole.setCreatedAt(now);
            sysUserRole.setUpdatedAt(now);
            sysUserRole.setCreatedBy(created_by);
            sysUserRole.setUpdatedBy(updated_by);
            sysUserRoleService.addSysUserRole(sysUserRole);
        }

        // 登录获取用户信息&权限信息
        SysUserBO sysUserBO = new SysUserBO();
        BeanUtils.copyProperties(sysUser3, sysUserBO);
        List<SysUserRole> sysUserRoleList = sysUserRoleService.userRolesByUserId(sysUser3.getId());
        List<Object> sysUserBORoles = new ArrayList<>();
        for (SysUserRole sysUserRole : sysUserRoleList) {
            long role_id = sysUserRole.getRoleId();
            SysRole sysRole = sysRoleService.roleWithId(role_id);
            sysUserBORoles.add(sysRole);
        }
        sysUserBO.setRoles(sysUserBORoles);

        return sysUserBO;
    }

    @Override
    public SysUserBO updateSysUser(HeaderBO headerBO, SysUserDto sysUserDto) throws Exception {
        SysUser sysUser = new SysUser();
        String token = headerBO.getToken();
        SysUser sysUser1 = sysUserMapper.getUserByToken(token);
        long created_by = sysUser1.getId();
        long updated_by = created_by;
        Date now = new Date();

        sysUser.setUpdatedAt(now);
        sysUser.setUpdatedBy(updated_by);

        long userId = sysUserDto.getId();
        String username = sysUserDto.getUsername();
        String name = sysUserDto.getName();
        String avatar = sysUserDto.getAvatar();
        String phone = sysUserDto.getPhone();
        sysUser.setId(userId);
        sysUser.setUsername(username);
        sysUser.setName(name);
        sysUser.setAvatar(avatar);
        sysUser.setPhone(phone);

        // 校验此用户名是否存在
        SysUser sysUser2 = sysUserMapper.getUserById(userId);
        if (sysUser2 == null) {
            throw new BizException(ErrorCodeEnum.SYS_EDIT_USER_NOT_EXIST.getCode(), ErrorCodeEnum.SYS_EDIT_USER_NOT_EXIST.getMsg());
        }
        // 用户名、真实姓名、头像、手机号、阿里号 - 附角色
        sysUserMapper.updateSysUser(sysUser);

        // 删除原有用户角色关系
        sysUserRoleService.delUserRolesByUserId(userId);

        String roleIds = sysUserDto.getRoleIds();
        String[] roles = roleIds.split(",");
        for (String roleIdStr : roles) {
            long roleId = Long.valueOf(roleIdStr);
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(userId);
            sysUserRole.setCreatedAt(now);
            sysUserRole.setUpdatedAt(now);
            sysUserRole.setCreatedBy(created_by);
            sysUserRole.setUpdatedBy(updated_by);
            sysUserRoleService.addSysUserRole(sysUserRole);
        }

        // 获取用户信息 - result
        SysUser sysUser3 = sysUserMapper.getUserById(userId);
        // 登录获取用户信息&权限信息
        SysUserBO sysUserBO = new SysUserBO();
        BeanUtils.copyProperties(sysUser3, sysUserBO);
        List<SysUserRole> sysUserRoleList = sysUserRoleService.userRolesByUserId(userId);
        List<Object> sysUserBORoles = new ArrayList<>();
        for (SysUserRole sysUserRole : sysUserRoleList) {
            long role_id = sysUserRole.getRoleId();
            SysRole sysRole = sysRoleService.roleWithId(role_id);
            sysUserBORoles.add(sysRole);
        }
        sysUserBO.setRoles(sysUserBORoles);

        return sysUserBO;
    }

    @Override
    public boolean delSysUser(long uid) {
        return sysUserMapper.realDelByUserId(uid) == 1;
    }

    @Override
    public boolean resetSysPWD(HeaderBO headerBO, long uid) {
        String token = headerBO.getToken();
        SysUser sysUser = sysUserMapper.getUserByToken(token);
        Date now = new Date();

        String password = "123456";
        Boolean flag = sysUserMapper.resetSysPWD(uid, password, sysUser.getId(), now) == 1;
        return flag;
    }

    @Override
    public boolean modifySysPWD(HeaderBO headerBO, SysModifyPWDDto sysModifyPWDDto) throws Exception {
        String token = headerBO.getToken();
        String originPWD = sysModifyPWDDto.getOriginPWD();
        String pwd = sysModifyPWDDto.getPwd();
        String surePWD = sysModifyPWDDto.getSurePWD();

        // 校验原密码
        SysUser sysUser = sysUserMapper.getUserByToken(token);
        String dbPWD = sysUser.getPassword();
        if (!originPWD.equals(dbPWD)) {
            throw new BizException(ErrorCodeEnum.SYS_MODIFY_PWD_NOT_RIGHT.getCode(), ErrorCodeEnum.SYS_MODIFY_PWD_NOT_RIGHT.getMsg());
        }

        if (!pwd.equals(surePWD)) {
            throw new BizException(ErrorCodeEnum.SYS_MODIFY_PWD_SURE_NOT_RIGHT.getCode(), ErrorCodeEnum.SYS_MODIFY_PWD_SURE_NOT_RIGHT.getMsg());
        }

        return sysUserMapper.resetSysPWD(sysUser.getId(), pwd, sysUser.getId(), new Date()) == 1;
    }

    @Override
    public Boolean sysUpdateUserStatus(String token, SysUpdateUserStatusDto sysUpdateUserStatusDto) {
        Date now = new Date();
        SysUser sysUser = sysUserMapper.getUserByToken(token);

        int status = sysUpdateUserStatusDto.getStatus();
        long id = sysUpdateUserStatusDto.getId();
        return sysUserMapper.updateSysEnable(id, status, sysUser.getId(), now) == 1;
    }

}
