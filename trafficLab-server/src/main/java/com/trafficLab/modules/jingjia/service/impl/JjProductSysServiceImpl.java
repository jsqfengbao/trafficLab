package com.trafficLab.modules.jingjia.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trafficLab.common.utils.PageUtils;
import com.trafficLab.common.utils.Query;

import com.trafficLab.modules.jingjia.dao.JjProductSysDao;
import com.trafficLab.modules.jingjia.entity.JjProductSysEntity;
import com.trafficLab.modules.jingjia.service.JjProductSysService;


@Service("jjProductSysService")
public class JjProductSysServiceImpl extends ServiceImpl<JjProductSysDao, JjProductSysEntity> implements JjProductSysService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<JjProductSysEntity> page = this.page(
                new Query<JjProductSysEntity>().getPage(params),
                new QueryWrapper<JjProductSysEntity>()
        );

        return new PageUtils(page);
    }

}