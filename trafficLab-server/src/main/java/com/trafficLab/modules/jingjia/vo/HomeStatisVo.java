package com.trafficLab.modules.jingjia.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * author jinsq
 *
 * @date 2019/5/29 11:28
 */
@Data
public class HomeStatisVo {

    private List<String> ispName; //运营商名

    private JSONArray ispData;  //运营商数据

    private List<String> operateName; //操作系统名

    private JSONArray operateData; //操作系统数据

    private List<String> regionName; //地区名

    private JSONArray regionData; //地区数据

    private Integer regionMax; //最大值

    private Integer regionMin; //最小值

    private List<String> todayName; //今日时间

    private JSONArray todayData; //今日数据

    private List<String> thisMonthName; //当月时间

    private JSONArray thisMonthData; //当月数据

    private List<String> keywordName; //关键词名

    private List<String> keywordValue; //关键词值

}
