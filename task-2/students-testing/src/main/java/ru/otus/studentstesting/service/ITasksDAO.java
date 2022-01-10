package ru.otus.studentstesting.service;

import ru.otus.studentstesting.model.Task;

import java.util.List;

public interface ITasksDAO {
    List<Task> readQuestions();
}
