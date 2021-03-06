package com.example.demo.service;

import com.example.demo.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public interface IKddhWlService {

    /**
     * 发送批处理请求
     *
     * @throws ServiceException
     */
    public void queryKddhWlState() throws ServiceException;

    /**
     * 得到进度达到100%的taskName
     */
    public void getQueryWlResult();

    /**
     * 执行拉取，更新数据
     */
    public void executeTask();
}
