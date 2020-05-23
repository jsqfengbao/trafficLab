package com.trafficLab.modules.jingjia.controller;

import java.util.Arrays;
import java.util.Map;

import com.trafficLab.modules.sys.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trafficLab.modules.jingjia.entity.JjProductSysEntity;
import com.trafficLab.modules.jingjia.service.JjProductSysService;
import com.trafficLab.common.utils.PageUtils;
import com.trafficLab.common.utils.R;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-05-13 14:30:38
 */
@RestController
@RequestMapping("jingjia/jjproductsys")
public class JjProductSysController {
    @Autowired
    private JjProductSysService jjProductSysService;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("jingjia:jjproductsys:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = jjProductSysService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("jingjia:jjproductsys:info")
    public R info(@PathVariable("id") Integer id){
		JjProductSysEntity jjProductSys = jjProductSysService.getById(id);

        return R.ok().put("jjProductSys", jjProductSys);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("jingjia:jjproductsys:save")
    public R save(@RequestBody JjProductSysEntity jjProductSys){
        jjProductSys.setSysUserName(sysUserService.getById(jjProductSys.getSysUserId()).getUsername());
		jjProductSysService.save(jjProductSys);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("jingjia:jjproductsys:update")
    public R update(@RequestBody JjProductSysEntity jjProductSys){
		jjProductSysService.updateById(jjProductSys);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("jingjia:jjproductsys:delete")
    public R delete(@RequestBody Integer[] ids){
		jjProductSysService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
