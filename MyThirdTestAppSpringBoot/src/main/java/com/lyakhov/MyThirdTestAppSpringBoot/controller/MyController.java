package com.lyakhov.MyThirdTestAppSpringBoot.controller;

import com.lyakhov.MyThirdTestAppSpringBoot.service.ModifyRequestService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.lyakhov.MyThirdTestAppSpringBoot.exception.UnsupportedCodeException;
import com.lyakhov.MyThirdTestAppSpringBoot.exception.ValidationFailedException;
import com.lyakhov.MyThirdTestAppSpringBoot.model.*;
import com.lyakhov.MyThirdTestAppSpringBoot.service.ValidationService;
import com.lyakhov.MyThirdTestAppSpringBoot.util.DateTimeUtil;

import java.util.Date;

@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(ValidationService validationService, ModifyRequestService modifyRequestService) {
        this.validationService = validationService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        log.info("Received request: {}", request);

        var response = Response.builder()
                .id(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("Initial response: {}", response);

        try {
            if (bindingResult.hasErrors()) {
                log.error("BindingResult has errors: {}", bindingResult.getAllErrors());
                throw new ValidationFailedException("Validation failed", "ValidationFailedException");
            }

            validationService.isValid(bindingResult);

            if ("123".equals(request.getUid())) {
                log.error("Unsupported UID: {}", request.getUid());
                throw new UnsupportedCodeException("UID не может быть равен 123", "UnsupportedCodeException");
            }
        } catch (ValidationFailedException e) {
            log.error("ValidationFailedException occurred: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            log.info("Updated response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Exception occurred: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            log.info("Updated response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyRequestService.modify(request);

        log.info("Final response: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}