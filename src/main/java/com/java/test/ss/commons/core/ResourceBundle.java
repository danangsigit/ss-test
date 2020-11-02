package com.java.test.ss.commons.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

public class ResourceBundle {

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;

    public String getMessage(String messageKey, Locale locale, Object... parameters) {
        String msg = null;
        try {
            msg = messageSource.getMessage(messageKey, parameters, messageKey, locale);
        } catch (NoSuchMessageException e) {
            msg = messageKey;
        }

        return msg;
    }

    public String getMessage(String message, Object... parameters) {
        Locale locale = LocaleContextHolder.getLocale();
        return getMessage(message, locale, parameters);
    }
}