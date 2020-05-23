package com.trafficLab.modules.jingjia.service.impl;//package com.trafficLab.modules.jingjia.service.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.trafficLab.common.utils.DateUtil;
//import com.trafficLab.common.utils.JedisUtil;
//import com.trafficLab.common.utils.RedisUtils;
//import com.trafficLab.modules.jingjia.dao.JjGetInfoDao;
//import com.trafficLab.modules.jingjia.entity.JjGetInfoEntity;
//import com.trafficLab.modules.jingjia.enums.OperateTypeEnum;
//import com.trafficLab.modules.jingjia.service.HomeStatisService;
//import com.trafficLab.modules.jingjia.vo.HomeStatisVo;
//import com.trafficLab.modules.jingjia.vo.StatisDataDo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.beans.IntrospectionException;
//import java.beans.PropertyDescriptor;
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.*;
//
///**
// * author jinsq
// *
// * @date 2019/5/29 11:36
// */
////@Service("homeStatisService")
//@Slf4j
//public class HomeStatisServiceImplBak extends ServiceImpl<JjGetInfoDao, JjGetInfoEntity> implements HomeStatisService {
//
////    @Autowired
////    private KeywordAnsj keywordAnsj;
//    @Autowired
//    private JedisUtil jedisUtil;
//
//    @Override
//    public List<JjGetInfoEntity>  queryInfo(long sysUserId){
//        String thisMonth = "2019-05";//DateUtil.date2Str(new Date(),"yyyy-MM");
//        log.info("thisMonth"+thisMonth);
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.eq(sysUserId > 0,"sys_user_id",sysUserId)
//                .ge("create_time",thisMonth);
//        return this.list(wrapper);
//    }
//
//    @Override
//    public HomeStatisVo getHomeStatis(long sysUserId){
//        HomeStatisVo home = new HomeStatisVo();
//        Map<String,Object> mapAllTypeData = queryAllTypeInfo(sysUserId);
//        home.setIspName((List<String>) mapAllTypeData.get("ispName"));
//        home.setIspData((JSONArray) mapAllTypeData.get("ispData"));
//        home.setOperateName((List<String>) mapAllTypeData.get("operateName"));
//        home.setOperateData((JSONArray) mapAllTypeData.get("operateData"));
//        home.setRegionName((List<String>) mapAllTypeData.get("regionName"));
//        JSONArray regionData = (JSONArray) mapAllTypeData.get("regionData");
//        home.setRegionData(regionData);
//        home = setRegionRange(regionData,home);
////        home.setTodayName((List<String>) mapAllTypeData.get("todayName"));
////        home.setTodayData((JSONArray) mapAllTypeData.get("todayData"));
////        home.setThisMonthName((List<String>) mapAllTypeData.get("thisMonthName"));
////        home.setThisMonthData((JSONArray) mapAllTypeData.get("thisMonthData"));
////        home.setKeywordName((List<String>) mapAllTypeData.get("keywordName"));
////        home.setKeywordValue((List<String>) mapAllTypeData.get("keywordValue"));
//        return home;
//    }
//    @Override
//    public Map<String,Object> queryAllTypeInfo(long sysUserId){
//        Map<String,Object> result = new HashMap<>();
//
//        List<String> ispName = new ArrayList<>();
//        JSONArray ispData = new JSONArray();
//        List<String> systemName = new ArrayList<>();
//        JSONArray systemData = new JSONArray();
//        List<String> regionName = new ArrayList<>();
//        JSONArray regionData = new JSONArray();
//
//        List<JjGetInfoEntity> jjGetInfoEntities = queryInfo(sysUserId);
//        List<JjGetInfoEntity> regionGetInfoEntities = new ArrayList<>();
//        for(JjGetInfoEntity jjGetInfoEntity : jjGetInfoEntities){
//            if(OperateTypeEnum.Copy.getValue().equals(jjGetInfoEntity.getOperateType().toString())){
//                regionGetInfoEntities.add(jjGetInfoEntity);
//            }
//        }
//        Map<String,Integer> mapIsp = getListByGroupBy(jjGetInfoEntities,"isp");
//        Map<String,Integer> mapSystemName = getListByGroupBy(jjGetInfoEntities,"systemName");
//        Map<String,Integer> mapRegion = getListByGroupBy(regionGetInfoEntities,"region");
//
//        result = runSetMap("isp",mapIsp,ispName,ispData,result);
//        result = runSetMap("operate",mapSystemName,systemName,systemData,result);
//        result = runSetMap("region",mapRegion,regionName,regionData,result);
////        result = getTimeData(jjGetInfoEntities,result);
////        Map<String,List<String>> keywordMap = keywordAnsj.parseKeyWord(jjGetInfoEntities);
////        result.put("keywordName",keywordMap.get("keywordName"));
////        result.put("keywordValue",keywordMap.get("keywordValue"));
//        return result;
//    }
//
//
//    private Map<String,Integer> getListByGroupBy(List<JjGetInfoEntity> list,String attributeName){
//        Map<String,Integer> map = new HashMap<>();
//        for(JjGetInfoEntity jjGetInfoEntity : list){
//            if(JSON.toJSONString(jjGetInfoEntity).contains(attributeName)) {
//
//                String attributeValue = getGetMethodValue(jjGetInfoEntity, attributeName).toString();
//                if (map.containsKey(attributeValue)) {
//                    map.put(attributeValue, map.get(attributeValue) + 1);
//                } else {
//                    map.put(attributeValue, 1);
//                }
//            }
//        }
//        return sortMap(map,attributeName,"value");
//    }
//
//    /**
//     * 对map进行排序
//     * @param map 传入的map
//     * @param attributeName 属性名
//     * @param type 排序类型，支持key和value
//     * @return
//     */
//    private Map<String,Integer> sortMap(Map<String,Integer> map,String attributeName,String type){
//        Map<String,Integer> result = new TreeMap<>();
//        List<Map.Entry<String,Integer>> listSort = new ArrayList<>(map.entrySet());
//        Collections.sort(listSort,new Comparator<Map.Entry<String,Integer>>() {
//
//            public int compare(Map.Entry<String, Integer> o1,
//                               Map.Entry<String, Integer> o2) {
//                if("value".equals(type)){
//                    return o2.getValue().compareTo(o1.getValue());   //降序排序
//                }else {
//                    return o1.getKey().compareTo(o2.getKey()); //升序排序
//                }
//            }
//        });
//        int i =0;
//        for(Map.Entry<String,Integer> entry : listSort){
//            if(i >= 5 && "isp".equals(attributeName)){
//                break;
//            } else if(i >= 5 && "systemName".equals(attributeName)){
//                break;
//            } else if(i>= 34 && "region".equals(attributeName)){
//                break;
//            } else if(i>= 24 && "today".equals(attributeName)){
//                break;
//            } else if(i>= 31 && "thisMonth".equals(attributeName)){
//                break;
//            }
//            result.put(entry.getKey(),entry.getValue());
//            i++;
//        }
//        return result;
//    }
//
//    /**
//     * 获取对应属性的方法值
//     * @param jjGetInfoEntity
//     * @param attributeName
//     * @return
//     * @throws Exception
//     */
//    private Object getGetMethodValue(JjGetInfoEntity jjGetInfoEntity ,String attributeName) {
////        log.info("对象信息为："+JSON.toJSONString(jjGetInfoEntity)+",属性为:"+attributeName);
//        Class<?> aClass = jjGetInfoEntity.getClass();
//        Field declaredField = null;
//        try {
//            declaredField = aClass.getDeclaredField(attributeName);
//
//            declaredField.setAccessible(true);
//            PropertyDescriptor pd = new PropertyDescriptor(declaredField.getName(),aClass);
//            Method readMethod = pd.getReadMethod();
//            Object result = readMethod.invoke(jjGetInfoEntity);
//            return result;
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IntrospectionException ei) {
//            ei.printStackTrace();
//        } catch (InvocationTargetException et) {
//            et.printStackTrace();
//        } catch (IllegalAccessException ec) {
//            ec.printStackTrace();
//        }
//        return "";
//    }
//
//    /**
//     * 给map赋值
//     * @param type 赋值类型
//     * @param mapData 所取到的数据
//     * @param attributeName 赋值属性列表
//     * @param attributeData 复制数据
//     */
//    private Map<String,Object> runSetMap(String type,Map<String,Integer> mapData,List<String> attributeName,JSONArray attributeData,Map<String,Object> result){
//        for(Map.Entry<String,Integer> entry : mapData.entrySet()){
//            StatisDataDo dataDo = new StatisDataDo();
//            dataDo.setValue(entry.getValue());
//            dataDo.setName(entry.getKey());
//
//            attributeName.add(entry.getKey());
//            attributeData.add(JSON.toJSON(dataDo));
//        }
//        result.put(type+"Data",attributeData);
//        result.put(type+"Name",attributeName);
//
//        return result;
//    }
//
//    private Map<String,Object> getTimeData(List<JjGetInfoEntity> jjGetInfoEntityList,Map<String,Object> result){
//        String timeName[] = new String[]{"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
//        String dayName[] = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
//        List<String> todayName = new ArrayList<>(); //Arrays.asList(timeName);
//        List<String> thisMonthName = new ArrayList<>(); //Arrays.asList(dayName);
//
//        JSONArray todayData = new JSONArray();
//        JSONArray thisMonthData = new JSONArray();
//        Map<String,Integer> todayMap = new HashMap<>();
//        Map<String,Integer> thisMonthMap = new HashMap<>();
//
//        for(JjGetInfoEntity jjGetInfoEntity : jjGetInfoEntityList){
//            String createTime = DateUtil.date2Str(jjGetInfoEntity.getCreateTime(),"yyyy-MM-dd HH:mm:ss");
//            String thisDay = DateUtil.date2Str(new Date(),"yyyy-Mm-dd HH:mm:ss").substring(8,10);
//            String theDay = createTime.substring(8,10);
//            String theTime = createTime.substring(11,13);
//
//            if(thisMonthMap.containsKey(theDay)){
//                int i = thisMonthMap.get(theDay);
//                i = i + 1;
//                thisMonthMap.replace(theDay,i);
//            }else{
//                thisMonthMap.put(theDay,1);
//            }
//            if(thisDay.equals(theDay)){
//                if(todayMap.containsKey(theTime)){
//                    int i = todayMap.get(theTime);
//                    i = i+1;
//                    todayMap.replace(theTime,i);
//                }else{
//                    todayMap.put(theTime,1);
//                }
//            }
//        }
//        Map<String,Integer> todayMapSort = sortMap(todayMap,"today","key");
//        Map<String,Integer> thisMonthMapSort = sortMap(thisMonthMap,"thisMonth","key");
//        result = runSetMap("today",todayMapSort,todayName,todayData,result);
//        result = runSetMap("thisMonth",thisMonthMapSort,thisMonthName,thisMonthData,result);
//        return result;
//    }
//
//    /**
//     * 赋值最大值和最小值
//     * @param regionData
//     * @param home
//     * @return
//     */
//    private HomeStatisVo setRegionRange(JSONArray regionData,HomeStatisVo home){
//        int regionMax = 0;
//        int regionMin = 99999;
//        for(int i =0;i<regionData.size();i++){
//            JSONObject jsonObject = regionData.getJSONObject(i);
//            int value = (int) jsonObject.get("value");
//            if(regionMin >= value){
//                regionMin = value;
//            }
//            if(regionMax <= value){
//                regionMax = value;
//            }
//        }
//        home.setRegionMax(regionMax);
//        home.setRegionMin(regionMin);
//
//        return home;
//    }
//}
