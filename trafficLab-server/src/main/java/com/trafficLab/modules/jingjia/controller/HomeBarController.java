package com.trafficLab.modules.jingjia.controller;

import com.trafficLab.common.utils.PageUtils;
import com.trafficLab.common.utils.R;
import com.trafficLab.modules.jingjia.entity.JjProductEntity;
import com.trafficLab.modules.jingjia.entity.JjProductSysEntity;
import com.trafficLab.modules.jingjia.service.JjProductService;
import com.trafficLab.modules.jingjia.service.JjProductSysService;
import com.trafficLab.modules.sys.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * author jinsq
 *
 * @date 2019/6/18 16:46
 */
@RestController
@RequestMapping("jingjia/navBar")
@Slf4j
public class HomeBarController extends AbstractController {

    @Autowired
    private JjProductSysService jjProductSysService;

    @RequestMapping("/getEndTime")
    public R getEndTime(){
        long sysUserId = getUserId();
        JjProductSysEntity jjProductSysEntity = jjProductSysService.getById(sysUserId);
        return R.ok().put("endTime",jjProductSysEntity.getEndTime());
    }
}
