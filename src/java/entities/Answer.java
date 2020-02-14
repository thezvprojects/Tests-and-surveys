/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author vasoj
 */
@Entity(name="testsurveyanswer")
public class Answer {
    
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="answertext")
    private String answertext;
    
    @Column(name="answernumber")
    private int answernumber;
    
    @Column(name="istrue")
    private int istrue;
    
    @Column(name="testquestion_id")
    private int testquestion_id;

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getAnswertext() {
        return answertext;
    }

    public void setAnswertext(String answertext) {
        this.answertext = answertext;
    }

    public int getAnswernumber() {
        return answernumber;
    }

    public void setAnswernumber(int answernumber) {
        this.answernumber = answernumber;
    }

    public int getIstrue() {
        return istrue;
    }

    public void setIstrue(int istrue) {
        this.istrue = istrue;
    }

    public int getTestquestion_id() {
        return testquestion_id;
    }

    public void setTestquestion_id(int testquestion_id) {
        this.testquestion_id = testquestion_id;
    }
    
}
