package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.ThirdConfig;
import com.example.demo.dao.KdgsMapper;
import com.example.demo.dao.NewKddhsMapper;
import com.example.demo.dao.model.Kdgs;
import com.example.demo.dao.model.NewKddhs;
import com.example.demo.dao.model.NewKddhsExample;
import com.example.demo.exception.ServiceException;
import com.example.demo.param.BatchQueryResultParam;
import com.example.demo.param.ThirdWlQueryParam;
import com.example.demo.service.IKddhWlService;
import com.example.demo.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.List;
import static com.example.demo.constant.Constant.*;
import static com.example.demo.constant.KddhStatusEnum.EXCEPTION_KD;
import static com.example.demo.constant.KddhStatusEnum.NOT_WL_KD;

/**
 * 0、新建一个快递公司表，通过快递公司进行轮询
 * 1、新建一个中间表new_kddhs，用于存储新进的快递单号数据
 * 2、向第三方发送的过滤掉 无物流的，以及异常的，这些数据通过定时任务提醒下相关人员
 * 3、返回的任务名字存储到中间表new_kddhs中
 * 4、再通过定时任务获取结果，并更新正的快递单表的状态，若状态为已签收，则将记录从临时表删除。
 */

@Service
@Slf4j
public class KddhWlServiceImpl implements IKddhWlService {

    @Autowired
    private NewKddhsMapper newKddhsMapper;
    @Autowired
    private KdgsMapper kdgsMapper;
    @Autowired
    private ThirdConfig thirdConfig;




    /**
     * @param kdgsNo
     * @throws ServiceException
     */
    @Override
    public void queryKddhWlState(String kdgsNo) throws ServiceException {

//        List<Kdgs>kdgs=null;
//        if(!ObjectUtils.isEmpty(kdgsNo)){
//            kdgs= new ArrayList<>();
//            Kdgs k=new Kdgs();
//            k.setKdgsNo(kdgsNo);
//            kdgs.add(k);
//        }

        //1、获取所有的快递公司
        List<Kdgs>kdgs= kdgsMapper.getAllKdgs();

        //2、准备调用第三方接口的参数
        if(!ObjectUtils.isEmpty(kdgs)){

            //循环所有的快递公司进行状态查询，可在这里开启多个线程
            for (Kdgs k:kdgs) {
                this.sendThirdRequestByKdgs(k);
            }

        }
    }

    @Override
    public void getQueryWlResult() {

        //1、schedule_task 查询所有的taskName

        //2、根据任务名进行相应的循环
        List<String> taskNameList=List.of("task1","task2");

        if(!ObjectUtils.isEmpty(taskNameList)){
            for (String taskName:taskNameList) {//可以采用多线程加快处理，但需考虑资源

                try {
                    this.dealThirdResult(this.getResult(1,taskName),1,taskName);
                }catch (Exception e){
                    log.error("调用第三方查询结果，异常e:{}",e.getMessage());
                }

            }
        }

    }

    /**
     * 构造查询结果循环体
     * @param pageNo
     * @param taskName
     * @return
     */
    private String getResult(Integer pageNo,String taskName){

        //创建请求参数
        BatchQueryResultParam param = BatchQueryResultParam.builder()
                .appid(Integer.parseInt(thirdConfig.getAppId()))
                .outerid(thirdConfig.getOuterId()).pageno(pageNo).taskname(taskName).build();

        String content = "拼接相应的参数";
        return HttpUtils.doPost(CREATE_BATCH_QUERY_RESULT, content);
    }


