package ru.otus.studentstesting.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class StudentInteractionService {
    public String getStudentName() {
        System.out.println("Your first and last name:");
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read answer", e);
        }
    }

    public int getAnswer() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Failed to read answer", e);
        }
    }
}
