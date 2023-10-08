package com.jpeony.lotus.core.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class SysRoleVO {

    private long id;

    private String name;

    private String intro;

    private List<SysMenuVO> routes;

}
