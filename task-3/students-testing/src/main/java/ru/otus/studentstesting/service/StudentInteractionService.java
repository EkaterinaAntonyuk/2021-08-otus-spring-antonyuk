package ru.otus.studentstesting.service;

import ru.otus.studentstesting.model.Task;

public interface StudentInteractionService {
    String getStudentName();
    int getAnswer();
    void printTestPassed(String name);
    void printTestNotPassed(String name);
    void printTask(Task task);
}
