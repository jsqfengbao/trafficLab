package com.trafficLab.modules.jingjia.enums;

/**
 * author jinsq
 *
 * @date 2019/6/3 9:44
 */

import java.util.LinkedHashMap;
import java.util.Map;

@Description("标准数据类型")
public enum OperateTypeEnum {
    /**
     * 2-复制
     **/
    Copy("2", "复制"),
    /**
     * 1-仅访问
     **/
    Visit("1", "仅访问");

    // 成员变量R
    private String value; // value
    private String desc; // 描述

    // 构造方法
    private OperateTypeEnum(String val, String desc) {
        this.value = val;
        this.desc = desc;
    }

    public static Map<String, String> getDescValueMap() {
        OperateTypeEnum[] loops = OperateTypeEnum.values();
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (OperateTypeEnum loop : loops) {
            map.put(loop.getDesc(), loop.getValue());
        }
        return map;
    }

    /**
     * 通过value获取对象
     */
    public static OperateTypeEnum getMessageType(String val) {
        for (OperateTypeEnum c : OperateTypeEnum.values()) {
            if (c.getValue().equals(val)) {
                return c;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}