package com.jpeony.daisy.cloud.admin.dto;

/**
 * 用户角色
 */
public class UserRoleDTO {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    private Long id;
    private String name;
    private boolean checked;

}
