package ru.otus.studentstesting.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.studentstesting.model.Task;
import ru.otus.studentstesting.shell.ApplicationCommands;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
public class ApplicationCommandsTest {
    @MockBean
    StudentInteractionService interactionService;
    @MockBean
    LocaleAwareMessageSource messageSource;
    @MockBean
    TasksDAO tasksDAO;

    @Test
    public void ifTheNumberOfCorrectAnswersIsMoreThanRequired() {
        List<Task> tasks = fillTasksList();

        when(tasksDAO.readQuestions()).thenReturn(tasks);
        when(interactionService.getStudentName()).thenReturn("Test Test");
        when(interactionService.getAnswer()).thenReturn(1, 1);

        ApplicationCommands applicationCommands = new ApplicationCommands(tasksDAO, interactionService,
                2, messageSource);
        applicationCommands.startTest();
        applicationCommands.showQuestions();
        applicationCommands.showResult();

        verify(interactionService).printTestPassed("Test Test");
        verify(interactionService, never()).printTestNotPassed(anyString());
    }

    @Test
    public void ifTheNumberOfCorrectAnswersIsLessThanRequired() {
        List<Task> tasks = fillTasksList();

        when(tasksDAO.readQuestions()).thenReturn(tasks);
        when(interactionService.getStudentName()).thenReturn("Test Test");
        when(interactionService.getAnswer()).thenReturn(1, 2);

        ApplicationCommands applicationCommands = new ApplicationCommands(tasksDAO, interactionService,
                2, messageSource);
        applicationCommands.startTest();
        applicationCommands.showQuestions();
        applicationCommands.showResult();

        verify(interactionService).printTestNotPassed("Test Test");
        verify(interactionService, never()).printTestPassed(anyString());
    }

    @Test
    public void ifShowQuestionsNotAvailable(){

        ApplicationCommands applicationCommands = new ApplicationCommands(tasksDAO, interactionService,
                2, messageSource);
        applicationCommands.showQuestions();

        verify(interactionService, never()).printTask(any());
        verify(interactionService, never()).printTestPassed(anyString());
        verify(interactionService, never()).printTestNotPassed(anyString());
    }

    @Test
    public void ifResultNotAvailable(){
        ApplicationCommands applicationCommands = new ApplicationCommands(tasksDAO, interactionService,
                2, messageSource);
        applicationCommands.startTest();
        applicationCommands.showResult();

        verify(interactionService, never()).printTask(any());
        verify(interactionService, never()).printTestPassed(anyString());
        verify(interactionService, never()).printTestNotPassed(anyString());
    }

    private List<Task> fillTasksList() {
        List<Task> tasks = new ArrayList<>();

        List<String> firstTaskAnswers = new ArrayList<>();
        firstTaskAnswers.add("Baghdad");
        firstTaskAnswers.add("Jerusalem");
        firstTaskAnswers.add("Ottawa");
        firstTaskAnswers.add("Abu Dhabi");
        tasks.add(new Task("Name the capital of Iraq", "Baghdad", firstTaskAnswers));

        List<String> secondTaskAnswers = new ArrayList<>();
        secondTaskAnswers.add("Dakar");
        secondTaskAnswers.add("Gitega");
        secondTaskAnswers.add("Baghdad");
        secondTaskAnswers.add("Antananarivo");
        tasks.add(new Task("Name the capital of Senegal", "Dakar", secondTaskAnswers));
        return tasks;
    }
}
