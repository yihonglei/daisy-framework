package com.mhjy.pojo.Dto;

import lombok.Data;

@Data
public class SysMenuDto {

    private long id;
    private long parentid;

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

    private String enable;

    private int sort;
}
