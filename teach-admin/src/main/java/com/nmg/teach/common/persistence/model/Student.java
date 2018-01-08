package com.nmg.teach.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
@TableName("less_student")
public class Student extends Model<Student> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 用户id，可关联到sys_user表的id
     */
	@TableField("user_id")
	private Integer userId;
    /**
     * 姓名
     */
	private String name;
    /**
     * 联系方式
     */
	@TableField("contact_phone")
	private String contactPhone;
    /**
     * 性别
     */
	private Integer sex;
    /**
     * 照片
     */
	@TableField("photo_url")
	private String photoUrl;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Student{" +
			"id=" + id +
			", userId=" + userId +
			", name=" + name +
			", contactPhone=" + contactPhone +
			", sex=" + sex +
			", photoUrl=" + photoUrl +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
