package ru.otus.studentstesting.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.studentstesting.model.Task;

import java.util.List;

@Service
public class TestingService {
    private final TasksReadingService tasksReadingService;
    private final StudentInteractionService studentInteractionService;
    private final int requiredCorrectAnswers;

    public TestingService(TasksReadingService tasksReadingService,
                          StudentInteractionService studentInteractionService,
                          @Value("${answers}") int requiredCorrectAnswers) {
        this.tasksReadingService = tasksReadingService;
        this.studentInteractionService = studentInteractionService;
        this.requiredCorrectAnswers = requiredCorrectAnswers;
    }

    public void test() {
        String name = studentInteractionService.getStudentName();
        List<Task> tasks = tasksReadingService.readQuestions();
        int correctAnswersCount = 0;
        for (Task task : tasks) {
            System.out.println(task.toString());
            if (task.checkAnswerIsCorrect(studentInteractionService.getAnswer())) {
                correctAnswersCount++;
            }
        }
        if (correctAnswersCount >= requiredCorrectAnswers) {
            System.out.println(name + ", you passed the test!");
        } else {
            System.out.println(name + ", you did not pass the test!");
        }
    }

}
