package com.lyakhov.MyThirdTestAppSpringBoot.service;

import com.lyakhov.MyThirdTestAppSpringBoot.model.Response;

import java.util.UUID;

public class ModifyOperationUidResponseService implements ModifyResponseService {
    @Override
    public Response modify(Response response) {
        UUID uuid = UUID.randomUUID();

        response.setOperationUid(uuid.toString());

        return response;
    }
}
