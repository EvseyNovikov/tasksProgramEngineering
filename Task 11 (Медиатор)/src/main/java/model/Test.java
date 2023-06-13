package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    private ArrayList<Question> test = new ArrayList<>(); //добавлен модификатор доступа private для инкапсуляции

    public Test() {
        load("C:\\Users\\daksi\\Documents\\ВУЗ\\6 семестр\\Минакова\\task11\\src\\test.txt");
    }

    public ArrayList<Question> createTest(int numberQwest) {// изменено на numberQwest
        ArrayList<Question> currentTest = new ArrayList<>();
        ArrayList<Question> testCopy = new ArrayList<>(test);//добавлено создание копии списка

        for (int i = 0; i < numberQwest; i++) {
            int index = (int) (Math.random() * testCopy.size());

            if (index == testCopy.size()) {
                index--;
            }

            currentTest.add(testCopy.get(index));
            testCopy.remove(index);
        }
        return currentTest;
    }

    public ArrayList<Question> getTest() {
        return test;
    }

    private void load(String filename) {
        try {
            Path filePath = Paths.get(filename);
            List<String> lines = Files.readAllLines(filePath);

            Question question = null;

            for (String line : lines) {
                if (line.equalsIgnoreCase("#nextQuestion")) {
                    if (question != null) {
                        test.add(question);
                    }
                    question = null;
                } else if (line.equalsIgnoreCase("#badQuestion")) {
                    if (question != null) {
                        question.addFalse("");
                    }
                } else {
                    if (question == null) {
                        question = new Question(line);
                    } else {
                        question.addTrue(line);
                    }
                }
            }

            if (question != null) {
                test.add(question);
            }

            System.out.println("Файл с вопросами успешно прочитан");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла");
            e.printStackTrace();
        }
    }
}