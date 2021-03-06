package com.example.demo.param;

/**
 *       "kdgs": "huitongkuaidi",
 *       "wuliuzhuangtai": "运输中",    /*物流状态（运输中、已签收、代收、无物流、疑似无物流、待查询、异常件 共7种，本站对于无物流单号会反复核查四遍，“疑似无物流”说明四遍尚未核完）
 *       "tiaoshu":3,
         *"fachushijian":"2017-11-21 00:46:42",
         *"zuixinshijian":"2017-11-21 03:31:07",
         *"zuihouwuliu":"2017-11-21 03:31:07 | 汕头市到件到汕头市【汕头转运中心】",
         *"xiangxiwuliu":"/*太长省略",
         *"chaxunshijian":"2017-11-21 15:46:31",
         *"dingdanhao":"20180411114359001"   /*订单号*/

public class BatchQueryResponse {

     private String kddh;

     private String kdgs;

     private String wuliuzhuangtai;


     private String tiaoshu;

     private String fachushijian;

     private String zuixinshijian;

     private String zuihouwuliu;

     private String xiangxiwuliu;

     private String chaxunshijian;

     private String dingdanhao;


}
