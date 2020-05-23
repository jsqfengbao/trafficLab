package com.trafficLab.modules.jingjia.dao.provider;

import com.qiniu.util.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * author jinsq
 *
 * @date 2019/5/6 16:37
 */
public class JjGetInfoProvider {

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    public String queryJjGetInfo(Map<String,Object> params){
        SQL sql = new SQL().SELECT(" * from jj_get_info");
        sql = whereCondition(params,sql,"select");
        return sql.toString();
    }

    /**
     * 导出使用；不分页
     * @param params
     * @return
     */
    public String queryJjGetInfoByNoPage(Map<String,Object> params){
        SQL sql = new SQL().SELECT(" * from jj_get_info");
        sql = whereCondition(params,sql,"nopage");
        return sql.toString();
    }

    /**
     * 根据条件计数
     * @param params
     * @return
     */
    public String queryCount(Map<String,Object> params){
        SQL sql = new SQL().SELECT("count(1) from jj_get_info");
        sql  = whereCondition(params,sql,"count");
        return sql.toString();
    }

    public SQL whereCondition(Map<String,Object> params,SQL sql,String type){
        int current = Integer.parseInt((String) params.get("page"));
        int size = Integer.parseInt((String) params.get("limit"));
        String wechat = (String) params.get("wechat");
        String operateType = (String) params.get("operateType");
        String productId = (String) params.get("productId");
        String visitUrl = (String) params.get("visitUrl");
        String visitIp = (String) params.get("visitIp");
        long sysUserId = (long) params.get("sysUserId");

        sql.WHERE("sys_user_id = #{sysUserId}");

        if(!StringUtils.isNullOrEmpty(wechat)){
            sql.WHERE("wechat = #{wechat}");
        }
        if(!StringUtils.isNullOrEmpty(operateType)){
            sql.WHERE("operate_type = #{operateType}");
        }
        if(!StringUtils.isNullOrEmpty(productId)){
            sql.WHERE("product_id = #{productId}");
        }
        if(!StringUtils.isNullOrEmpty(visitUrl)){
            sql.WHERE("visit_url like '%"+visitUrl+"'%");
        }
        if(!StringUtils.isNullOrEmpty(visitIp)){
            sql.WHERE("visit_ip = #{visitIp}");
        }
        sql.WHERE("enabled = 1");
        if("count".equals(type) || "nopage".equals(type)){
            sql.ORDER_BY("create_time desc");
        }else if("select".equals(type)){
            sql.ORDER_BY("create_time desc limit "+(current-1)*size+","+size);
        }
        return sql;
    }

    /**
     * 访问页面连接sql
     * @param params
     * @return
     */
    public String queryVisitUrlVo(Map<String,Object> params) {
        SQL sql = new SQL().SELECT("substring_index(visit_url,'/',3) as visitUrl,count(id) as visitNum ,sum(operate_type =2) as copyNum from jj_get_info");
        sql = whereConditionByName(params,sql,"visitUrl","");
        return sql.toString();
    }

    public String queryVisitUrlNoPage(Map<String,Object> params){
        SQL sql = new SQL().SELECT("substring_index(visit_url,'/',3) as visitUrl,count(id) as visitNum ,sum(operate_type =2) as copyNum from jj_get_info");
        sql = whereConditionByName(params,sql,"visitUrl","nopage");
        return sql.toString();
    }

    public SQL whereConditionByName(Map<String,Object> params,SQL sql,String groupByName,String noPageType){
        int current = Integer.parseInt((String) params.get("page"));
        int size = Integer.parseInt((String) params.get("limit"));
        long sysUserId = (long) params.get("sysUserId");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        String sortType = (String) params.get("sortType");

        sql.WHERE("sys_user_id = #{sysUserId}");
        sql.WHERE("enabled = 1");
        if(!StringUtils.isNullOrEmpty(startTime) && !StringUtils.isNullOrEmpty(endTime)){
            sql.WHERE(" create_time >= #{startTime} and create_time <= #{endTime} ");
        }
        sql.GROUP_BY(groupByName);

        if(!StringUtils.isNullOrEmpty(sortType) && !"nopage".equals(noPageType)){
            sql.ORDER_BY(sortType+" desc limit "+(current-1)*size+","+size);
        }else if(!"nopage".equals(noPageType)){
            sql.ORDER_BY("visitNum desc limit "+(current-1)*size+","+size);
        }

        return sql;
    }

