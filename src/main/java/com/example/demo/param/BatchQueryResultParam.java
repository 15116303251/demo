package com.example.demo.param;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BatchQueryResultParam {

    private Integer appid;

    private String outerid;

    private Integer pageno;

    private String  taskname;
}
