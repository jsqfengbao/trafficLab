package com.trafficLab.modules.jingjia.vo;

import lombok.Data;

/**
 * author jinsq
 *
 * @date 2019/5/6 18:39
 */
@Data
public class WechatVo {

    private String wechat;    //微信号

    private Integer visitNum;  //访问次数

    private Integer copyNum;  // 复制次数

    private String copyRatio;  //复制比率
}
