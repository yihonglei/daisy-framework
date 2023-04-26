package com.jpeony.lotus.core.pojo.dto;

import lombok.Data;

@Data
public class SysMenuDTO {

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

    private String enable;

    private int sort;

}
