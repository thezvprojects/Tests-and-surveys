/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author vasoj
 */
@Entity(name="users")
public class User {
    
    @Id
    @Column
    private Integer id;
    
    @Column
    private String username;
    
    @Column
    private String password;
    
    @Column    
    private String name;
    
    @Column
    private String surname;
    
    @Column
    private Date dateOfBirth;
    
    @Column
    private String placeOfBirth;
    
    @Column
    private String jmbg;
    
    @Column
    private String contactPhone;
    
    @Column
    private String email;
    
    @Column   
    private int type;
    
    @Column
    private int checked;
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

//    public Set getTestsurveys() {
//        return testsurveys;
//    }
//
//    public void setTestsurveys(Set testsurveys) {
//        this.testsurveys = testsurveys;
//    }
//
//    public Set getUseranswers() {
//        return useranswers;
//    }
//
//    public void setUseranswers(Set useranswers) {
//        this.useranswers = useranswers;
//    }
//
//    public Set getTestsurveyresults() {
//        return testsurveyresults;
//    }
//
//    public void setTestsurveyresults(Set testsurveyresults) {
//        this.testsurveyresults = testsurveyresults;
//    }
     
     
     
}
