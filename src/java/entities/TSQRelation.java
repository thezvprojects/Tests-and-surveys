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
@Entity(name="testsurveyquestionrelation")
public class TSQRelation {
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="testsurvey_id")
    private int testsurvey_id;
    
    @Column(name="testsurveyquestion_id")
    private int testsurveyquestion_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestsurvey_id() {
        return testsurvey_id;
    }

    public void setTestsurvey_id(int testsurvey_id) {
        this.testsurvey_id = testsurvey_id;
    }

    public int getTestsurveyquestion_id() {
        return testsurveyquestion_id;
    }

    public void setTestsurveyquestion_id(int testsurveyquestion_id) {
        this.testsurveyquestion_id = testsurveyquestion_id;
    }
    
    
}
