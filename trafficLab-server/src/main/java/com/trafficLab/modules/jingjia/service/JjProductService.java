package com.trafficLab.modules.jingjia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trafficLab.common.utils.PageUtils;
import com.trafficLab.modules.jingjia.entity.JjProductEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-04-19 18:17:18
 */
public interface JjProductService extends IService<JjProductEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public int queryCount(Map<String, Object> params);
}

