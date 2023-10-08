package com.mhjy.pojo.Bo;

import lombok.Data;

import java.util.List;

@Data
public class SysRoleBO {

    private long id;

    private String name;

    private String intro;

    private List<SysMenuBO> routes;

}
