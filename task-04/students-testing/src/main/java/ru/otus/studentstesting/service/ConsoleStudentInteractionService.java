package ru.otus.studentstesting.service;

import org.springframework.stereotype.Service;
import ru.otus.studentstesting.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class ConsoleStudentInteractionService implements StudentInteractionService {

    private final LocaleAwareMessageSource localeAwareMessageSource;

    public ConsoleStudentInteractionService(LocaleAwareMessageSource localeAwareMessageSource) {
        this.localeAwareMessageSource = localeAwareMessageSource;
    }

    @Override
    public String getStudentName() {
        System.out.println(localeAwareMessageSource.getMessage("strings.hello"));
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
        System.out.println(localeAwareMessageSource.getMessage("strings.positive-result", name));
    }

    @Override
    public void printTestNotPassed(String name){
        System.out.println(localeAwareMessageSource.getMessage("strings.negative-result", name));
    }

    @Override
    public void printTask(Task task) {
        System.out.println(task.print());
    }
}
