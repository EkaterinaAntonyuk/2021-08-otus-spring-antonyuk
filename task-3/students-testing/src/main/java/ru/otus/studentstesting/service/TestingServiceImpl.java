package ru.otus.studentstesting.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.studentstesting.model.Task;

import java.util.List;

@Service
public class TestingServiceImpl implements TestingService {
    private final TasksCsvDAO tasksDAO;
    private final StudentInteractionService studentInteractionService;
    private final int requiredCorrectAnswers;

    public TestingServiceImpl(TasksCsvDAO tasksDAO,
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
            studentInteractionService.printTask(task);
            if (task.checkAnswerIsCorrect(studentInteractionService.getAnswer())) {
                correctAnswersCount++;
            }
        }
        if (correctAnswersCount >= requiredCorrectAnswers) {
            studentInteractionService.printTestPassed(name);
        } else {
            studentInteractionService.printTestNotPassed(name);
        }
    }

}
