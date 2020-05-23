package com.trafficLab.modules.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trafficLab.common.utils.PageUtils;
import com.trafficLab.modules.job.entity.ScheduleJobLogEntity;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
}
