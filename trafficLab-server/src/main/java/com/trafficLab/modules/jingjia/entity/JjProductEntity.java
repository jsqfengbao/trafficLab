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
 * @date 2019-04-19 18:17:18
 */
@Data
@TableName("jj_product")
public class JjProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type= IdType.INPUT)
	private String id;
	/**
	 * 用户ID
	 */
	private Integer sysUserId;
	/**
	 * 产品名称
	 */
	private String name;

	/**
	 * 微信号
	 */
	private String wechat;
	/**
	 * 代码链接
	 */
	private String codeUrl;
	/**
	 * 
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
