package com.trafficLab.modules.jingjia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trafficLab.common.utils.PageUtils;
import com.trafficLab.modules.jingjia.entity.JjProductSysEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-13 14:30:38
 */
public interface JjProductSysService extends IService<JjProductSysEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

