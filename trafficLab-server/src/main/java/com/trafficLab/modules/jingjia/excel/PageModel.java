package com.trafficLab.modules.jingjia.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * author jinsq
 *
 * @date 2019/6/3 20:23
 */
@Data
public class PageModel extends BaseRowModel {

    @ExcelProperty(value = "访问链接",index = 0)
    private String visitUrl;    //访问链接

    @ExcelProperty(value = "访问次数",index = 1)
    private Integer visitNum;  //访问次数

    @ExcelProperty(value = "复制次数",index = 2)
    private Integer copyNum;  // 复制次数

    @ExcelProperty(value = "复制比率",index = 3)
    private String copyRatio;  //复制比率
}
