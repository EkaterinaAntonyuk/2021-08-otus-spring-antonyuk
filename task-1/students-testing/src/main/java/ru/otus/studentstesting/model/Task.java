package ru.otus.studentstesting.model;

import java.util.List;

public class Task {
    private String question;
    private List<String> answers;

    public Task(String question, List<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    @Override
    public String toString() {
        StringBuilder answersString = new StringBuilder();
        for (int i = 0; i < answers.size(); i++) {
            answersString.append(i + 1).append(".").append(answers.get(i)).append(" ");
        }
        return question + ":" + "\n" + answersString;
    }
}
