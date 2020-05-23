package com.trafficLab.modules.jingjia.service;

import com.alibaba.fastjson.JSONArray;
import com.trafficLab.modules.jingjia.entity.HomeStatisEntity;
import com.trafficLab.modules.jingjia.entity.JjGetInfoEntity;
import com.trafficLab.modules.jingjia.vo.HomeStatisVo;

import java.util.List;
import java.util.Map;

/**
 * author jinsq
 *
 * @date 2019/5/29 11:35
 */
public interface HomeStatisService {

    public HomeStatisVo getHomeStatis(long sysUserId);

    public List<JjGetInfoEntity>  queryInfo(long sysUserId);

    public Map<String,Object> queryAllTypeInfo(long sysUserId);
}
