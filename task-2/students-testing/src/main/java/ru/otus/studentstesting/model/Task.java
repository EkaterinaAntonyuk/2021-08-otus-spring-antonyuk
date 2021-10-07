package ru.otus.studentstesting.model;

import java.util.List;

public class Task {
    private final String question;
    private final String correctAnswer;
    private final List<String> answers;

    public Task(String question, String correctAnswer, List<String> answers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
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

    public boolean checkAnswerIsCorrect(int answerNumber){
        return answers.get(answerNumber - 1).equals(correctAnswer);
    }
}
