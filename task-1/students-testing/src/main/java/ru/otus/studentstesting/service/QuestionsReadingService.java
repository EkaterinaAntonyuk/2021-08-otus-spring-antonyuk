package ru.otus.studentstesting.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.studentstesting.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionsReadingService {
    private final String resourceName;

    public QuestionsReadingService(String resourceName) {
        this.resourceName = resourceName;
    }

    public List<Task> readQuestions() {
        InputStream inputStream = getFileFromResourceAsStream(resourceName);
        Iterable<CSVRecord> records = fetchCsvRecords(inputStream);
        List<Task> tasks = new ArrayList<>();
        for (CSVRecord csvRecord : records) {
            List<String> answers = new ArrayList<>();
            for (int i = 0; i < csvRecord.size() - 1; i++) {
                answers.add(csvRecord.get(i + 1));
            }
            Task task = new Task(csvRecord.get(0), answers);
            tasks.add(task);
        }
        return tasks;
    }

    private InputStream getFileFromResourceAsStream(String resourceName) {
        InputStream inputStream = getClass().getResourceAsStream("/" + resourceName);
        if (inputStream == null) {
            throw new IllegalArgumentException("resource not found! " + resourceName);
        } else {
            return inputStream;
        }

    }

    public Iterable<CSVRecord> fetchCsvRecords(InputStream is) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return CSVFormat.DEFAULT.parse(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read records from resource", e);
        }
    }

}
