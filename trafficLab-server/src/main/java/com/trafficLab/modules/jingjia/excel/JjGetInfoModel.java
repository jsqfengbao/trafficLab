package com.trafficLab.modules.jingjia.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author jinsq
 *
 * @date 2019/6/2 16:56
 */
@Data
public class JjGetInfoModel extends BaseRowModel {

    @ExcelProperty(value="微信号",index=0)
    private String wechat;

    @ExcelProperty(value="操作类型",index=1)
    private String operateType;

    @ExcelProperty(value="关键字",index=2)
    private String keyword;

    @ExcelProperty(value="访问URL",index=3)
    private String visitUrl;

    @ExcelProperty(value="访客IP",index=4)
    private String visitIp;

    @ExcelProperty(value="运营商",index=5)
    private String isp;

    @ExcelProperty(value="操作系统类型",index=6)
    private String systemName;

    @ExcelProperty(value="浏览器名称",index=7)
    private String appName;

    @ExcelProperty(value="访客地址",index=8)
    private String visitAddress;

    @ExcelProperty(value="访问时间",index=9)
    private String createTime;
}
