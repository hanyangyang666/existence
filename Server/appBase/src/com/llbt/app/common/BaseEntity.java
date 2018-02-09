package com.llbt.app.common;

import java.io.Serializable;
import java.util.Date;

/**
 * 基本模型，定义公共基本属性
 * 
 * @author 邓重阳
 * 
 */
public class BaseEntity implements Serializable {
	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 5420251788226369271L;

	/**
	 * ID
	 */
	protected String id;

	/**
	 * 创建者ID
	 */
	protected String createBy;

	/**
	 * 创建时间
	 */
	protected Date createDate;

	/**
	 * 修改者ID
	 */
	protected String updateBy;

	/**
	 * 修改时间
	 */
	protected Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	

}