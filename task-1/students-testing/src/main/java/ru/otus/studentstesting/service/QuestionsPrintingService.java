package ru.otus.studentstesting.service;

import ru.otus.studentstesting.model.Task;

import java.util.List;

public class QuestionsPrintingService {
    private final QuestionsReadingService questionsReadingService;

    public QuestionsPrintingService(QuestionsReadingService questionsReadingService) {
        this.questionsReadingService = questionsReadingService;
    }

    public void printQuestions() {
        List<Task> tasks = questionsReadingService.readQuestions();
        for (Task task :
                tasks) {
            System.out.println(task.toString());
        }
    }

}
