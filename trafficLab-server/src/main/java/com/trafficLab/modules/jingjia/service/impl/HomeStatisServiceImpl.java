package com.trafficLab.modules.jingjia.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trafficLab.common.utils.DateUtil;
import com.trafficLab.common.utils.StringUtils;
import com.trafficLab.modules.jingjia.dao.HomeStatisDao;
import com.trafficLab.modules.jingjia.dao.JjGetInfoDao;
import com.trafficLab.modules.jingjia.entity.HomeStatisEntity;
import com.trafficLab.modules.jingjia.entity.JjGetInfoEntity;
import com.trafficLab.modules.jingjia.service.HomeStatisService;
import com.trafficLab.modules.jingjia.vo.HomeStatisVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * author jinsq
 *
 * @date 2019/5/29 11:36
 */
@Service("homeStatisService")
@Slf4j
public class HomeStatisServiceImpl extends ServiceImpl<JjGetInfoDao, JjGetInfoEntity> implements HomeStatisService {

    @Autowired
    private HomeStatisDao homeStatisDao;

    @Override
    public List<JjGetInfoEntity>  queryInfo(long sysUserId){
        String thisMonth = DateUtil.date2Str(new Date(),"yyyy-MM"); //"2019-05";
        return homeStatisDao.queryHomeStatis(sysUserId,thisMonth);
    }

    @Override
    public HomeStatisVo getHomeStatis(long sysUserId){
        HomeStatisVo home = new HomeStatisVo();
        Map<String,Object> mapAllTypeData = queryAllTypeInfo(sysUserId);
        home.setIspName((List<String>) mapAllTypeData.get("ispName"));
        home.setIspData((JSONArray) mapAllTypeData.get("ispData"));
        home.setOperateName((List<String>) mapAllTypeData.get("operateName"));
        home.setOperateData((JSONArray) mapAllTypeData.get("operateData"));
        home.setRegionName((List<String>) mapAllTypeData.get("regionName"));
        home.setRegionData((JSONArray) mapAllTypeData.get("regionData"));
        home.setRegionMax((Integer) mapAllTypeData.get("regionMax"));
        home.setRegionMin((Integer) mapAllTypeData.get("regionMin"));
        home.setTodayName((List<String>) mapAllTypeData.get("todayName"));
        home.setTodayData((JSONArray) mapAllTypeData.get("todayData"));
        home.setThisMonthName((List<String>) mapAllTypeData.get("thisMonthName"));
        home.setThisMonthData((JSONArray) mapAllTypeData.get("thisMonthData"));
        return home;
    }

