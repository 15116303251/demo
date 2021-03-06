package com.example.demo.param;



public class ThirdWlQueryParam {

    /**
     * 就传你在猪猪快递云的userid
     */
    private Integer appid;

    /**
     * 你的账号体系里，用户唯一标识，不能是中文，长度必须等于16
     */
    private String outerid;

    /**
     * 支付方式只能传jinbi（是“金币”两个汉字的全拼）。接口调用需要用到金币
     */
    private Integer zffs;
    /**
     * 	快递公司代码
     */
    private String kdgs;

    /**
     * 快递单号集合，以半角逗号间隔，≤1万单；
     * 若发布的是顺丰任务，要求传入对应电话号码的后四位，格式举例：111111||电话号码后4位,222222||电话号码后4位,333333||电话号码后4位
     */
    private String kddhs;

    /**
     * 是否反馈任务名；非必传；默认no，如果需要反馈任务名请传yes
     */
    private String isBackTaskName;

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getOuterid() {
        return outerid;
    }

    public void setOuterid(String outerid) {
        this.outerid = outerid;
    }

    public Integer getZffs() {
        return zffs;
    }

    public void setZffs(Integer zffs) {
        this.zffs = zffs;
    }

    public String getKdgs() {
        return kdgs;
    }

    public void setKdgs(String kdgs) {
        this.kdgs = kdgs;
    }

    public String getKddhs() {
        return kddhs;
    }

    public void setKddhs(String kddhs) {
        this.kddhs = kddhs;
    }

    public String getIsBackTaskName() {
        return isBackTaskName;
    }

    public void setIsBackTaskName(String isBackTaskName) {
        this.isBackTaskName = isBackTaskName;
    }
}
