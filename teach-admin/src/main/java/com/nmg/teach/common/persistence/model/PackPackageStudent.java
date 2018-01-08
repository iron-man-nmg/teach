package com.nmg.teach.common.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 套餐、学生关系表
 * </p>
 *
 * @author nmg
 * @since 2018-01-08
 */
@TableName("pack_package_student")
public class PackPackageStudent extends Model<PackPackageStudent> {

    private static final long serialVersionUID = 1L;

	private Integer id;
    /**
     * 套餐id，关联pack_package表的id
     */
	@TableField("package_id")
	private Integer packageId;
    /**
     * 学生id，关联less_student表的id
     */
	@TableField("student_id")
	private Integer studentId;
    /**
     * 剩余课时数
     */
	@TableField("remaining_hour")
	private Integer remainingHour;
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

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getRemainingHour() {
		return remainingHour;
	}

	public void setRemainingHour(Integer remainingHour) {
		this.remainingHour = remainingHour;
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
		return "PackPackageStudent{" +
			"id=" + id +
			", packageId=" + packageId +
			", studentId=" + studentId +
			", remainingHour=" + remainingHour +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
