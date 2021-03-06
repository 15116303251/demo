package com.example.demo.service;

import com.example.demo.exception.ServiceException;
import org.springframework.stereotype.Service;

@Service
public interface IKddhWlService {

    public void queryKddhWlState(String kdgsNo) throws ServiceException;

    public void getQueryWlResult();
}
