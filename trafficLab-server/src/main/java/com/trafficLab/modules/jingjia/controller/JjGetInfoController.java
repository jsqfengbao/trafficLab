package com.trafficLab.modules.jingjia.controller;

import java.util.*;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trafficLab.common.excel.ExcelUtil;
import com.trafficLab.common.utils.DateUtil;
import com.trafficLab.modules.jingjia.entity.JjProductEntity;
import com.trafficLab.modules.jingjia.excel.*;
import com.trafficLab.modules.jingjia.service.JjProductService;
import com.trafficLab.modules.job.utils.CallTaoBaoIP;
import com.trafficLab.modules.sys.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.trafficLab.modules.jingjia.entity.JjGetInfoEntity;
import com.trafficLab.modules.jingjia.service.JjGetInfoService;
import com.trafficLab.common.utils.PageUtils;
import com.trafficLab.common.utils.R;

import javax.servlet.http.HttpServletResponse;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-04-18 17:05:37
 */
@RestController
@RequestMapping("jingjia/jjgetinfo")
@Slf4j
public class JjGetInfoController extends AbstractController {
    @Autowired
    private JjGetInfoService jjGetInfoService;
    @Autowired
    private JjProductService jjProductService;
    @Autowired
    HttpServletResponse response;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("jingjia:jjgetinfo:list")
    public R list(@RequestParam Map<String, Object> params) {
        params.put("sysUserId", getUserId());
        PageUtils page = jjGetInfoService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 导出实时数据
     *
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/exportInfo", method = RequestMethod.GET)
    @RequiresPermissions("jingjia:jjgetinfo:export")
    public void export(@RequestParam Map<String, Object> params) throws Exception {
        params.put("sysUserId", getUserId());
        List<JjGetInfoModel> list = jjGetInfoService.exportJjGetInfo(params);
        String fileName = "实时统计" + DateUtil.date2Str(new Date(), "yyyy-MM-dd");
        String sheetName = "实时统计";

        ExcelUtil.writeExcel(response, list, fileName, sheetName, new JjGetInfoModel());
    }

    /**
     * 每日统计
     *
     * @param params
     * @return
     */
    @RequestMapping("/perStatisList")
    @RequiresPermissions("jingjia:perStatis:list")
    public R perStatisList(@RequestParam Map<String, Object> params) {
        params.put("sysUserId", getUserId());
        PageUtils page = jjGetInfoService.selectByCondition(params);
        return R.ok().put("page", page);
    }

    /**
     * 每日统计数据导出
     */
    @RequestMapping(value = "/exportPerStatis", method = RequestMethod.GET)
    @RequiresPermissions("jingjia:jjgetinfo:export")
    public void exportPerStatis(){
        List<PerStatisModel> perStatisModelList = jjGetInfoService.exportPerStatis(getUserId());
        String fileName = "每日统计" + DateUtil.date2Str(new Date(), "yyyy-MM-dd");
        String sheetName = "每日统计";

        ExcelUtil.writeExcel(response, perStatisModelList, fileName, sheetName, new PerStatisModel());
    }

    /**
     * 微信转化统计
     *
     * @param params
     * @return
     */
    @RequestMapping("/wechatList")
    @RequiresPermissions("jingjia:wechat:list")
    public R wechatList(@RequestParam Map<String, Object> params) {
        params.put("sysUserId", getUserId());
        PageUtils page = jjGetInfoService.selectWechat(params);
        return R.ok().put("page", page);
    }

    /**
     * 微信转化统计数据导出
     */
    @RequestMapping(value = "/exportWechat", method = RequestMethod.GET)
    @RequiresPermissions("jingjia:jjgetinfo:export")
    public void exportWechat(@RequestParam Map<String,Object> params){
        params.put("sysUserId",getUserId());
        List<WeChatModel> weChatModelList = jjGetInfoService.exportWechat(params);
        String fileName = "微信转化统计" + DateUtil.date2Str(new Date(), "yyyy-MM-dd");
        String sheetName = "微信转化统计";

        ExcelUtil.writeExcel(response, weChatModelList, fileName, sheetName, new WeChatModel());
    }

    /**
     * 页面转化统计
     *
     * @param params
     * @return
     */
    @RequestMapping("/visitUrlList")
    @RequiresPermissions("jingjia:vistUrl:list")
    public R visitUrlList(@RequestParam Map<String, Object> params) {
        params.put("sysUserId", getUserId());
        PageUtils page = jjGetInfoService.selectVisitUrl(params);
        return R.ok().put("page", page);
    }

    /**
     * 页面转化统计数据导出
     */
    @RequestMapping(value = "/exportPage", method = RequestMethod.GET)
    @RequiresPermissions("jingjia:jjgetinfo:export")
    public void exportPage(@RequestParam Map<String,Object> params){
        params.put("sysUserId",getUserId());
        List<PageModel> weChatModelList = jjGetInfoService.exportPage(params);
        String fileName = "页面转化统计" + DateUtil.date2Str(new Date(), "yyyy-MM-dd");
        String sheetName = "页面转化统计";

        ExcelUtil.writeExcel(response, weChatModelList, fileName, sheetName, new PageModel());
    }

    /**
     * 关键词转化统计
     *
     * @param params
     * @return
     */
    @RequestMapping("/keywordList")
    @RequiresPermissions("jingjia:keyword:list")
    public R keywordList(@RequestParam Map<String, Object> params) {
        params.put("sysUserId", getUserId());
        PageUtils page = jjGetInfoService.selectKeyword(params);
        return R.ok().put("page", page);
    }

    /**
     * 页面转化统计数据导出
     */
    @RequestMapping(value = "/exportKeyword", method = RequestMethod.GET)
    @RequiresPermissions("jingjia:jjgetinfo:export")
    public void exportKeyword(@RequestParam Map<String,Object> params){
        params.put("sysUserId",getUserId());
        List<KeywordModel> keywordModels = jjGetInfoService.exportKeyword(params);
        String fileName = "页面转化统计" + DateUtil.date2Str(new Date(), "yyyy-MM-dd");
        String sheetName = "页面转化统计";

        ExcelUtil.writeExcel(response, keywordModels, fileName, sheetName, new KeywordModel());
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("jingjia:jjgetinfo:info")
    public R info(@PathVariable("id") Integer id) {
        JjGetInfoEntity jjGetInfo = jjGetInfoService.getById(id);

        return R.ok().put("jjGetInfo", jjGetInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody(required = false) String reqStr) {
        log.info("reqStr:" + reqStr);
        JjGetInfoEntity jjGetInfo = JSONUtil.toBean(reqStr, JjGetInfoEntity.class);
        JjProductEntity panduanEntity = jjProductService.getById(jjGetInfo.getProductId());
        if(panduanEntity.getEnabled() == 0) {
            return R.ok();
        }
        jjGetInfo = CallTaoBaoIP.callAndUpdateArea(jjGetInfo);

        JjProductEntity jjProductEntity = jjProductService.getById(jjGetInfo.getProductId());
        jjGetInfo.setEnabled(1);
        if ("".equals(jjGetInfo.getWechat().trim()) || jjGetInfo.getWechat().trim().length() <= 0) {
            jjGetInfo.setWechat(jjProductEntity.getWechat());
        }
        jjGetInfo.setSysUserId(jjProductEntity.getSysUserId());
        jjGetInfoService.save(jjGetInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("jingjia:jjgetinfo:update")
    public R update(@RequestBody JjGetInfoEntity jjGetInfo) {
        jjGetInfoService.updateById(jjGetInfo);

        return R.ok();
    }

    /**
     * 修改访问方式
     *
     * @param jjGetInfo
     * @return
     */
    @RequestMapping("/updateInfo")
    public R updateInfo(@RequestBody JjGetInfoEntity jjGetInfo) {
        JjProductEntity jjProductEntity = jjProductService.getById(jjGetInfo.getProductId());
        if(jjProductEntity.getEnabled() == 0) {
            return R.ok();
        }
        String nowDay = DateUtil.date2Str(new Date(), "yyyy-MM-dd");
        jjGetInfoService.update(jjGetInfo, new QueryWrapper<JjGetInfoEntity>() {
            @Override
            public String getSqlSegment() {
                return " where visit_ip = '" + jjGetInfo.getVisitIp() + "' and product_id = '" + jjGetInfo.getProductId() + "' and create_time like '" + nowDay + "%' ORDER BY create_time DESC LIMIT 1";
            }
        });
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("jingjia:jjgetinfo:delete")
    public R delete(@RequestBody Integer[] ids) {
        jjGetInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    private List<JjGetInfoModel> getList() {
        List<JjGetInfoModel> list = new ArrayList<>();
        JjGetInfoModel model1 = new JjGetInfoModel();
        model1.setWechat("howie");
        model1.setVisitUrl("19");
        model1.setVisitIp("123456789");
        model1.setSystemName("123456789@gmail.com");
        list.add(model1);
        return list;
    }

}
