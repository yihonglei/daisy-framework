package com.jpeony.lotus.core.pojo.vo;

import lombok.Data;

import java.util.List;

@Data
public class SysMenuVO {

    private long id;

    private long parentId;

    private String path;

    private String component;

    private int hidden;

    private String redirect;

    private int alwaysShow;

    private String name;

    private String title;

    private String icon;

    private int noCache;

    private int breadcrumb;

    private int affix;

    private String activeMenu;

    private int enable;

    private int sort;

    private int checked;

    private List<String> roles;

    private List<SysMenuVO> children;

}
