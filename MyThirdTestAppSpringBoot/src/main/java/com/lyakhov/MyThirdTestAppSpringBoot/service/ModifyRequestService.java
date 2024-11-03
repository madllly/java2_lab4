package com.lyakhov.MyThirdTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import com.lyakhov.MyThirdTestAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
