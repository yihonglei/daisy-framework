package com.mhjy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysMenu {

    private long id;
    private long parent_id;

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

    private Date created_at;

    private long created_by;

    private Date updated_at;

    private long updated_by;
}
