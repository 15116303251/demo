package com.example.demo;

import com.example.demo.service.IKddhWlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KddhWlServiceImplTest {


    @Autowired
    IKddhWlService kddhWlService;


    /**
     * 调用第三方创建批处理任务
     *
     * 1、新建表new_kddhs，用于存储那些还没有被签收的快递单
     * 2、扫描表new_kddhs，其中不是无物流与异常单的记录，调用第三方查询它们的记录，异常单等人工修复后，再修改状态为默认-1 ，下次会重新向第三方查询它们的状态
     */
    @Test
    public void sendThirdRequestByKdgsTest() {

        kddhWlService.queryKddhWlState("shunfeng");

    }

    /**
     * 调用第三方根据taskName 查询改任务下快递单下相应的状态
     * 定时任务10分钟执行一次
     * 1、从schedule_task（taskName,由上面那个批处理任务调用第三方获取） 获取对应的taskName,若是任务的查询返回的结果为进度100%，则将该表status改为可执行状态
     * 2、5分钟扫描表中达到100%的任务，进行从第三方获取数据
     * 3、将快递单号状态为 0:运输中、1:已签收、2:代收的更新到原来的快递单表
     * 4、将第三方推送的状态更新到new_kddhs，并将状态为无物流与异常单的记录信息推送给相应人员，进行问题排查
     */

    @Test
    public void getQueryWlResultTest()  {

        kddhWlService.getQueryWlResult();

    }
}
