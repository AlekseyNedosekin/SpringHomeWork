package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService serviceQuestion = context.getBean(QuestionService.class);
        int countCorrectAnswer = 0;
        System.out.println("Enter your full name ");
        String fio = reader.readLine();
        for (int i = 0; i < serviceQuestion.getCountQuestion(); i++){
            Question someQuestion = serviceQuestion.getByNumber(i);
            System.out.print("Question № " + (i+1) + ": ");
            System.out.println(someQuestion.getQuestion());
            for (int j = 0; j < someQuestion.getAnswer().length; j++) {
                System.out.println(String.valueOf(j+1) + " - " + someQuestion.getAnswer()[j]);
            }
            System.out.print("You have answered: ");
            int answer = Integer.parseInt(reader.readLine());
            if (answer == someQuestion.getAnswerNum()) {
                System.out.println("+ correct answer ");
                countCorrectAnswer++;
            }
            else
                System.out.println("- incorrect answer, correct answer: "+someQuestion.getAnswerNum());
            }

        System.out.println(fio + " Total correct answers: " + countCorrectAnswer);
        if (countCorrectAnswer > 3)
            System.out.println("Congratulations to you - you are smart");
        else
            System.out.println("You have a lot to learn");
        context.close();

    }
}