    public String queryCountByVisitUrl(Map<String,Object> params){
        StringBuilder sqlBulider = new StringBuilder();
        sqlBulider.append("select count(1) from (select substring_index(visit_url,'/',3) as visitUrl,count(id) as visitNum ,sum(operate_type =2) as copyNum from jj_get_info");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        long sysUserId = (long) params.get("sysUserId");
        sqlBulider.append(" where sys_user_id = #{sysUserId} and enabled = 1 ");
        if(!StringUtils.isNullOrEmpty(startTime) && !StringUtils.isNullOrEmpty(endTime)){
            sqlBulider.append("and create_time >= #{startTime} and create_time <= #{endTime}");
        }
        sqlBulider.append("  group by visitUrl ) as tableName");

        return sqlBulider.toString();
    }

    /**
     * 微信号转化分析
     * @param params
     * @return
     */
    public String queryWechatVo (Map<String,Object> params) {
        SQL sql = new SQL().SELECT("wechat,count(id) as visitNum ,sum(operate_type =2) as copyNum from jj_get_info");
        sql = whereConditionByName(params,sql,"wechat","");
        return sql.toString();
    }

    /**
     * 导出微信号不分页
     * @param params
     * @return
     */
    public String queryWechatNoPage(Map<String,Object> params){
        SQL sql = new SQL().SELECT("wechat,count(id) as visitNum ,sum(operate_type =2) as copyNum from jj_get_info");
        sql = whereConditionByName(params,sql,"wechat","nopage");
        return sql.toString();
    }

    public String queryCountByWechat(Map<String,Object> params){
        StringBuilder sqlBulider = new StringBuilder();
        sqlBulider.append("select count(1) from (select wechat from jj_get_info");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        long sysUserId = (long) params.get("sysUserId");
        sqlBulider.append(" where sys_user_id = #{sysUserId} and enabled = 1 ");
        if(!StringUtils.isNullOrEmpty(startTime) && !StringUtils.isNullOrEmpty(endTime)){
            sqlBulider.append("and create_time >= #{startTime} and create_time <= #{endTime}");
        }
        sqlBulider.append(" group by wechat) as tableName");

        return sqlBulider.toString();
    }

    public String queryKeywordVo (Map<String,Object> params){
        SQL sql = new SQL().SELECT("keyword,count(id) as visitNum ,sum(operate_type =2) as copyNum from jj_get_info");
        sql = whereConditionByName(params,sql,"keyword","");
        return sql.toString();
    }

    public String queryKeywordNoPage(Map<String,Object> params){
        SQL sql = new SQL().SELECT("keyword,count(id) as visitNum ,sum(operate_type =2) as copyNum from jj_get_info");
        sql = whereConditionByName(params,sql,"keyword","nopage");
        return sql.toString();
    }

    public String queryCountByKeyword(Map<String,Object> params){
        StringBuilder sqlBulider = new StringBuilder();
        sqlBulider.append("select count(1) from (select keyword from jj_get_info");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        long sysUserId = (long) params.get("sysUserId");
        sqlBulider.append(" where sys_user_id = #{sysUserId} and enabled = 1 ");
        if(!StringUtils.isNullOrEmpty(startTime) && !StringUtils.isNullOrEmpty(endTime)){
            sqlBulider.append("and create_time >= #{startTime} and create_time <= #{endTime}");
        }
        sqlBulider.append(" group by keyword) as tableName");

        return sqlBulider.toString();
    }
}
