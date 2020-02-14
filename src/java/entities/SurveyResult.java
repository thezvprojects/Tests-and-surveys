/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author vasoj
 */
public class SurveyResult {
    String answertext;
    int numberofapps;
    int percent;
    String question;
    String tsname;

    public String getTsname() {
        return tsname;
    }

    public void setTsname(String tsname) {
        this.tsname = tsname;
    }
    
    

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    

    public String getAnswertext() {
        return answertext;
    }

    public void setAnswertext(String answertext) {
        this.answertext = answertext;
    }

    public int getNumberofapps() {
        return numberofapps;
    }

    public void setNumberofapps(int numberofapps) {
        this.numberofapps = numberofapps;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
    
}
