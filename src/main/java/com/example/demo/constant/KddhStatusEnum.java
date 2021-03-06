package com.example.demo.constant;

public enum KddhStatusEnum {
    EXCEPTION_KD(6,"异常件") ,
    NOT_WL_KD(3,"无物流") ;


    private Integer statusCode;
    private String stausDesc;

    KddhStatusEnum(Integer statusCode, String stausDesc) {
        this.statusCode = statusCode;
        this.stausDesc = stausDesc;
    }


    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStausDesc() {
        return stausDesc;
    }

    // 通过编号得到描述
    public static String getDescByCode(Integer statusCode) {
        for (KddhStatusEnum k : KddhStatusEnum.values()) {
            if (k.getStatusCode() == statusCode) {
                return k.getStausDesc();
            }
        }
        return null;
    }

    // 通过描述得到编号
    public static Integer getColorByIndex(String desc) {
        for (KddhStatusEnum k : KddhStatusEnum.values()) {
            if (k.getStausDesc().equals(desc)) {
                return k.getStatusCode();
            }
        }
        return null;
    }

}
