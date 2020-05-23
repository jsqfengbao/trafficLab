package com.trafficLab.modules.jingjia.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trafficLab.common.utils.PageUtils;
import com.trafficLab.common.utils.Query;

import com.trafficLab.modules.jingjia.dao.JjProductDao;
import com.trafficLab.modules.jingjia.entity.JjProductEntity;
import com.trafficLab.modules.jingjia.service.JjProductService;


@Service("jjProductService")
public class JjProductServiceImpl extends ServiceImpl<JjProductDao, JjProductEntity> implements JjProductService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String sysUserId = (Long) params.get("sysUserId") +"";

        IPage<JjProductEntity> page = this.page(
                new Query<JjProductEntity>().getPage(params),
                new QueryWrapper<JjProductEntity>()
                        .eq(null != sysUserId,"sys_user_id",sysUserId)
                        .orderByDesc("create_time")
        );

        return new PageUtils(page);
    }

    @Override
    public int queryCount(Map<String, Object> params){
        long sysUserId = (long) params.get("sysUserId");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(sysUserId > 0,"sys_user_id",sysUserId);
        return  this.count(wrapper);
    }

}