package com.trafficLab.modules.jingjia.dao;

import com.trafficLab.modules.jingjia.entity.JjProductEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-04-19 18:17:18
 */
@Mapper
public interface JjProductDao extends BaseMapper<JjProductEntity> {


    @Update("update from jj_product set enabled = 0 where sys_user_id = #{sysUserId}")
    public boolean updateEnabledBySysUserId(@Param("sysUserId") long sysUserId);
}
