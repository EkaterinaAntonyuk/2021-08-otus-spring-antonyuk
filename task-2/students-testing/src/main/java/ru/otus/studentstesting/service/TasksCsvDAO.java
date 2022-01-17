package ru.otus.studentstesting.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.studentstesting.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class TasksCsvDAO implements ITasksDAO {
    private final String resourceName;

    public TasksCsvDAO(@Value("${questions.filename}") String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public List<Task> readQuestions() {
        try (InputStream is = getFileFromResourceAsStream(resourceName)) {
            Iterable<CSVRecord> records = fetch(is);
            List<Task> tasks = new ArrayList<>();
            for (CSVRecord csvRecord : records) {
                List<String> answers = new ArrayList<>();
                for (int i = 0; i < csvRecord.size() - 2; i++) {
                    answers.add(csvRecord.get(i + 2));
                }
                Task task = new Task(csvRecord.get(0), csvRecord.get(1), answers);
                tasks.add(task);
            }
            return tasks;
        } catch (IOException e){
            throw new RuntimeException("Failed to read records from resource", e);
        }
    }

    private InputStream getFileFromResourceAsStream(String resourceName) {
        InputStream inputStream = getClass().getResourceAsStream("/" + resourceName);
        if (inputStream == null) {
            throw new IllegalArgumentException("resource not found! " + resourceName);
        } else {
            return inputStream;
        }

    }

    private Iterable<CSVRecord> fetch(InputStream is) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return CSVFormat.DEFAULT.parse(reader);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read records from resource", e);
        }
    }

}
