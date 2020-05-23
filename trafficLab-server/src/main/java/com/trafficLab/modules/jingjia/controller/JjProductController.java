package com.trafficLab.modules.jingjia.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import cn.hutool.Hutool;
import com.aliyun.oss.internal.OSSUtils;
import com.trafficLab.modules.jingjia.service.JjProductSysService;
import com.trafficLab.modules.jingjia.utils.JSFileUtils;
import com.trafficLab.modules.oss.cloud.OSSFactory;
import com.trafficLab.modules.oss.entity.SysOssEntity;
import com.trafficLab.modules.oss.service.SysOssService;
import com.trafficLab.modules.sys.controller.AbstractController;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trafficLab.modules.jingjia.entity.JjProductEntity;
import com.trafficLab.modules.jingjia.service.JjProductService;
import com.trafficLab.common.utils.PageUtils;
import com.trafficLab.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-04-19 18:17:18
 */
@RestController
@RequestMapping("jingjia/jjproduct")
@Slf4j
public class JjProductController extends AbstractController {
    @Autowired
    private JjProductService jjProductService;
    @Autowired
    private SysOssService sysOssService;
    @Autowired
    private JjProductSysService jjProductSysService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("jingjia:jjproduct:list")
    public R list(@RequestParam Map<String, Object> params){
        params.put("sysUserId",this.getUserId());
        PageUtils page = jjProductService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("jingjia:jjproduct:info")
    public R info(@PathVariable("id") String id){
		JjProductEntity jjProduct = jjProductService.getById(id);

        return R.ok().put("jjProduct", jjProduct);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("jingjia:jjproduct:save")
    @Transactional
    public R save(@RequestBody JjProductEntity jjProduct){
        Map<String,Object> map = new HashMap<>();
        map.put("sysUserId",getUserId());
        int thisNum = jjProductService.queryCount(map);
        int maxNum  = jjProductSysService.getById(getUserId()).getMaxProductNum();
        if(thisNum >= maxNum){
            return R.error("添加产品已超，请联系管理员");
        }
        String productId = UUID.randomUUID().toString().replace("-","");
        jjProduct.setId(productId);
        jjProduct.setSysUserId(getUserId().intValue());
        try {
            File file = ResourceUtils.getFile("classpath:mapper/jingjia/jsFileModule/jsModule.js");
            String newFilePath = ResourceUtils.getURL("classpath:mapper/jingjia/jsFile/").getPath();
            File newFile = new File(newFilePath+File.separator+productId+".js");
            newFile.createNewFile();
            JSFileUtils.rwJS(newFile,file,productId,jjProduct.getWechat());
            log.info("newFilePath:"+newFile.getPath());
            //上传文件
            FileInputStream fileInputStream = new FileInputStream(newFile);
            MultipartFile multipartFile = new MockMultipartFile("copy"+newFile.getName(),file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(),fileInputStream);
            String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            String url = OSSFactory.build().uploadSuffix(multipartFile.getBytes(), suffix);

            jjProduct.setCodeUrl(url);
            jjProduct.setEnabled(1);
            //保存文件信息
            SysOssEntity ossEntity = new SysOssEntity();
            ossEntity.setUrl(url);
            ossEntity.setCreateDate(new Date());
            sysOssService.save(ossEntity);
            try {
                JSFileUtils.rwJS(newFile,file,productId,jjProduct.getWechat());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }

		jjProductService.save(jjProduct);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("jingjia:jjproduct:update")
    public R update(@RequestBody JjProductEntity jjProduct){
		jjProductService.updateById(jjProduct);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("jingjia:jjproduct:delete")
    public R delete(@RequestBody String[] ids){
		jjProductService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/remainNum")
    @RequiresPermissions("jingjia:jjproduct:remainNum")
    public R getRemainNum(){
        Map<String,Object> map = new HashMap<>();
        map.put("sysUserId",getUserId());
        int thisNum = jjProductService.queryCount(map);
        int maxNum  = jjProductSysService.getById(getUserId()).getMaxProductNum();
        int remainNum = maxNum - thisNum;
        return R.ok().put("remainNum",remainNum);
    }

}
