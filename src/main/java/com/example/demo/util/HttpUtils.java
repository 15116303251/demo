package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.example.demo.param.ThirdWlQueryParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpUtils {


    public static String doPost(String url, String content) {
        log.info("请求的路径为：" + url + "请求的参数为" + content);
        try {
            String responseBody="";
            log.info("请求的路径为：" + url + "请求的参数为" + JSON.toJSONString(content)+"请求返回的结果如下："+JSON.toJSONString(responseBody));

        }catch (Exception e){

            log.error("调用第三方异常如下：{}",e.getMessage());
        }

        return null;
    }

    public static String doGet(String url,String param) {

        return null;
    }

}
