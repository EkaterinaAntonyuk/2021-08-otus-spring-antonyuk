package ru.otus.studentstesting;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.studentstesting.service.QuestionsPrintingService;

public class StudentTestingApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionsPrintingService questionsPrintingService = context.getBean(QuestionsPrintingService.class);
        questionsPrintingService.printQuestions();
    }


}
