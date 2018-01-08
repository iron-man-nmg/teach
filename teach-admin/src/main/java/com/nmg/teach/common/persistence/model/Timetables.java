package com.nmg.teach.common.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 课程表
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
@TableName("less_timetables")
public class Timetables extends Model<Timetables> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 周次
     */
	@TableField("day_of_week")
	private Integer dayOfWeek;
    /**
     * 课次
     */
	@TableField("lesson_of_day")
	private Integer lessonOfDay;
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

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public Integer getLessonOfDay() {
		return lessonOfDay;
	}

	public void setLessonOfDay(Integer lessonOfDay) {
		this.lessonOfDay = lessonOfDay;
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
		return "Timetables{" +
			"id=" + id +
			", dayOfWeek=" + dayOfWeek +
			", lessonOfDay=" + lessonOfDay +
			", beginTime=" + beginTime +
			", endTime=" + endTime +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
