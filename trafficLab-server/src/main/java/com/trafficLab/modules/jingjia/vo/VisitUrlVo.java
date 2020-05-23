package com.trafficLab.modules.jingjia.vo;

import lombok.Data;

/**
 * author jinsq
 *
 * @date 2019/5/6 19:24
 */
@Data
public class VisitUrlVo {

    private String visitUrl;    //访问链接

    private Integer visitNum;  //访问次数

    private Integer copyNum;  // 复制次数

    private String copyRatio;  //复制比率
}