    /**
     * 对第三方返回的结果进行解析并作相应的处理
     * @param result
     */
    private void dealThirdResult(String result,Integer pageNo,String taskName){
        if(!ObjectUtils.isEmpty(result)){

            JSONObject resultJson= JSONObject.parseObject(result);

            Integer code =resultJson.getIntValue("code");
            if(ObjectUtils.isEmpty(code) || code.intValue()!=1){
                log.error("调用第三方请求返回的结束码不属于正常的返回：{}",resultJson);
            }

            JSONObject msgJson=resultJson.getJSONObject("msg");

            if(!ObjectUtils.isEmpty(msgJson)) {
                Integer jinDu = msgJson.getIntValue("jindu");
                if (ObjectUtils.isEmpty(jinDu) || jinDu < 100) {
                    return;
                }

//                int maxPageSize=msgJson.getIntValue("totalpage");
//                //若是进度100，此时可以相应的数据库更新
//                //处理当前页的100条数据
//                List<BatchQueryResultParam> perOrderStatusArr= (List<BatchQueryResultParam>) msgJson.getObject("list",BatchQueryResultParam.class);
//                for (Object perOrderStatus:perOrderStatusArr) {
//
//                    //更新状态到真正的快递号表
//                    //更新new_kddhs 中的状态，若状态为疑似无物流，表中count+1，若是为已签收则删除该条记录
//                    //异常单以及无物流单号提醒相应人员处理
//                }
//
//
//                if(pageNo+1<=maxPageSize){//todo 需考虑栈深度
//                    this.dealThirdResult(this.getResult(pageNo+1,taskName),pageNo+1,taskName);
//                }

                //todo 写到这里发现可能会出现线程过多，或者内存、cpu出现问题
                //todo 第一想法：将此时的返回进度为100% 的任务，任务名称放到数据库，哪个100%了，就执行哪个。
                //todo 第二想法：将此已经100% 的任务，丢到MQ ，设置不一样的延迟时间，进行分开执行。

            }
        }

    }



    /**
     * 根据快递公司进行调用第三方
     * @param k
     * @throws ServiceException
     */
    private void sendThirdRequestByKdgs(Kdgs k) throws ServiceException {

        //不查询无物流与异常件，都说明需人工处理,处理完后将状态修改为默认就好。
        NewKddhsExample example=new NewKddhsExample();
        example.createCriteria().andKdgsEqualTo(k.getKdgsNo()).andStatusNotIn(List.of(NOT_WL_KD.getStatusCode(),
                EXCEPTION_KD.getStatusCode()));
        List<NewKddhs> list=newKddhsMapper.selectByExample(example);


        //当前快递公司存在快递单号
        while(!ObjectUtils.isEmpty(list)){

            //金币数判断，若是采用多线进行的化，金币数需要加锁
            int goldAmount=10;
            if(list.size()>goldAmount){
                throw new ServiceException("调用第三方接口查询快递单号状态，金币数量不足，请充值！");
            }

            StringBuffer stringBuffer=new StringBuffer();
            //当前快递单量是否大于10000
            if(list.size()>AMOUNT){
                this.generateKddhStr(list,stringBuffer,k);
                list=list.subList(AMOUNT+1,list.size());
            }else{
                this.generateKddhStr(list,stringBuffer,k);
            }

            try {
                //3、调用第三方公司进行批量任务创建
                ThirdWlQueryParam param = this.generateQueryParam(k, stringBuffer);
                //todo 拼接成&方式参数
                String content="拼接成&方式参数";
                String taskName=HttpUtils.doPost(CREATE_BATCH_QUERY, content);

                //4、存储名字到数据新的专门一张任务表schedule_task
            }catch (Exception e){
                list=null;//防止出现异常导致后面的调用停止
            }
        }
    }


    /**
     * 生成请求参数快递单号字符串
     * @param list
     * @param stringBuffer
     * @param k
     */
    private void generateKddhStr(List<NewKddhs> list, StringBuffer stringBuffer,Kdgs k){
        if(k.getKdgsNo().equalsIgnoreCase(SHUN_FENG)){
            list.stream().map(s->s.getKddhs()).forEach(i->stringBuffer.append(i).append(","));
        }else{
            list.stream().forEach(i->{
                stringBuffer.append(i.getKddhs()).append("||").append(i.getPhone()).append(",");
            });
        }
    }

    /**
     * 生成请求url参数后面字符串
     * @param k
     * @param stringBuffer
     * @return
     */
    private ThirdWlQueryParam generateQueryParam(Kdgs k,StringBuffer stringBuffer){
        ThirdWlQueryParam param=new ThirdWlQueryParam();
        param.setAppid(Integer.parseInt(thirdConfig.getAppId()));
        param.setOuterid(thirdConfig.getOuterId());
        param.setKdgs(k.getKdgsNo());
        param.setIsBackTaskName(YES);
        param.setKddhs(stringBuffer.toString());
        return param;
    }



    public static void main(String[] args) {

        StringBuffer stringBuffer=new StringBuffer();

        List<String>list=List.of("1","2","3");
        list.stream().forEach(i->{
            stringBuffer.append(i).append("||").append("手机号").append(",");
        });

        System.out.println(stringBuffer.toString());

//        list=list.subList(2,list.size());
//        System.out.println(list);

    }
}
