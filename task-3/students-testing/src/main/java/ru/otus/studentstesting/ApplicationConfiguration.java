package ru.otus.studentstesting;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import ru.otus.studentstesting.service.TasksCsvDAO;
import ru.otus.studentstesting.service.TasksCsvDAOImpl;

@Configuration
public class ApplicationConfiguration {
    private final MessageSource messageSource;

    public ApplicationConfiguration(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Bean
    public TasksCsvDAO tasksCsvDAO() {
        return new TasksCsvDAOImpl(messageSource.getMessage("data.filename",
                null, LocaleContextHolder.getLocale()));
    }
}
