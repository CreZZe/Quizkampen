/**
 *
 * @author marcu
 * aer awesome
 */
package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class QuestionHandler {
//    public static void main(String[] args){
//     //   WANT TO TEST THIS FILE? UNCOMMENT THIS PART
//    QuestionHandler p = new QuestionHandler();
//    System.out.println(p.getCategories());
//        System.out.println(p.GetQuestionAmount());
//        System.out.println(p.getQuestion(255).getQandA());
//        System.out.println(questionsInfo.get(1).getAnswer3());
//    }

    public Path currentRelativePath = Paths.get("");
    //BELOW IS THE POSITION FOR THE FOLDER CONTAINING THE QUESTIONS

    //below is for the active project
    //Folder was moved by niklas
    public String questionsFolder = currentRelativePath.toAbsolutePath().toString() + "/src/server/DB/Questions/";
    //this is for my testing folder
    //String questionsFolder = currentRelativePath.toAbsolutePath().toString()+"\\src\\test03\\Questions\\";

    //COULD USE THE LINE BELOW AS WELL
    //Path path = Paths.get("\\src\\test03\\Questions");
    int numberOfCategories = 0;
    int[] numberOfQuestionsPerCategory;

    public List<String> categories = new LinkedList<>();

    public List<QuestionObject> questionsInfo = new LinkedList<>();

    public QuestionObject CurrentQuestion;

    public List<List<QuestionObject>> questionsPerCategory = new LinkedList<>();

    public void setPath(String path) {
        questionsFolder = currentRelativePath.toAbsolutePath().toString() + "/" + path;

    }

    QuestionHandler() {
        //in the try{ below
        //this class checks for the Questions-folder
        //and checks for all files in it
        //all files ending in .txt are read
        //the .txt files name is the category
        //each line in the .txt files is a question with 4 answers
        //seperated by ##%#
        //Each line is made into an QuestionObject.java object
        //so each object is a question with answers and category
        //this class holds the proper .get-methods to get all necessary info

        //QestionObject.java should never directly be accessed,
        //this class holds all info you should need
        try {
            File folder = new File(questionsFolder);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                numberOfQuestionsPerCategory = new int[listOfFiles.length];
                int counter = 0;
                File file = listOfFiles[i];
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    numberOfCategories++;
                    //WANT TO PRINT THE NAMES OF ALL .TXT FILES? UNCOMMENT BELOW
                    //System.out.println(file.getName());
                    categories.add(file.getName().substring(0, file.getName().length() - 4));
                    List<QuestionObject> innerList = new LinkedList<>();

                    BufferedReader r = new BufferedReader(new FileReader((questionsFolder + file.getName())));
                    String templine;
                    String t[];
                    while ((templine = r.readLine()) != null) {
                        counter++;
                        t = templine.split("##\u0025#", 5); 
                        //if(t.length>5){
//                           OBS OBS OM DENNA PUNKT NAS SO AR FRAGORNA FEL FORMATERADE
                        // }
//                        for (String string : t) {
//                            System.out.println(string);
//                        }

                        QuestionObject NewQuestionToBeAddedToList
                                = new QuestionObject(file.getName(), t[0], t[1], t[2], t[3], t[4]);

//                       NewQuestionToBeAddedToList.ShuffleAnswers();
                        questionsInfo.add(NewQuestionToBeAddedToList);
                        innerList.add(NewQuestionToBeAddedToList);

                    }
                    numberOfQuestionsPerCategory[i] = counter;
                    questionsPerCategory.add(innerList);
                }
            }
            numberOfQuestionsPerCategory = new int[numberOfCategories];
        } catch (Exception e) {
            e.printStackTrace();
        }

//        LINE BELOW LETS YOU CHECK THAT IT FINDS THE RIGHT QUESTIONS/ANSWERS
//        questionsInfo is a list containing ALL questions
//        
//        System.out.println(questionsInfo.get(1).getAnswer3());
    }

    public int GetQuestionAmount() {
        return questionsInfo.size();
    }

    public int getCategoryAmount() {
        return numberOfCategories;
    }

    public List getCategories() {
        return categories;
    }

    public QuestionObject getQuestion(int questionNumber) {
        return questionsInfo.get(questionNumber);
    }

    public List getCategoryLists() {
        return questionsPerCategory;
    }

}
