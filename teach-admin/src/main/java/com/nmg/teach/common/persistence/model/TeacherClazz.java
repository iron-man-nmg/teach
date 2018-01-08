package com.nmg.teach.common.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 老师、班级关系表
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
@TableName("less_teacher_clazz")
public class TeacherClazz extends Model<TeacherClazz> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 班级id，关联less_class表的id
     */
	@TableField("clazz_id")
	private Integer clazzId;
    /**
     * 老师id，关联less_teacher表的id
     */
	@TableField("teacher_id")
	private Integer teacherId;
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

	public Integer getClazzId() {
		return clazzId;
	}

	public void setClazzId(Integer clazzId) {
		this.clazzId = clazzId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
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
		return "TeacherClazz{" +
			"id=" + id +
			", clazzId=" + clazzId +
			", teacherId=" + teacherId +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
