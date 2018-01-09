package com.nmg.teach.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 套餐表
 * </p>
 *
 * @author nmg
 * @since 2018-01-09
 */
@TableName("pack_package")
public class PackPackage extends Model<PackPackage> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 名字
     */
	private String name;
    /**
     * 价格
     */
	private BigDecimal price;
    /**
     * 课时
     */
	@TableField("clazz_hour")
	private Integer clazzHour;
    /**
     * 备注
     */
	private String desc;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getClazzHour() {
		return clazzHour;
	}

	public void setClazzHour(Integer clazzHour) {
		this.clazzHour = clazzHour;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
		return "PackPackage{" +
			"id=" + id +
			", name=" + name +
			", price=" + price +
			", clazzHour=" + clazzHour +
			", desc=" + desc +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
