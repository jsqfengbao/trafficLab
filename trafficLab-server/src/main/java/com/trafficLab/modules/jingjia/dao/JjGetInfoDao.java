package com.trafficLab.modules.jingjia.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trafficLab.modules.jingjia.dao.provider.JjGetInfoProvider;
import com.trafficLab.modules.jingjia.entity.JjGetInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trafficLab.modules.jingjia.vo.KeywordVo;
import com.trafficLab.modules.jingjia.vo.PerStatis;
import com.trafficLab.modules.jingjia.vo.VisitUrlVo;
import com.trafficLab.modules.jingjia.vo.WechatVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-04-18 17:05:37
 */
@Mapper
public interface JjGetInfoDao extends BaseMapper<JjGetInfoEntity> {

    @Select("select DATE_FORMAT(create_time,'%Y-%m-%d') day,count(id) visitNum ,sum(operate_type =2) as copyNum from jj_get_info where sys_user_id = #{sysUserId} group by day")
    public List<PerStatis> queryJjGetInfo(Page<PerStatis> page, String sysUserId);

    @Select("select DATE_FORMAT(create_time,'%Y-%m-%d') day,count(id) visitNum ,sum(operate_type =2) as copyNum from jj_get_info where sys_user_id = #{sysUserId} group by day")
    public List<PerStatis> queryByPerStatis(String sysUserId);

    @Select("select * from jj_get_info where country is null limit 1000")
    public List<JjGetInfoEntity> getNullArea();

    @SelectProvider(type = JjGetInfoProvider.class,method = "queryWechatVo")
    public List<WechatVo> queryWechatVo(Map<String, Object> params);

    @SelectProvider(type = JjGetInfoProvider.class,method = "queryWechatNoPage")
    public List<WechatVo> queryWechatNoPage(Map<String, Object> params);

    @SelectProvider(type=JjGetInfoProvider.class,method = "queryCountByWechat")
    public int queryCountByWechat(Map<String, Object> params);

    @SelectProvider(type = JjGetInfoProvider.class,method = "queryVisitUrlVo")
    public List<VisitUrlVo> queryVisitUrlVo(Map<String, Object> params);

    @SelectProvider(type=JjGetInfoProvider.class,method="queryVisitUrlNoPage")
    public List<VisitUrlVo> queryVisitUrlNoPage(Map<String, Object> params);

    @SelectProvider(type=JjGetInfoProvider.class,method = "queryCountByVisitUrl")
    public int queryCountByVisitUrl(Map<String, Object> params);

    @SelectProvider(type=JjGetInfoProvider.class,method = "queryKeywordVo")
    public List<KeywordVo> queryKeywordVo(Map<String, Object> params);

    @SelectProvider(type = JjGetInfoProvider.class,method="queryKeywordNoPage")
    public List<KeywordVo> queryKeywordNoPage(Map<String, Object> params);

    @SelectProvider(type=JjGetInfoProvider.class,method = "queryCountByKeyword")
    public int queryCountByKeyword(Map<String, Object> params);

    @SelectProvider(type = JjGetInfoProvider.class,method = "queryJjGetInfo")
    public List<JjGetInfoEntity> queryByCondition(Map<String, Object> params);

    @SelectProvider(type = JjGetInfoProvider.class,method = "queryJjGetInfoByNoPage")
    public List<JjGetInfoEntity> queryByConditionNoPage(Map<String, Object> params);

    @SelectProvider(type = JjGetInfoProvider.class,method = "queryCount")
    public int queryCount(Map<String, Object> params);
}
