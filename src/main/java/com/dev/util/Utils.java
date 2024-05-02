package com.dev.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    @Resource
    private MessageSource messageSource;
    public String getMessageLocalized(String messageKey){
        return messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
    }

    public ResponseEntity<?> generateInternalServerErrorMessage() throws JsonProcessingException {
        String errorMessage = getMessageLocalized("label.internal.server.error");
        return GenerateResponse.error(errorMessage, null);
    }
}
