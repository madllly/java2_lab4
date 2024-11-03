package com.lyakhov.MyThirdTestAppSpringBoot.service;

import com.lyakhov.MyThirdTestAppSpringBoot.model.Response;
import com.lyakhov.MyThirdTestAppSpringBoot.util.DateTimeUtil;

import java.util.Date;

public class ModifySystemTimeResponseService implements ModifyResponseService{
    @Override
    public Response modify(Response response) {
        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
        return response;
    }
}
