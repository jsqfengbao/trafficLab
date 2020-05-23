package com.trafficLab.modules.jingjia.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-13 14:30:38
 */
@Data
@TableName("jj_product_sys")
public class JjProductSysEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId(type = IdType.INPUT)
	private Integer sysUserId;

	/**
	 * 系统名称
	 */
	private String sysUserName;
	/**
	 * 最大产品数量
	 */
	private Integer maxProductNum;
	/**
	 * 截止时间
	 */
	private Date endTime;

	/**
	 * 备注
	 */
	private String mark;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 
	 */
	private Integer enabled;

}
