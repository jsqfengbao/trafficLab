package com.trafficLab.modules.jingjia.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trafficLab.common.utils.DateUtil;
import com.trafficLab.modules.jingjia.dao.provider.JjGetInfoProvider;
import com.trafficLab.modules.jingjia.enums.OperateTypeEnum;
import com.trafficLab.modules.jingjia.excel.*;
import com.trafficLab.modules.jingjia.vo.KeywordVo;
import com.trafficLab.modules.jingjia.vo.PerStatis;
import com.trafficLab.modules.jingjia.vo.VisitUrlVo;
import com.trafficLab.modules.jingjia.vo.WechatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trafficLab.common.utils.PageUtils;

import com.trafficLab.modules.jingjia.dao.JjGetInfoDao;
import com.trafficLab.modules.jingjia.entity.JjGetInfoEntity;
import com.trafficLab.modules.jingjia.service.JjGetInfoService;


@Service("jjGetInfoService")
public class JjGetInfoServiceImpl extends ServiceImpl<JjGetInfoDao, JjGetInfoEntity> implements JjGetInfoService {

    @Autowired
    private JjGetInfoDao jjGetInfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        List<JjGetInfoEntity> myUserEntityList = jjGetInfoDao.queryByCondition(params);
        int count = jjGetInfoDao.queryCount(params);
        Page<JjGetInfoEntity> jjGetInfoEntityPage = new Page<JjGetInfoEntity>();
        jjGetInfoEntityPage.setRecords(myUserEntityList);
        jjGetInfoEntityPage.setTotal(count);
        return new PageUtils(jjGetInfoEntityPage);
    }

    @Override
    public PageUtils selectByCondition(Map<String, Object> params) {
        int current = Integer.parseInt((String) params.get("page"));
        int limit = Integer.parseInt((String) params.get("limit"));
        String sysUserId = (Long) params.get("sysUserId") +"";
        Page<PerStatis> page = new Page<PerStatis>(current,limit);
        List<PerStatis> pageList = this.baseMapper.queryJjGetInfo(page,sysUserId);
        for(PerStatis perStatis : pageList){
            int copyNum = perStatis.getCopyNum();
            int visitNum = perStatis.getVisitNum();
            if(visitNum == 0){
                perStatis.setCopyRatio("0%");
            }else{
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                String num = df.format(((float)copyNum/visitNum)*100)+'%';//返回的是String类型
                perStatis.setCopyRatio(num);
            }
        }
        page.setRecords(pageList);
        return new PageUtils(page);
    }

    @Override
    public PageUtils selectWechat(Map<String, Object> params) {
        Page<WechatVo> page = new Page<WechatVo>();
        int count = jjGetInfoDao.queryCountByWechat(params);
        List<WechatVo> wechatVoList = this.baseMapper.queryWechatVo(params);
        for(WechatVo wechatVo : wechatVoList){
            int copyNum = wechatVo.getCopyNum();
            int visitNum = wechatVo.getVisitNum();
            if(visitNum == 0){
                wechatVo.setCopyRatio("0%");
            }else{
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                String num = df.format(((float)copyNum/visitNum)*100)+'%';//返回的是String类型
                wechatVo.setCopyRatio(num);
            }
        }
        page.setRecords(wechatVoList);
        page.setTotal(count);
        return new PageUtils(page);
    }

    @Override
    public PageUtils selectVisitUrl(Map<String, Object> params) {
        Page<VisitUrlVo> page = new Page<VisitUrlVo>();
        int count = jjGetInfoDao.queryCountByVisitUrl(params);
        List<VisitUrlVo> visitUrlVos = this.baseMapper.queryVisitUrlVo(params);
        for(VisitUrlVo visitUrlVo : visitUrlVos){
            int copyNum = visitUrlVo.getCopyNum();
            int visitNum = visitUrlVo.getVisitNum();
            if(visitNum == 0){
                visitUrlVo.setCopyRatio("0%");
            }else{
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                String num = df.format(((float)copyNum/visitNum)*100)+'%';//返回的是String类型
                visitUrlVo.setCopyRatio(num);
            }
        }
        page.setRecords(visitUrlVos);
        page.setTotal(count);
        return new PageUtils(page);
    }

    @Override
    public PageUtils selectKeyword(Map<String, Object> params) {
        Page<KeywordVo> page = new Page<KeywordVo>();
        String sysUserId = (Long) params.get("sysUserId") +"";
        int count = this.baseMapper.queryCountByKeyword(params);
        List<KeywordVo> keywordVoList = this.baseMapper.queryKeywordVo(params);
        for(KeywordVo keywordVo : keywordVoList){
            int copyNum = keywordVo.getCopyNum();
            int visitNum = keywordVo.getVisitNum();
            if(visitNum == 0){
                keywordVo.setCopyRatio("0%");
            }else{
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                String num = df.format(((float)copyNum/visitNum)*100)+'%';//返回的是String类型
                keywordVo.setCopyRatio(num);
            }
        }
        page.setRecords(keywordVoList);
        page.setTotal(count);
        return new PageUtils(page);
    }

    @Override
    public List<JjGetInfoEntity> getNullAreaList(){
        return jjGetInfoDao.getNullArea();
    }

    /*
    导出实时数据
     */
    public List<JjGetInfoModel> exportJjGetInfo(Map<String,Object> params){
        List<JjGetInfoModel> jjGetInfoModelList = new ArrayList<>();
        List<JjGetInfoEntity> jjGetInfoEntityList = jjGetInfoDao.queryByConditionNoPage(params);
        for(JjGetInfoEntity jjGetInfoEntity : jjGetInfoEntityList){
            JjGetInfoModel jjGetInfoModel = new JjGetInfoModel();
            jjGetInfoModel.setAppName(jjGetInfoEntity.getAppName());
            jjGetInfoModel.setCreateTime(DateUtil.date2Str(jjGetInfoEntity.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
            jjGetInfoModel.setIsp(jjGetInfoEntity.getIsp());
            jjGetInfoModel.setKeyword(jjGetInfoEntity.getKeyword());
            if(OperateTypeEnum.Visit.getValue().equals(jjGetInfoEntity.getOperateType().toString())){
                jjGetInfoModel.setOperateType(OperateTypeEnum.Visit.getDesc());
            }else if(OperateTypeEnum.Copy.getValue().equals(jjGetInfoEntity.getOperateType().toString())){
                jjGetInfoModel.setOperateType(OperateTypeEnum.Copy.getDesc());
            }
            jjGetInfoModel.setSystemName(jjGetInfoEntity.getSystemName());
            jjGetInfoModel.setVisitAddress(jjGetInfoEntity.getVisitAddress());
            jjGetInfoModel.setVisitIp(jjGetInfoEntity.getVisitIp());
            jjGetInfoModel.setVisitUrl(jjGetInfoEntity.getVisitUrl());
            jjGetInfoModel.setWechat(jjGetInfoEntity.getWechat());
            jjGetInfoModelList.add(jjGetInfoModel);
        }
        return jjGetInfoModelList;
    }

    /**
     * 每日统计数据导出
     * @param sysUserId
     * @return
     */
    @Override
    public List<PerStatisModel> exportPerStatis(long sysUserId){
        List<PerStatisModel> perStatisModelList = new ArrayList<>();
        List<PerStatis> perStatisList = jjGetInfoDao.queryByPerStatis(sysUserId+"");
        for(PerStatis perStatis : perStatisList){
            if(perStatis.getVisitNum() == 0){
                perStatis.setCopyRatio("0%");
            }else{
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                String num = df.format(((float)perStatis.getCopyNum()/perStatis.getVisitNum())*100)+'%';//返回的是String类型
                perStatis.setCopyRatio(num);
            }

            PerStatisModel vo = new PerStatisModel();
            vo.setDay(perStatis.getDay());
            vo.setCopyNum(perStatis.getCopyNum());
            vo.setCopyRatio(perStatis.getCopyRatio());
            vo.setVisitNum(perStatis.getVisitNum());
            perStatisModelList.add(vo);
        }
        return perStatisModelList;
    }

    @Override
    public List<WeChatModel> exportWechat(Map<String,Object> params){
        List<WeChatModel> weChatModelList = new ArrayList<>();
        List<WechatVo> vo = jjGetInfoDao.queryWechatNoPage(params);
        for(WechatVo wechatVo : vo){

            if(wechatVo.getVisitNum() == 0){
                wechatVo.setCopyRatio("0%");
            }else{
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                String num = df.format(((float)wechatVo.getCopyNum()/wechatVo.getVisitNum())*100)+'%';//返回的是String类型
                wechatVo.setCopyRatio(num);
            }
            WeChatModel weChatModel = new WeChatModel();
            weChatModel.setCopyNum(wechatVo.getCopyNum());
            weChatModel.setCopyRatio(wechatVo.getCopyRatio());
            weChatModel.setVisitNum(wechatVo.getVisitNum());
            weChatModel.setWechat(wechatVo.getWechat());

            weChatModelList.add(weChatModel);
        }
        return weChatModelList;
    }

    @Override
    public List<PageModel> exportPage(Map<String,Object> params){
        List<PageModel> pageModelList = new ArrayList<>();
        List<VisitUrlVo> vo = jjGetInfoDao.queryVisitUrlNoPage(params);
        for(VisitUrlVo visitUrlVo : vo){

            if(visitUrlVo.getVisitNum() == 0){
                visitUrlVo.setCopyRatio("0%");
            }else{
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                String num = df.format(((float)visitUrlVo.getCopyNum()/visitUrlVo.getVisitNum())*100)+'%';//返回的是String类型
                visitUrlVo.setCopyRatio(num);
            }
            PageModel pageModel = new PageModel();
            pageModel.setCopyNum(visitUrlVo.getCopyNum());
            pageModel.setCopyRatio(visitUrlVo.getCopyRatio());
            pageModel.setVisitNum(visitUrlVo.getVisitNum());
            pageModel.setVisitUrl(visitUrlVo.getVisitUrl());

            pageModelList.add(pageModel);
        }
        return pageModelList;
    }

    @Override
    public List<KeywordModel> exportKeyword(Map<String,Object> params){
        List<KeywordModel> keywordModelList = new ArrayList<>();
        List<KeywordVo> vo = jjGetInfoDao.queryKeywordNoPage(params);
        for(KeywordVo keywordVo : vo){

            if(keywordVo.getVisitNum() == 0){
                keywordVo.setCopyRatio("0%");
            }else{
                DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                String num = df.format(((float)keywordVo.getCopyNum()/keywordVo.getVisitNum())*100)+'%';//返回的是String类型
                keywordVo.setCopyRatio(num);
            }
            KeywordModel keywordModel = new KeywordModel();
            keywordModel.setCopyNum(keywordVo.getCopyNum());
            keywordModel.setCopyRatio(keywordVo.getCopyRatio());
            keywordModel.setVisitNum(keywordVo.getVisitNum());
            keywordModel.setKeyword(keywordVo.getKeyword());

            keywordModelList.add(keywordModel);
        }
        return keywordModelList;
    }
}