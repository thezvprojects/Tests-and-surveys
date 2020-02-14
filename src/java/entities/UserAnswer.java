/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author vasoj
 */
@Entity(name="useranswer")
public class UserAnswer {
    @Id
    @Column
    private int id;
    
    @Column
    private int testsurveyquestion_id;
    
    @Column
    private String testsurveyanswertext;
    
    @Column
    private int user_id;
    
    
    @Column
    private int testsurvey_id;
    
    @Transient
    private String answertext;

    public String getAnswertext() {
        return answertext;
    }

    public void setAnswertext(String answertext) {
        this.answertext = answertext;
    }

    public int getTestsurvey_id() {
        return testsurvey_id;
    }

    public void setTestsurvey_id(int testsurvey_id) {
        this.testsurvey_id = testsurvey_id;
    }
    

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestsurveyquestion_id() {
        return testsurveyquestion_id;
    }

    public void setTestsurveyquestion_id(int testsurveyquestion_id) {
        this.testsurveyquestion_id = testsurveyquestion_id;
    }

    public String getTestsurveyanswertext() {
        return testsurveyanswertext;
    }

    public void setTestsurveyanswertext(String testsurveyanswertext) {
        this.testsurveyanswertext = testsurveyanswertext;
    }

    

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    
    
    
}
