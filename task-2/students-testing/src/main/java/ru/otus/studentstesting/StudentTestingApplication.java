package ru.otus.studentstesting;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.studentstesting.service.TestingService;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan
public class StudentTestingApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(StudentTestingApplication.class);
        TestingService service = context.getBean(TestingService.class);
        service.test();
    }


}
