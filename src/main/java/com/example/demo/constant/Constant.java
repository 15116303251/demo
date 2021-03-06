package com.example.demo.constant;

public interface Constant {

    /**
     * 创建批量订单查询的URL，一次最多10000 条
     */
    Integer AMOUNT=10000;

    /**
     * 快递公司顺丰
     */
    String SHUN_FENG="shunfeng";

    /**
     * 创建批量订单查询的URL，返回任务名
     */
    String YES="yes";


    /**
     * 创建批量订单查询的URL
     */
    String CREATE_BATCH_QUERY="http://yun.zhuzhufanli.com/mini/create/";

    /**
     * 创建批量订单结果查询的URL
     */
    String CREATE_BATCH_QUERY_RESULT="http://yun.zhuzhufanli.com/mini/select/";
}
