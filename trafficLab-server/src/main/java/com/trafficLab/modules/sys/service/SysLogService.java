package com.trafficLab.modules.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.trafficLab.modules.sys.entity.SysLogEntity;
import com.trafficLab.common.utils.PageUtils;

import java.util.Map;


/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
