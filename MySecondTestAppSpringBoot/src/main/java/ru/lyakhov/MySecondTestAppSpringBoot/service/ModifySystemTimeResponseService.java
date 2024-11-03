package ru.lyakhov.MySecondTestAppSpringBoot.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.lyakhov.MySecondTestAppSpringBoot.model.Response;
import ru.lyakhov.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.util.Date;


@Service("ModifySystemTimeResponseService")
public class ModifySystemTimeResponseService implements ModifyResponseService{
    @Override
    public Response modify(Response response) {
        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
        return response;
    }
}
