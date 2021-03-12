package com.jpeony.daisy.cloud.cms.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 文件上传
 */
public class FileDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//文件类型
	private Integer type;
	//URL地址
	private String url;
	//创建时间
	private Date createDate;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：文件类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：文件类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：URL地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：URL地址
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}
}
