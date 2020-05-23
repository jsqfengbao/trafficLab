package com.trafficLab.modules.job.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.trafficLab.modules.jingjia.entity.JjGetInfoEntity;
import com.trafficLab.modules.jingjia.entity.JjGetInfoEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * author jinsq
 *
 * @date 2019/5/17 9:34
 */
@Slf4j
public class CallTaoBaoIP {

    public static JjGetInfoEntity callAndUpdateArea(JjGetInfoEntity jjGetInfo){
        HttpResponse res = HttpRequest.get("http://ip.taobao.com/service/getIpInfo.php?ip="+jjGetInfo.getVisitIp()).execute();
        log.info("resStatus状态:"+res.getStatus()+",body内容:"+res.body());
        if("200".equals(res.getStatus()+"")){
            String result = res.body();
            log.info("result:"+result);
            JSONObject jsonObject = JSON.parseObject(result);
            if(jsonObject.getString("code").equals("0")){
                JSONObject data = jsonObject.getJSONObject("data");
                jjGetInfo.setVisitIp(data.getString("ip"));
                jjGetInfo.setCountry(data.getString("country"));
                jjGetInfo.setArea(data.getString("area"));
                jjGetInfo.setRegion(data.getString("region"));
                jjGetInfo.setCity(data.getString("city"));
                jjGetInfo.setCounty(data.getString("county"));
                jjGetInfo.setIsp(data.getString("isp"));
                jjGetInfo.setCountryId(data.getString("country_id"));
                jjGetInfo.setAreaId(data.getString("area_id"));
                jjGetInfo.setRegionId(data.getString("region_id"));
                jjGetInfo.setCityId(data.getString("city_id"));
                jjGetInfo.setCountyId(data.getString("county_id"));
                jjGetInfo.setIspId(data.getString("isp_id"));
            }
        }
        return jjGetInfo;
    }
}
