package com.nmg.teach.common.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 班级、学生关系历史表
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
@TableName("history_clazz_student")
public class HistoryClazzStudent extends Model<HistoryClazzStudent> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 班级id，关联less_clazz表的id
     */
	@TableField("clazz_id")
	private Integer clazzId;
    /**
     * 学生id，关联less_student表的id
     */
	@TableField("student_id")
	private Integer studentId;
    /**
     * 开始时间
     */
	@TableField("begin_time")
	private Date beginTime;
    /**
     * 结束时间
     */
	@TableField("end_time")
	private Date endTime;
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

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
		return "HistoryClazzStudent{" +
			"id=" + id +
			", clazzId=" + clazzId +
			", studentId=" + studentId +
			", beginTime=" + beginTime +
			", endTime=" + endTime +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
