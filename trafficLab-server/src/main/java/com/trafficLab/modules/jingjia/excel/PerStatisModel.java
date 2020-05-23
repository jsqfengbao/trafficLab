package com.trafficLab.modules.jingjia.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * author jinsq
 *
 * @date 2019/6/3 15:37
 */
@Data
public class PerStatisModel extends BaseRowModel {

    @ExcelProperty(value = "日期",index = 0)
    private String day;       //日期

    @ExcelProperty(value = "访问次数",index = 1)
    private Integer visitNum; //访问次数

    @ExcelProperty(value = "复制次数",index = 2)
    private Integer copyNum;  //复制次数

    @ExcelProperty(value = "复制比率",index = 3)
    private String copyRatio;  //复制比率
}
