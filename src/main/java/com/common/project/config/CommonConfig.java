package com.common.project.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 通用配置
 *
 * @Author wh
 * @Date 2023/6/7 17:09
 * @Version 1.0
 **/
@Data
@Configuration
public class CommonConfig  implements InitializingBean {
    //通知execl表格头省份名称
    @Value("${province.name:}")
    public   String defaultHouseIds;
    //企业微信机器人地址
    @Value("${qywx.robot.address:}")
    public  String robotAddress;
    //联通或者移动路径地址
    @Value("${operator.route:}")
    public  String operator;
    //机房ip
    @Value("${idc.ds.host.ip:}")
    public  String ips;
    //机房ids
    @Value("${idc.ds.host.id:}")
    public  String ids;
    //单机房设置为1；多机房1台设置为1,其他设置为0
    @Value("${multi.data.center:}")
    public  String center;
    //生成excl跟图片存放位置
    @Value("${execl.png.path:}")
    public  String execlPngPath;

    //数据库url
    @Value("${spring.datasource.url:}")
    public  String datasourceUrl;
    //mybatis驱动
    @Value("${spring.datasource.driver-class-name:}")
    public  String driverClassName;
    //用户名
    @Value("${spring.datasource.username:}")
    public  String dataSourceUsername;
    //密码
    @Value("${spring.datasource.password:}")
    public  String dataSourcePassword;



    public  static String defaultHouseIdsConst;
    public static String robotAddressConst;
    public static String operatorConst;
    public static String ipsConst;
    public static String idsConst;
    public static String centerConst;
    public static String execlPngPathConst;
    public static String datasourceUrlConst;
    public static String driverClassNameConst;
    public static String dataSourceUsernameConst;
    public static String dataSourcePasswordConst;

    @Override
    public void afterPropertiesSet() throws Exception {
        defaultHouseIdsConst=this.defaultHouseIds;
        robotAddressConst=this.robotAddress;
        operatorConst=this.operator;
        ipsConst=this.ips;
        idsConst=this.ids;
        centerConst=this.center;
        execlPngPathConst=this.execlPngPath;
        datasourceUrlConst=this.datasourceUrl;
        driverClassNameConst=this.driverClassName;
        dataSourceUsernameConst=this.dataSourceUsername;
        dataSourcePasswordConst=this.dataSourcePassword;
    }
}
