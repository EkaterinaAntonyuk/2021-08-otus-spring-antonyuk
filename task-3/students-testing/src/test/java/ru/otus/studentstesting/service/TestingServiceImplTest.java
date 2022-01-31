package ru.otus.studentstesting.service;

import org.junit.jupiter.api.Test;
import ru.otus.studentstesting.model.Task;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TestingServiceImplTest {

    @Test
    public void ifTheNumberOfCorrectAnswersIsMoreThanRequired() {
        StudentInteractionService interactionService = mock(StudentInteractionService.class);
        TasksCsvDAO tasksDAO = mock(TasksCsvDAO.class);
        List<Task> tasks = fillTasksList();

        when(tasksDAO.readQuestions()).thenReturn(tasks);
        when(interactionService.getStudentName()).thenReturn("Test Test");
        when(interactionService.getAnswer()).thenReturn(1, 1);

        TestingServiceImpl testingService = new TestingServiceImpl(tasksDAO, interactionService, 2);
        testingService.test();

        verify(interactionService).printTestPassed("Test Test");
        verify(interactionService, never()).printTestNotPassed(anyString());
    }

    @Test
    public void ifTheNumberOfCorrectAnswersIsLessThanRequired() {
        StudentInteractionService interactionService = mock(StudentInteractionService.class);
        TasksCsvDAO tasksDAO = mock(TasksCsvDAO.class);
        List<Task> tasks = fillTasksList();

        when(tasksDAO.readQuestions()).thenReturn(tasks);
        when(interactionService.getStudentName()).thenReturn("Test Test");
        when(interactionService.getAnswer()).thenReturn(1, 2);

        TestingServiceImpl testingService = new TestingServiceImpl(tasksDAO, interactionService, 2);
        testingService.test();

        verify(interactionService).printTestNotPassed("Test Test");
        verify(interactionService, never()).printTestPassed(anyString());
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