    public Map<String,Object> queryAllTypeInfo(long sysUserId){
        Map<String,Object> result = new HashMap<>();
        String[] ispNameArr = {"电信","移动","联通"};
        String[] operateNameArr = {"Android","WP","iOS","Others"};
        String[] regionNameArr = {"广东","北京","江苏","山东","河北","浙江","湖南","安徽","辽宁","江西","四川","天津","湖北",
                "陕西","河南","福建","山西","黑龙江","上海","内蒙古","广西","云南","重庆","吉林","贵州","甘肃","新疆","宁夏",
                "海南","青海","西藏","香港","台湾","澳门","XX"};
        String todayNameArr[] = new String[]{"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
        String thisMonthNameArr[] = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};

        int dianxin = 0,yidong = 0,liantong =0;
        int android = 0,wp = 0,ios = 0,others = 0;
        Map<String,Integer> regionMap = new HashMap<>();
        Map<String,Integer> todayMap = new TreeMap<>();
        Map<String,Integer> thisMonthMap = new TreeMap<>();

        for(int i =0;i<regionNameArr.length;i++){
            regionMap.put(regionNameArr[i],0);
        }
        log.info("-------------regionMap初始化数据完成----------");
        for(int i = 0;i<todayNameArr.length;i++){
            todayMap.put(todayNameArr[i],0);
        }
        log.info("-------------todayMap初始化数据完成----------");
        for(int i = 0;i<thisMonthNameArr.length;i++){
            thisMonthMap.put(thisMonthNameArr[i],0);
        }
        log.info("-------------thisMonthMap初始化数据完成----------");
        List<JjGetInfoEntity> jjGetInfoEntities = queryInfo(sysUserId);
        log.info("-------------数据查询-----初始化数据完成----------");
        String thisDay = DateUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss").substring(8,10); //今天-日
        for(JjGetInfoEntity jjGetInfoEntity : jjGetInfoEntities){
            if(regionMap.containsKey(jjGetInfoEntity.getRegion()) && jjGetInfoEntity.getOperateType() == 2){
                int value = regionMap.get(jjGetInfoEntity.getRegion());
                regionMap.replace(jjGetInfoEntity.getRegion(),++value);
            }
            String createTime = DateUtil.date2Str(jjGetInfoEntity.getCreateTime(),"yyyy-MM-dd HH:mm:ss");
            String theDay = createTime.substring(8,10); //那天-日
            String theTime = createTime.substring(11,13); //那天-时
            if(thisDay.equals(theDay) && todayMap.containsKey(theTime)){
                int value = todayMap.get(theTime);
                todayMap.replace(theTime,++value);
            }
            if(thisMonthMap.containsKey(theDay)){
                int value = thisMonthMap.get(theDay);
                thisMonthMap.replace(theDay,++value);
            }

            if(null != jjGetInfoEntity.getIsp() || StringUtils.isNotBlank(jjGetInfoEntity.getIsp())){
                switch (jjGetInfoEntity.getIsp()){   //运营商
                    case "电信":
                        dianxin++;
                        break;
                    case "移动":
                        yidong++;
                        break;
                    case "联通":
                        liantong++;
                        break;
                }
            }
            switch (jjGetInfoEntity.getSystemName()){  //操作系统类型
                case "Android":
                    android++;
                    break;
                case "WP":
                    wp++;
                    break;
                case "iOS":
                    ios++;
                    break;
                case "Others":
                    others++;
                    break;
            }
        }
        log.info("-------------数据计算完成----------");
        List<String> ispName = Arrays.asList(ispNameArr);
        List<String> operateName = Arrays.asList(operateNameArr);
        List<String> regionName = Arrays.asList(regionNameArr);
        List<String> todayName = Arrays.asList(todayNameArr);
        List<String> thisMonthName = Arrays.asList(thisMonthNameArr);

        JSONArray ispData = new JSONArray();
        for(int i = 0;i<ispName.size();i++){
            JSONObject isp = new JSONObject();
            String name = ispName.get(i);
            if(name.equals("电信")){
                isp.put("name",name);
                isp.put("value",dianxin);
            }else if(name.equals("联通")){
                isp.put("name",name);
                isp.put("value",liantong);
            }else if(name.equals("移动")){
                isp.put("name",name);
                isp.put("value",yidong);
            }
            ispData.add(isp);
        }
        log.info("-------------ispName组装JSONArray完成----------");
        JSONArray operateData = new JSONArray();
        for(int i =0;i<operateName.size();i++){
            JSONObject jsonObject = new JSONObject();
            String name = operateName.get(i);
            if(name.equals("Android")){
                jsonObject.put("name",name);
                jsonObject.put("value",android);
            }else if(name.equals("WP")){
                jsonObject.put("name",name);
                jsonObject.put("value",wp);
            }else if(name.equals("iOS")){
                jsonObject.put("name",name);
                jsonObject.put("value",ios);
            }else if(name.equals("Others")){
                jsonObject.put("name",name);
                jsonObject.put("value",others);
            }
            operateData.add(jsonObject);
        }
        log.info("-------------operateName组装JSONArray完成----------");

        sortMap(todayMap,"key");
        sortMap(thisMonthMap,"key");
        JSONArray regionData = new JSONArray();
        for(String key: regionMap.keySet()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",key);
            jsonObject.put("value",regionMap.get(key));

            regionData.add(jsonObject);
        }
        log.info("-------------regionData组装完成----------");
        JSONArray todayData = new JSONArray();
        for(String key : todayMap.keySet()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",key);
            jsonObject.put("value",todayMap.get(key));

            todayData.add(jsonObject);
        }
        log.info("-------------todayData组装完成----------");
        JSONArray thisMonthData = new JSONArray();
        for(String key : thisMonthMap.keySet()){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name",key);
            jsonObject.put("value",thisMonthMap.get(key));

            thisMonthData.add(jsonObject);
        }
        log.info("-------------thisMonthData组装完成----------");
        result = setRegionRange(regionMap,result);
        result.put("ispName",ispName);
        result.put("ispData",ispData);
        result.put("operateName",operateName);
        result.put("operateData",operateData);
        result.put("regionName",regionName);
        result.put("regionData",regionData);
        result.put("todayName",todayName);
        result.put("todayData",todayData);
        result.put("thisMonthName",thisMonthName);
        result.put("thisMonthData",thisMonthData);
        return result;
    }

    /**
     * 赋值最大值和最小值
     * @return
     */
    private Map<String,Object> setRegionRange(Map<String,Integer> mapRegion,Map<String,Object> result){
        int regionMax = 0;
        int regionMin = 99999;
        for(String key : mapRegion.keySet()){
            int value = mapRegion.get(key);
            if(regionMin >= value){
                regionMin = value;
            }
            if(regionMax <= value){
                regionMax = value;
            }
        }
        result.put("regionMax",regionMax);
        result.put("regionMin",regionMin);

        return result;
    }

    /**
          * 对map进行排序
          * @param map 传入的map
          * @param type 排序类型，支持key和value
          * @return
          */
    private Map<String,Integer> sortMap(Map<String,Integer> map,String type){
        Map<String,Integer> result = new TreeMap<>();
        List<Map.Entry<String,Integer>> listSort = new ArrayList<>(map.entrySet());
        Collections.sort(listSort,new Comparator<Map.Entry<String,Integer>>() {

            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                if("value".equals(type)){
                    return o2.getValue().compareTo(o1.getValue());   //降序排序
                }else {
                    return o1.getKey().compareTo(o2.getKey()); //升序排序
                }
            }
        });

        return result;
    }

}
