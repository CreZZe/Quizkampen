/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author marcu
 * aer awesome
 */
package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class QuestionObject {

    String category;
    String question;

    String correctAnswer, answer2, answer3, answer4;

    Map<String, Boolean> mapAnswers = new HashMap<>();

    QuestionObject(String c, String q, String a1, String a2, String a3, String a4) {
        category = c;
        question = q;
//        correctAnswer = a1.substring(1, a1.length());
//        answer2 = a2.substring(1, a2.length());
//        answer3 = a3.substring(1, a3.length());
//        answer4 = a4.substring(1, a4.length());

        mapAnswers.put(answer2, Boolean.FALSE);
        mapAnswers.put(answer3, Boolean.FALSE);
        mapAnswers.put(answer4, Boolean.FALSE);
        mapAnswers.put(correctAnswer, Boolean.TRUE);

        correctAnswer = a1;
        answer2 = a2;
        answer3 = a3;
        answer4 = a4;

    }

    public List<String> getQandA() {
        List<String> fragan = new LinkedList<>();
        fragan.add(category);
        fragan.add(question);
        fragan.add(correctAnswer);
        fragan.add(answer2);
        fragan.add(answer3);
        fragan.add(answer4);

        return fragan;
    }

    public boolean checkAnswer(String check) {
        return mapAnswers.get(check);
    }

    public String getCategory() {
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void genQ(String cat) {
        this.category = cat;

    }

    public void printMe() {
        System.out.println(category);
        System.out.println(question);
        System.out.println(correctAnswer);
        System.out.println(answer2);
        System.out.println(answer3);
        System.out.println(answer4);
        this.checkAnswer(correctAnswer);
    }
    

}
