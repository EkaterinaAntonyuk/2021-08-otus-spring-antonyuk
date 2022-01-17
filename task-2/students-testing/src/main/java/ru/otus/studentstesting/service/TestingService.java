package ru.otus.studentstesting.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.studentstesting.model.Task;

import java.util.List;

@Service
public class TestingService implements ITestingService {
    private final ITasksDAO tasksDAO;
    private final IStudentInteractionService studentInteractionService;
    private final int requiredCorrectAnswers;

    public TestingService(TasksCsvDAO tasksDAO,
                          StudentInteractionService studentInteractionService,
                          @Value("${answers}") int requiredCorrectAnswers) {
        this.tasksDAO = tasksDAO;
        this.studentInteractionService = studentInteractionService;
        this.requiredCorrectAnswers = requiredCorrectAnswers;
    }

    @Override
    public void test() {
        String name = studentInteractionService.getStudentName();
        List<Task> tasks = tasksDAO.readQuestions();
        int correctAnswersCount = 0;
        for (Task task : tasks) {
            System.out.println(task.print());
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
