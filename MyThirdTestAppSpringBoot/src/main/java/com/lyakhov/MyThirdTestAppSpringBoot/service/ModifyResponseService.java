package com.lyakhov.MyThirdTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import com.lyakhov.MyThirdTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
