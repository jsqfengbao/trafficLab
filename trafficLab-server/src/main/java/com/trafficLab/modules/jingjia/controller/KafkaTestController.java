package com.trafficLab.modules.jingjia.controller;//package com.trafficLab.modules.jingjia.controller;
//
//import com.trafficLab.common.utils.R;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * author jinsq
// *
// * @date 2019/5/22 10:59
// */
//@RestController
//@RequestMapping("test")
//public class KafkaTestController {
//
//    @Autowired
//    private KafkaTemplate<String,String> kafkaTemplate;
//
//    @RequestMapping(value = "/producer")
//    public R consume(@RequestBody String body) throws IOException {
//        kafkaTemplate.send("result",body);
//        return R.ok();
//    }
//}
