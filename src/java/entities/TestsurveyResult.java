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
@Entity(name="testsurveyresult")
public class TestsurveyResult {
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="pointpercent")
    private int pointpercent;
    
    @Column(name="user_id")
    private int user_id;
    
    @Column(name="testsurvey_id")
    private int testsurvey_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPointpercent() {
        return pointpercent;
    }

    public void setPointpercent(int pointpercent) {
        this.pointpercent = pointpercent;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTestsurvey_id() {
        return testsurvey_id;
    }

    public void setTestsurvey_id(int testsurvey_id) {
        this.testsurvey_id = testsurvey_id;
    }
    
    
}
