package com.trafficLab.modules.jingjia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.trafficLab.common.utils.PageUtils;
import com.trafficLab.modules.jingjia.entity.JjGetInfoEntity;
import com.trafficLab.modules.jingjia.excel.*;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-04-18 17:05:37
 */
public interface JjGetInfoService extends IService<JjGetInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public PageUtils selectByCondition(Map<String, Object> params);

    public PageUtils selectWechat(Map<String, Object> params);

    public PageUtils selectVisitUrl(Map<String, Object> params);

    public PageUtils selectKeyword(Map<String, Object> params);

    /**
     * 获取地区参数为空的数据
     * @return
     */
    public List<JjGetInfoEntity> getNullAreaList();

    /**
     * 导出查询的实时数据
     * @param params
     * @return
     */
    public List<JjGetInfoModel> exportJjGetInfo(Map<String, Object> params);

    public List<PerStatisModel> exportPerStatis(long sysUserId);

    public List<WeChatModel> exportWechat(Map<String, Object> params);

    public List<PageModel> exportPage(Map<String, Object> params);

    public List<KeywordModel> exportKeyword(Map<String, Object> params);
}

