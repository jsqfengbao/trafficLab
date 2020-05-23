package com.trafficLab.modules.jingjia.vo;

import lombok.Data;

/**
 * author jinsq
 *
 * @date 2019/4/29 17:04
 */
@Data
public class PerStatis {

    private String day;       //日期
    private Integer visitNum; //访问次数
    private Integer copyNum;  //复制次数
    private String copyRatio;  //复制比率
}
