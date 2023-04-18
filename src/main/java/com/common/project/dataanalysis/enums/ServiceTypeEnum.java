package com.common.project.dataanalysis.enums;


public enum ServiceTypeEnum {
    //es解析器
    elasticsearchParser("elasticsearch", "esClientHandler","esQueryHandler", "esOutputHandler", "es解析器"),
    //redis解析器
    reidsParser("redis", "redisClientHandler","redisQueryHandler","redisOutputHandler","redis解析器");
    //类型
    String type;
    //客户端服务处理名称
    String clientHandlerName;
    //查询服务处理名称
    String queryHandlerName;
    //数据输出处理名称
    String outPutHandlerName;
    //描述
    String desc;

    ServiceTypeEnum(String type, String clientHandlerName, String queryHandlerName, String outPutHandlerName, String desc) {
        this.type = type;
        this.clientHandlerName = clientHandlerName;
        this.queryHandlerName = queryHandlerName;
        this.outPutHandlerName = outPutHandlerName;
        this.desc = desc;
    }

    public static ServiceTypeEnum getByValue(String type) {
        for (ServiceTypeEnum a : ServiceTypeEnum.values()) {
            if (a.getType().equals(type)) {
                return a;
            }
        }
        return null;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClientHandlerName() {
        return clientHandlerName;
    }

    public String getQueryHandlerName() {
        return queryHandlerName;
    }

    public void setQueryHandlerName(String queryHandlerName) {
        this.queryHandlerName = queryHandlerName;
    }

    public String getOutPutHandlerName() {
        return outPutHandlerName;
    }

    public void setOutPutHandlerName(String outPutHandlerName) {
        this.outPutHandlerName = outPutHandlerName;
    }

    public void setClientHandlerName(String clientHandlerName) {
        this.clientHandlerName = clientHandlerName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



}
