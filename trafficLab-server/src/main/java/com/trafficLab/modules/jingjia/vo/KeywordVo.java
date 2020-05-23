package com.trafficLab.modules.jingjia.vo;

import lombok.Data;

/**
 * author jinsq
 *
 * @date 2019/5/6 20:04
 */
@Data
public class KeywordVo {

    private String keyword;    //关键词

    private Integer visitNum;  //访问次数

    private Integer copyNum;  // 复制次数

    private String copyRatio;  //复制比率
}
