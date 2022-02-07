package ru.otus.studentstesting.shell;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.studentstesting.model.Task;
import ru.otus.studentstesting.service.LocaleAwareMessageSource;
import ru.otus.studentstesting.service.StudentInteractionService;
import ru.otus.studentstesting.service.TasksDAO;

import java.util.List;

@ShellComponent
public class ApplicationCommands {
    private final TasksDAO tasksDAO;
    private final StudentInteractionService studentInteractionService;
    private final int requiredCorrectAnswers;
    private final LocaleAwareMessageSource localeAwareMessageSource;

    private String userName;
    private int correctAnswersCount;
    private boolean isTestDone;

    public ApplicationCommands(TasksDAO tasksDAO, StudentInteractionService studentInteractionService,
                               @Value("${answers}") int requiredCorrectAnswers,
                               LocaleAwareMessageSource localeAwareMessageSource) {
        this.tasksDAO = tasksDAO;
        this.studentInteractionService = studentInteractionService;
        this.requiredCorrectAnswers = requiredCorrectAnswers;
        this.localeAwareMessageSource = localeAwareMessageSource;
    }

    @ShellMethod(value = "Start  test", key = {"s", "start"})
    public void startTest() {
        this.userName = studentInteractionService.getStudentName();
    }

    @ShellMethod(value = "Show questions", key = {"q", "question"})
    @ShellMethodAvailability(value = "isShowQuestionsAvailable")
    public void showQuestions() {
        List<Task> tasks = tasksDAO.readQuestions();
        correctAnswersCount = 0;
        for (Task task : tasks) {
            studentInteractionService.printTask(task);
            if (task.checkAnswerIsCorrect(studentInteractionService.getAnswer())) {
                correctAnswersCount++;
            }
        }
        isTestDone = true;
    }

    @ShellMethod(value = "Show result", key = {"r", "result"})
    @ShellMethodAvailability(value = "isResultAvailable")
    public void showResult() {
        if (correctAnswersCount >= requiredCorrectAnswers) {
            studentInteractionService.printTestPassed(userName);
        } else {
            studentInteractionService.printTestNotPassed(userName);
        }
    }

    private Availability isShowQuestionsAvailable() {
        return userName == null ? Availability.unavailable(localeAwareMessageSource
                .getMessage("strings.questions-unavailable")) : Availability.available();
    }

    private Availability isResultAvailable() {
        return !isTestDone ? Availability.unavailable(localeAwareMessageSource
                .getMessage("strings.result-unavailable")) : Availability.available();
    }
}
