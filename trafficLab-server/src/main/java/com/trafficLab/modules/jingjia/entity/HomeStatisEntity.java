package com.trafficLab.modules.jingjia.entity;

import lombok.Data;

import java.util.Date;

/**
 * author jinsq
 *
 * @date 2019/6/25 17:58
 */
@Data
public class HomeStatisEntity {

    private Integer sysUserId;

    private Integer operateType;

    private String region;

    private String isp;

    private String systemName;

    private Date createTime;
}
