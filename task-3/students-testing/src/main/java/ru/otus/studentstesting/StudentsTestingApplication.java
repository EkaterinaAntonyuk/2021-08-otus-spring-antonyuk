package ru.otus.studentstesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.studentstesting.service.TestingService;
import ru.otus.studentstesting.service.TestingServiceImpl;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class StudentsTestingApplication {
    private final TestingService testingService;

    public StudentsTestingApplication(TestingServiceImpl testingService) {
        this.testingService = testingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentsTestingApplication.class, args);
    }

    @PostConstruct
    private void startTesting(){
        testingService.test();
    }

}
