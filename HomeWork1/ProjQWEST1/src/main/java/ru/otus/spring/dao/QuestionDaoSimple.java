package ru.otus.spring.dao;

import java.util.ArrayList;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import ru.otus.spring.domain.Question;
import java.io.InputStreamReader;

public class QuestionDaoSimple implements QuestionDao {

    private ArrayList<Question> questions;

    public QuestionDaoSimple(String fileName) {
        CSVReader csvReader;
        try {
            String path = getClass().getClassLoader().getResource(fileName).toString();
            csvReader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName)));
            char separator = ';';
            CSVParser parser = new CSVParserBuilder().withSeparator(separator)
                    .build();
            String[] nextLine;
            int index = 0;

            this.questions = new ArrayList<Question>();
            while ((nextLine = csvReader.readNext()) != null) {
                for (String string : nextLine) {
                    String[] value;
                    value = parser.parseLine(string);

                    String[] answers = new String[4];
                    try {
                        answers[0] = value[1];
                        answers[1] = value[2];
                        answers[2] = value[3];
                        answers[3] = value[4];
                        Question question = new Question(value[0], answers, Integer.valueOf(value[5]));
                        questions.add(question);
                        index++;
                    } catch (Exception e) {
                        System.out.println("Вопрос №" + String.valueOf(index + 1) + " не корректный");
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("Файл csv не найден!");
        }
    }

    public Question findByNumber(int number) {
        return questions.get(number);
    }

    public int getCountQuestion(){
        return questions.size();
    }

}
