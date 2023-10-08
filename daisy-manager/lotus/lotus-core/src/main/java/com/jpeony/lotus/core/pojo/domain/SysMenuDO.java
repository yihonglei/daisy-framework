package com.jpeony.lotus.core.pojo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class SysMenuDO {

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

    private Date createdAt;

    private long createdBy;

    private Date updatedAt;

    private long updatedBy;
    
}
