package com.jpeony.lotus.core.service;

import com.jpeony.lotus.core.pojo.bo.HeaderBO;
import com.jpeony.lotus.core.pojo.bo.SysUserBO;
import com.jpeony.lotus.core.pojo.domain.SysUser;
import com.jpeony.lotus.core.pojo.dto.SysLoginDto;
import com.jpeony.lotus.core.pojo.dto.SysModifyPWDDto;
import com.jpeony.lotus.core.pojo.dto.SysUpdateUserStatusDto;
import com.jpeony.lotus.core.pojo.dto.SysUserDto;

import java.util.List;

public interface SysUserService {

    SysUser getUserById(long uid);

    SysUserBO doLogin(SysLoginDto sysLoginDto) throws Exception;

    SysUserBO userinfo(String token) throws Exception;

    Boolean logout(HeaderBO headerBO);

    List<SysUserBO> getSysUsersList(HeaderBO headerBO);

    SysUserBO addSysUser(HeaderBO headerBO, SysUserDto sysUserDto) throws Exception;

    SysUserBO updateSysUser(HeaderBO headerBO, SysUserDto sysUserDto) throws Exception;

    boolean delSysUser(long uid);

    boolean resetSysPWD(HeaderBO headerBO, long uid);

    boolean modifySysPWD(HeaderBO headerBO, SysModifyPWDDto sysModifyPWDDto) throws Exception;

    Boolean sysUpdateUserStatus(String token, SysUpdateUserStatusDto sysUpdateUserStatusDto);

}
