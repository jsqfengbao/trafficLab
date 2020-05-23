package com.trafficLab.modules.jingjia.controller;

import com.trafficLab.common.utils.R;
import com.trafficLab.modules.jingjia.service.HomeStatisService;
import com.trafficLab.modules.sys.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author jinsq
 *
 * @date 2019/5/29 10:43
 */
@RestController
@RequestMapping("jingjia/homeStatis")
@Slf4j
public class HomeStatisController extends AbstractController {

    @Autowired
    private HomeStatisService homeStatisService;

    @RequestMapping(value = "/homeStatis")
    public R queryHomeStatis(){
        return R.ok().put("homeData",homeStatisService.getHomeStatis(getUserId()));
    }
}
