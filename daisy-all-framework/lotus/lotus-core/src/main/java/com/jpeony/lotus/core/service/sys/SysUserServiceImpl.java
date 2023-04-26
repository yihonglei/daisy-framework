package com.jpeony.lotus.core.service.sys;

import com.jpeony.lotus.common.enums.ErrorCodeEnum;
import com.jpeony.lotus.common.exception.BizException;
import com.jpeony.lotus.common.utils.DateUtils;
import com.jpeony.lotus.core.mapper.SysUserMapper;
import com.jpeony.lotus.core.pojo.bo.HeaderBO;
import com.jpeony.lotus.core.pojo.vo.SysUserVO;
import com.jpeony.lotus.core.pojo.domain.SysRoleDO;
import com.jpeony.lotus.core.pojo.domain.SysUserDO;
import com.jpeony.lotus.core.pojo.domain.SysUserRoleDO;
import com.jpeony.lotus.core.pojo.dto.SysLoginDTO;
import com.jpeony.lotus.core.pojo.dto.SysModifyPWDDTO;
import com.jpeony.lotus.core.pojo.dto.SysUpdateUserStatusDTO;
import com.jpeony.lotus.core.pojo.dto.SysUserDTO;
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
     */
    private SysUserDO getUserByUserName(String username) {
        return sysUserMapper.getUserByUserName(username);
    }

    @Override
    public SysUserDO getUserById(long userId) {
        return sysUserMapper.getUserById(userId);
    }

    @Override
    public SysUserVO doLogin(SysLoginDTO sysLoginDTO) throws Exception {
        String username = sysLoginDTO.getUsername();
        String password = sysLoginDTO.getPassword();

        // 校验用户是否存在
        SysUserDO sysUserDO = getUserByUserName(username);
        if (sysUserDO == null) {
            throw new BizException(ErrorCodeEnum.SYS_LOGIN_NO_USER.getCode(), ErrorCodeEnum.SYS_LOGIN_NO_USER.getMsg());
        }

        // 校验密码是否正确:注意校验规则 #TODO 后续需要完善
        String dPassword = sysUserDO.getPassword();
//        String passwordmd5 = CommonUtil.MD5_32(password+CommonUtil.MD5_32(sysUser.getSalt()));
        if (!dPassword.equals(password)) {
            throw new BizException(ErrorCodeEnum.SYS_LOGIN_PWD_ERROR.getCode(), ErrorCodeEnum.SYS_LOGIN_PWD_ERROR.getMsg());
        }

        // #TODO 登录成功需要生成token，存入到 redis，这里为了方便，不增加环境复杂度
        // 登录获取用户信息&权限信息
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(sysUserDO, sysUserVO);

        return sysUserVO;
    }

    @Override
    public SysUserVO userInfo(String token) throws Exception {
        // 获取用户信息 TODO
        SysUserDO sysUserDO = sysUserMapper.getUserByToken(token);
        if (sysUserDO == null) {
            throw new BizException(ErrorCodeEnum.SYS_LOGIN_EXPIRE.getCode(), ErrorCodeEnum.SYS_LOGIN_EXPIRE.getMsg());
        }

        // 登录获取用户信息&权限信息
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(sysUserDO, sysUserVO);

        long userId = sysUserDO.getId();
        List<SysUserRoleDO> sysUserRoleDOList = sysUserRoleService.userRolesByUserId(userId);

        List<Object> roles = new ArrayList<>();
        for (SysUserRoleDO sysUserRoleDO : sysUserRoleDOList) {
            long roleId = sysUserRoleDO.getRoleId();
            SysRoleDO sysRoleDO = sysRoleService.roleById(roleId);
            roles.add(sysRoleDO.getName());
        }
        sysUserVO.setRoles(roles);

        return sysUserVO;
    }

    @Override
    public Boolean logout(HeaderBO headerBO) {
        return true;
    }

    /**
     * 用户管理-用户列表
     */
    @Override
    public List<SysUserVO> getSysUsersList(HeaderBO headerBO) {
        List<SysUserVO> result = new ArrayList<>();
        List<SysUserDO> tmpList = sysUserMapper.usersList();
        for (SysUserDO sysUserDO : tmpList) {
            SysUserVO sysUserVO = new SysUserVO();
            BeanUtils.copyProperties(sysUserDO, sysUserVO);
            sysUserVO.setCreatedTime(DateUtils.formatString(sysUserDO.getCreatedAt(), DateUtils.TIME_DATE));
            sysUserVO.setUpdatedTime(DateUtils.formatString(sysUserDO.getUpdatedAt(), DateUtils.TIME_DATE));
            long createdBy = sysUserDO.getCreatedBy();
            long updatedBy = sysUserDO.getCreatedBy();

            SysUserDO sysUserDO1 = sysUserMapper.getUserById(createdBy);
            SysUserDO sysUserDO2 = sysUserMapper.getUserById(updatedBy);

            sysUserVO.setCreatedByUsername(sysUserDO1.getUsername());
            sysUserVO.setUpdatedByUsername(sysUserDO2.getUsername());

            long uid = sysUserDO.getId();
            List<SysUserRoleDO> sysUserRoleDOList = sysUserRoleService.userRolesByUserId(uid);
            List<Object> roles = new ArrayList<>();
            for (SysUserRoleDO sysUserRoleDO : sysUserRoleDOList) {
                long role_id = sysUserRoleDO.getRoleId();
                SysRoleDO sysRoleDO = sysRoleService.roleById(role_id);
                roles.add(sysRoleDO);
            }
            sysUserVO.setRoles(roles);

            result.add(sysUserVO);
        }

        return result;
    }

    public SysUserDO autoAddSysUser(SysUserDO sysUserDO) {
        sysUserMapper.addSysUser(sysUserDO);

        return sysUserMapper.getUserByUserName(sysUserDO.getUsername());
    }

    @Override
    public SysUserVO addSysUser(HeaderBO headerBO, SysUserDTO sysUserDTO) throws Exception {
        SysUserDO sysUserDO = new SysUserDO();
        String token = headerBO.getToken();
        SysUserDO sysUserDO1 = sysUserMapper.getUserByToken(token);
        long createdBy = sysUserDO1.getId();
        long updatedBy = createdBy;
        Date now = new Date();

        sysUserDO.setCreatedAt(now);
        sysUserDO.setCreatedBy(createdBy);
        sysUserDO.setUpdatedAt(now);
        sysUserDO.setUpdatedBy(updatedBy);

        String username = sysUserDTO.getUsername();
        String name = sysUserDTO.getName();
        String avatar = sysUserDTO.getAvatar();
        String phone = sysUserDTO.getPhone();
        sysUserDO.setUsername(username);
        sysUserDO.setName(name);
        sysUserDO.setAvatar(avatar);
        sysUserDO.setPhone(phone);

        // 校验此用户名是否存在
        SysUserDO sysUserDO2 = sysUserMapper.getUserByUserName(username);
        if (sysUserDO2 != null) {
            throw new BizException(ErrorCodeEnum.SYS_ADD_USER_EXIST.getCode(), ErrorCodeEnum.SYS_ADD_USER_EXIST.getMsg());
        }

        String attCode = RandomStringUtils.randomAlphanumeric(8);
        sysUserDO.setAttcode(attCode);
        sysUserDO.setStatus(1);
        sysUserDO.setPassword(username + "123");
        sysUserDO.setSalt("123456");
        sysUserDO.setToken(username + "-token");
        sysUserMapper.addSysUser(sysUserDO);

        // 获取用户信息 - result
        SysUserDO sysUserDO3 = sysUserMapper.getUserByUserName(username);
        String roleIds = sysUserDTO.getRoleIds();
        String[] roles = roleIds.split(",");
        for (String roleIdStr : roles) {
            long roleId = Long.valueOf(roleIdStr);
            SysUserRoleDO sysUserRoleDO = new SysUserRoleDO();
            sysUserRoleDO.setRoleId(roleId);
            sysUserRoleDO.setUserId(sysUserDO3.getId());
            sysUserRoleDO.setCreatedAt(now);
            sysUserRoleDO.setUpdatedAt(now);
            sysUserRoleDO.setCreatedBy(createdBy);
            sysUserRoleDO.setUpdatedBy(updatedBy);
            sysUserRoleService.addSysUserRole(sysUserRoleDO);
        }

        // 登录获取用户信息&权限信息
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(sysUserDO3, sysUserVO);
        List<SysUserRoleDO> sysUserRoleDOList = sysUserRoleService.userRolesByUserId(sysUserDO3.getId());
        List<Object> sysUserBORoles = new ArrayList<>();
        for (SysUserRoleDO sysUserRoleDO : sysUserRoleDOList) {
            long roleId = sysUserRoleDO.getRoleId();
            SysRoleDO sysRoleDO = sysRoleService.roleById(roleId);
            sysUserBORoles.add(sysRoleDO);
        }
        sysUserVO.setRoles(sysUserBORoles);

        return sysUserVO;
    }

    @Override
    public SysUserVO updateSysUser(HeaderBO headerBO, SysUserDTO sysUserDTO) throws Exception {
        SysUserDO sysUserDO = new SysUserDO();
        String token = headerBO.getToken();
        SysUserDO sysUserDO1 = sysUserMapper.getUserByToken(token);
        long createdBy = sysUserDO1.getId();
        long updatedBy = createdBy;
        Date now = new Date();

        sysUserDO.setUpdatedAt(now);
        sysUserDO.setUpdatedBy(updatedBy);

        long userId = sysUserDTO.getId();
        String username = sysUserDTO.getUsername();
        String name = sysUserDTO.getName();
        String avatar = sysUserDTO.getAvatar();
        String phone = sysUserDTO.getPhone();
        sysUserDO.setId(userId);
        sysUserDO.setUsername(username);
        sysUserDO.setName(name);
        sysUserDO.setAvatar(avatar);
        sysUserDO.setPhone(phone);

        // 校验此用户名是否存在
        SysUserDO sysUserDO2 = sysUserMapper.getUserById(userId);
        if (sysUserDO2 == null) {
            throw new BizException(ErrorCodeEnum.SYS_EDIT_USER_NOT_EXIST.getCode(), ErrorCodeEnum.SYS_EDIT_USER_NOT_EXIST.getMsg());
        }
        // 用户名、真实姓名、头像、手机号、阿里号 - 附角色
        sysUserMapper.updateSysUser(sysUserDO);

        // 删除原有用户角色关系
        sysUserRoleService.delUserRolesByUserId(userId);

        String roleIds = sysUserDTO.getRoleIds();
        String[] roles = roleIds.split(",");
        for (String roleIdStr : roles) {
            long roleId = Long.valueOf(roleIdStr);
            SysUserRoleDO sysUserRoleDO = new SysUserRoleDO();
            sysUserRoleDO.setRoleId(roleId);
            sysUserRoleDO.setUserId(userId);
            sysUserRoleDO.setCreatedAt(now);
            sysUserRoleDO.setUpdatedAt(now);
            sysUserRoleDO.setCreatedBy(createdBy);
            sysUserRoleDO.setUpdatedBy(updatedBy);
            sysUserRoleService.addSysUserRole(sysUserRoleDO);
        }

        // 获取用户信息 - result
        SysUserDO sysUserDO3 = sysUserMapper.getUserById(userId);
        // 登录获取用户信息&权限信息
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(sysUserDO3, sysUserVO);
        List<SysUserRoleDO> sysUserRoleDOList = sysUserRoleService.userRolesByUserId(userId);
        List<Object> sysUserBORoles = new ArrayList<>();
        for (SysUserRoleDO sysUserRoleDO : sysUserRoleDOList) {
            long roleId = sysUserRoleDO.getRoleId();
            SysRoleDO sysRoleDO = sysRoleService.roleById(roleId);
            sysUserBORoles.add(sysRoleDO);
        }
        sysUserVO.setRoles(sysUserBORoles);

        return sysUserVO;
    }

    @Override
    public boolean delSysUser(long userId) {
        return sysUserMapper.realDelByUserId(userId) == 1;
    }

    @Override
    public boolean resetSysPWD(HeaderBO headerBO, long userId) {
        String token = headerBO.getToken();
        SysUserDO sysUserDO = sysUserMapper.getUserByToken(token);
        Date now = new Date();

        String password = "123456";
        Boolean flag = sysUserMapper.resetSysPWD(userId, password, sysUserDO.getId(), now) == 1;
        return flag;
    }

    @Override
    public boolean modifySysPWD(HeaderBO headerBO, SysModifyPWDDTO sysModifyPWDDTO) throws Exception {
        String token = headerBO.getToken();
        String originPWD = sysModifyPWDDTO.getOriginPWD();
        String pwd = sysModifyPWDDTO.getPwd();
        String surePWD = sysModifyPWDDTO.getSurePWD();

        // 校验原密码
        SysUserDO sysUserDO = sysUserMapper.getUserByToken(token);
        String dbPWD = sysUserDO.getPassword();
        if (!originPWD.equals(dbPWD)) {
            throw new BizException(ErrorCodeEnum.SYS_MODIFY_PWD_NOT_RIGHT.getCode(), ErrorCodeEnum.SYS_MODIFY_PWD_NOT_RIGHT.getMsg());
        }

        if (!pwd.equals(surePWD)) {
            throw new BizException(ErrorCodeEnum.SYS_MODIFY_PWD_SURE_NOT_RIGHT.getCode(), ErrorCodeEnum.SYS_MODIFY_PWD_SURE_NOT_RIGHT.getMsg());
        }

        return sysUserMapper.resetSysPWD(sysUserDO.getId(), pwd, sysUserDO.getId(), new Date()) == 1;
    }

    @Override
    public Boolean sysUpdateUserStatus(String token, SysUpdateUserStatusDTO sysUpdateUserStatusDTO) {
        Date now = new Date();
        SysUserDO sysUserDO = sysUserMapper.getUserByToken(token);

        int status = sysUpdateUserStatusDTO.getStatus();
        long id = sysUpdateUserStatusDTO.getId();
        return sysUserMapper.updateSysEnable(id, status, sysUserDO.getId(), now) == 1;
    }

}
