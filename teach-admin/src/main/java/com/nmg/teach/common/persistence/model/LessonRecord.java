package com.nmg.teach.common.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 上课记录表
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
@TableName("less_lesson_record")
public class LessonRecord extends Model<LessonRecord> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 学生id，关联到less_student表的id
     */
	@TableField("student_id")
	private Integer studentId;
    /**
     * 班级id，关联到less_clazz表的id
     */
	@TableField("clazz_id")
	private Integer clazzId;
    /**
     * 课次
     */
	@TableField("lesson_of_day")
	private Integer lessonOfDay;
    /**
     * 老师id，可以关联到less_teacher的id
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

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getClazzId() {
		return clazzId;
	}

	public void setClazzId(Integer clazzId) {
		this.clazzId = clazzId;
	}

	public Integer getLessonOfDay() {
		return lessonOfDay;
	}

	public void setLessonOfDay(Integer lessonOfDay) {
		this.lessonOfDay = lessonOfDay;
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
		return "LessonRecord{" +
			"id=" + id +
			", studentId=" + studentId +
			", clazzId=" + clazzId +
			", lessonOfDay=" + lessonOfDay +
			", teacherId=" + teacherId +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
