package com.trafficLab.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trafficLab.modules.sys.entity.SysRoleEntity;
import com.trafficLab.common.utils.PageUtils;

import java.util.List;
import java.util.Map;


/**
 * 角色
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysRoleService extends IService<SysRoleEntity> {

	PageUtils queryPage(Map<String, Object> params);

	void saveRole(SysRoleEntity role);

	void update(SysRoleEntity role);

	void deleteBatch(Long[] roleIds);

	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
