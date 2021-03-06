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
     */
    @Test
    public void sendThirdRequestByKdgsTest() {

        kddhWlService.queryKddhWlState();

    }

    /**
     * 更新定时任务状态
     */
    @Test
    public void getQueryWlResultTest()  {

        kddhWlService.getQueryWlResult();

    }

    /**
     * 执行批处理任务结果查询，并作相应数据处理
     */
    @Test
    public void executeTask() {

        kddhWlService.executeTask();

    }
}
