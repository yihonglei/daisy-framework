package com.jpeony.lotus.core.service;

import com.jpeony.lotus.core.pojo.bo.HeaderBO;
import com.jpeony.lotus.core.pojo.vo.SysUserVO;
import com.jpeony.lotus.core.pojo.domain.SysUserDO;
import com.jpeony.lotus.core.pojo.dto.SysLoginDTO;
import com.jpeony.lotus.core.pojo.dto.SysModifyPWDDTO;
import com.jpeony.lotus.core.pojo.dto.SysUpdateUserStatusDTO;
import com.jpeony.lotus.core.pojo.dto.SysUserDTO;

import java.util.List;

public interface SysUserService {

    SysUserDO getUserById(long userId);

    SysUserVO doLogin(SysLoginDTO sysLoginDTO) throws Exception;

    SysUserVO userInfo(String token) throws Exception;

    Boolean logout(HeaderBO headerBO);

    List<SysUserVO> getSysUsersList(HeaderBO headerBO);

    SysUserVO addSysUser(HeaderBO headerBO, SysUserDTO sysUserDTO) throws Exception;

    SysUserVO updateSysUser(HeaderBO headerBO, SysUserDTO sysUserDTO) throws Exception;

    boolean delSysUser(long userId);

    boolean resetSysPWD(HeaderBO headerBO, long userId);

    boolean modifySysPWD(HeaderBO headerBO, SysModifyPWDDTO sysModifyPWDDTO) throws Exception;

    Boolean sysUpdateUserStatus(String token, SysUpdateUserStatusDTO sysUpdateUserStatusDTO);

}
