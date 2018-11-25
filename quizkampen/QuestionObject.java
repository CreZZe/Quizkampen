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

package quizkampen;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class QuestionObject {

    String category;
    String question;
    
    String answer1, answer2, answer3, answer4;
    
    QuestionObject(String c, String q, String a1, String a2, String a3, String a4){
        category=c.substring(0,c.length()-4);
        question=q;
        answer1=a1.substring(1, a1.length());
        answer2=a2.substring(1, a2.length());
        answer3=a3.substring(1, a3.length());
        answer4=a4.substring(1, a4.length());
//        HashMap<String, Boolean> svaren = new HashMap<>();
//        svaren.put(a1, true);
//        svaren.put(a2, false);
//        svaren.put(a3, false);
//        svaren.put(a4, false);
        //Collections.shuffle(svaren):
    }
    public List getQandA(){
        List<String> fragan = new LinkedList<>();
        fragan.add(category);
        fragan.add(question);
        fragan.add(answer1);
        fragan.add(answer2);
        fragan.add(answer3);
        fragan.add(answer4);
        return fragan;
    }
    
    public String getCategory(){
        return category;
    }
    public String getQuestion(){
        return question;
    }
    public String getAnswer1(){
        return answer1;
    }
    public String getAnswer2(){
        return answer2;
    }
    public String getAnswer3(){
        return answer3;
    }
    public String getAnswer4(){
        return answer4;
    }
    
    
}