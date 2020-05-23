package com.trafficLab.modules.jingjia.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trafficLab.modules.jingjia.entity.HomeStatisEntity;
import com.trafficLab.modules.jingjia.entity.JjGetInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * author jinsq
 *
 * @date 2019/6/25 17:57
 */
@Mapper
public interface HomeStatisDao extends BaseMapper<JjGetInfoEntity> {

    @Select("select sys_user_id,isp,operate_type,region,system_name,create_time from jj_get_info where sys_user_id =#{sysUserId} and create_time >#{createTime}")
    List<JjGetInfoEntity> queryHomeStatis(long sysUserId, String createTime);
}
