package ru.otus.studentstesting;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import ru.otus.studentstesting.service.TasksDAO;
import ru.otus.studentstesting.service.CsvTasksDAO;

import java.util.Locale;

@Configuration
public class ApplicationConfiguration {
    private final MessageSource messageSource;

    public ApplicationConfiguration(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Bean
    public TasksDAO tasksCsvDAO() {
        return new CsvTasksDAO(messageSource.getMessage("data.filename",
                null, LocaleContextHolder.getLocale()));
    }

    @Bean
    public Locale locale(){
        return LocaleContextHolder.getLocale();
    }

}
