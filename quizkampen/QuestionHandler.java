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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.*;//FileUtils.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;


public class QuestionHandler {
//    public static void main(String[] args){
//        WANT TO TEST THIS FILE ONLY? UNCOMMENT THIS PART
//    QuestionHandler p = new QuestionHandler();
//    }
    
    Path currentRelativePath = Paths.get("");
    //BELOW IS THE POSITION FOR THE FOLDER CONTAINING THE QUESTIONS
    String questionsFolder = currentRelativePath.toAbsolutePath().toString()+"\\src\\test03\\Questions\\";
    
    
    //COULD USE THE LINE BELOW AS WELL
    //Path path = Paths.get("\\src\\test03\\Questions");
    
    int numberOfCategories=0;
    int[] numberOfQuestionsPerCategory;
    
    List<String> categories = new LinkedList<>();
    
    List<QuestionObject> questionsInfo = new LinkedList<>();
    
    
    QuestionHandler(){
        
        try{
            File folder = new File(questionsFolder);
            File[] listOfFiles = folder.listFiles();
            
            
            for (int i = 0; i < listOfFiles.length; i++) {
                File file = listOfFiles[i];
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    numberOfCategories++;
                  //WANT TO PRINT THE NAMES OF ALL .TXT FILES? UNCOMMENT BELOW
                  //System.out.println(file.getName());
                  categories.add(file.getName());
                  
                  BufferedReader r = new BufferedReader(new FileReader ((questionsFolder+file.getName())));
                  String templine;
                    while((templine=r.readLine()) !=null){
                       String t[] = templine.split(",");
                       QuestionObject NewQuestionToBeAddedToList =
                               new QuestionObject(file.getName(), t[0], t[1], t[2], t[3], t[4]);
                       questionsInfo.add(NewQuestionToBeAddedToList);
                      
                    }
                } 
            }
            
            numberOfQuestionsPerCategory = new int[numberOfCategories];
            
            
        }
        catch(Exception e){
            
            e.printStackTrace();
        }
        
        //LINE BELOW LETS YOU CHECK THAT IT FINDS THE RIGHT QUESTIONS/ANSWERS
        //questionsInfo is a list containing ALL questions
        
       // System.out.println(questionsInfo.get(1).getAnswer3());
    }
    public int GetQuestionAmount(){
        return questionsInfo.size();
    }
    public int getCategoryAmount(){
        return numberOfCategories;
    }
    public List getCategories(){
        return categories;
    }
    public QuestionObject getQuestion(int questionNumber){
        return questionsInfo.get(questionNumber);
    }
    
}
