package ru.lyakhov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.lyakhov.MySecondTestAppSpringBoot.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}
