package com.trafficLab.modules.jingjia.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @date 2019-04-18 17:05:37
 */
@Data
@TableName("jj_get_info")
public class JjGetInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;

	/**
	 * 产品ID
	 */
	private String productId;
	/**
	 * 用户ID
	 */
	private Integer sysUserId;

//	@TableField(exist = false)
	private String wechat;

	/**
	 * 查看页面的比例
	 */
	private String viewPercent;
	/**
	 * 操作类型:1,仅访问 2，复制，3，点击
	 */
	private Integer operateType;
	/**
	 * 关键字
	 */
	private String keyword;
	/**
	 * 访问链接
	 */
	private String visitUrl;
	/**
	 * 
	 */
	private String visitIp;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 区域
	 */
	private String area;
	/**
	 * 省
	 */
	private String region;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区
	 */
	private String county;
	/**
	 * 运营商
	 */
	private String isp;
	/**
	 * 国家编码
	 */
	private String countryId;
	/**
	 * 区域ID
	 */
	private String areaId;
	/**
	 * 省ID
	 */
	private String regionId;
	/**
	 * 市ID
	 */
	private String cityId;
	/**
	 * 区ID
	 */
	private String countyId;
	/**
	 * 运营商ID
	 */
	private String ispId;
	/**
	 * 操作系统名称
	 */
	private String systemName;
	/**
	 * 浏览器名称
	 */
	private String appName;
	/**
	 * 浏览器版本号
	 */
	private String appVersion;
	/**
	 * 用户浏览器是否启用了cookie
	 */
	private String cookieEnabled;
	/**
	 * 用户计算机的cpu的型号，通常intel芯片返回"x86"（火狐没有）
	 */
	private String cpuClass;
	/**
	 *  浏览器支持的所有MIME类型的数组
	 */
	private String mimeType;
	/**
	 * 浏览器正在运行的操作系统平台，包括Win16(windows3.x) 
	 */
	private String platform;
	/**
	 * 安装在浏览器上的所有插件的数组
	 */
	private String plugins;
	/**
	 * 用户在自己的操作系统上设置的语言（火狐没有）
	 */
	private String userLanguage;
	/**
	 * 包含以下属性中所有或一部分的字符串：appCodeName,appName,appVersion,language,platform
	 */
	private String userAgent;
	/**
	 * 用户操作系统支持的默认语言（火狐没有）
	 */
	private String systemLanguage;
	/**
	 * 浏览器类型
	 */
	private String browserType;
	/**
	 * 与浏览器相关的内部代码名
	 */
	private String appCodeName;
	/**
	 * 辅版本号（通常应用于浏览器的补丁或服务包)
	 */
	private String appMinorVersion;
	/**
	 * 浏览器支持的语言 （IE没有）
	 */
	private String language;
	/**
	 * 返回浏览器是否处于在线模式（IE4以上版本）
	 */
	private String onLine;
	/**
	 * 浏览器正在运行的操作系统，其中可能有CPU的信息（IE没有）
	 */
	private String oscpu;
	/**
	 * 浏览器的产品名（IE没有）
	 */
	private String product;
	/**
	 * 关于浏览器更多信息（IE没有）
	 */
	private String productSub;
	/**
	 * 浏览器支持的加密类型（IE没有）
	 */
	private String securityPolicy;
	/**
	 * 返回一个UserProfile对象，它存储用户的个人信息（火狐没有）
	 */
	private String userProfile;
	/**
	 * 浏览器厂商名称（IE、火狐没有）
	 */
	private String vender;
	/**
	 * 关于浏览器厂商更多的信息 
	 */
	private String vendorSub;
	/**
	 * 访客地址
	 */
	private String visitAddress;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 是否有效
	 */
	private Integer enabled;

}
