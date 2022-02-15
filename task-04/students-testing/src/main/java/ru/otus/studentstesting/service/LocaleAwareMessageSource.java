package ru.otus.studentstesting.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocaleAwareMessageSource {

    private final MessageSource messageSource;
    private final Locale locale;

    public LocaleAwareMessageSource(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    public String getMessage(String code, Object... args){
        return messageSource.getMessage(code, args, locale);
    }
}
