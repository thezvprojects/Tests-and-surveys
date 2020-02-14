/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author vasoj
 */
@Entity(name="testsurveyquestion")
public class Question {
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="numberofanswers")
    private int numberofanswers;
    
    @Column(name="questiontext")
    private String questiontext;
    
    @Column(name="questiontype")
    private int questiontype;
    
    @Transient
    private List<Answer> answers;
    
    @Transient
    private List<UserAnswer> useranswers;
    
    @Transient
    private Answer singleuseranswer;
    
    @Transient
    private String singleuseranswertext;
    
    @Transient
    private int localnum;
    
    @Transient
    private boolean truewrong;
    
    @Transient
    private boolean show;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
    

    public boolean isTruewrong() {
        return truewrong;
    }

    public void setTruewrong(boolean truewrong) {
        this.truewrong = truewrong;
    }
    
    

    public String getSingleuseranswertext() {
        return singleuseranswertext;
    }

    public void setSingleuseranswertext(String singleuseranswertext) {
        this.singleuseranswertext = singleuseranswertext;
    }
    

    public Answer getSingleuseranswer() {
        return singleuseranswer;
    }

    public void setSingleuseranswer(Answer singleuseranswer) {
        this.singleuseranswer = singleuseranswer;
    }

    
    
    

    public List<UserAnswer> getUseranswers() {
        return useranswers;
    }

    public void setUseranswers(List<UserAnswer> useranswers) {
        this.useranswers = useranswers;
    }
    

    public int getLocalnum() {
        return localnum;
    }

    public void setLocalnum(int localnum) {
        this.localnum = localnum;
    }
    
    

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    
    
  
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(int questiontype) {
        this.questiontype = questiontype;
    }


    

    public String getQuestiontext() {
        return questiontext;
    }

    public void setQuestiontext(String questiontext) {
        this.questiontext = questiontext;
    }

    public int getNumberofanswers() {
        return numberofanswers;
    }

    public void setNumberofanswers(int numberofanswers) {
        this.numberofanswers = numberofanswers;
    }
    
    public boolean isNumOrShortTextInput() {
        return (questiontype==1||questiontype==2);
    }
    
    public boolean isShortTextInput() {
        return (questiontype==2);
    }
    
    public boolean isLongTextInput() {
        return (questiontype==3);
    }
    
    public boolean isRadioInput() {
        return (questiontype==4);
    }
    
    public boolean isSelectOneInput() {
        return (questiontype==5);
    }
    
    public boolean isCheckboxInput() {
        return (questiontype==6);
    }
    
    public boolean showRight() {
        if(isShow()&&isTruewrong()) return true;
        else return false;
    }
    
    public boolean showWrong() {
        if(isShow()&&!isTruewrong()) return true;
        else return false;
    }
}
