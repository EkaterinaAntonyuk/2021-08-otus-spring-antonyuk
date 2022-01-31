package ru.otus.studentstesting.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ru.otus.studentstesting.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

@Service
public class StudentInteractionServiceImpl implements StudentInteractionService {

    private final MessageSource messageSource;

    public StudentInteractionServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getStudentName() {
        System.out.println(messageSource.getMessage("strings.hello", null, LocaleContextHolder.getLocale()));
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read answer", e);
        }
    }

    @Override
    public int getAnswer() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Failed to read answer", e);
        }
    }

    @Override
    public void printTestPassed(String name){
        System.out.println(messageSource.getMessage("strings.positive-result", new String[]{name},
                LocaleContextHolder.getLocale()));
    }

    @Override
    public void printTestNotPassed(String name){
        System.out.println(messageSource.getMessage("strings.negative-result", new String[]{name},
                LocaleContextHolder.getLocale()));
    }

    @Override
    public void printTask(Task task) {
        System.out.println(task.print());
    }
}
