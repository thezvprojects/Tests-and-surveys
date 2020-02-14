/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author vasoj
 */
@Entity(name="testsurvey")
public class Testsurvey {
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="creator_id")
    private int creator_id;
    
    @Column(name="description")
    private String description;
    
    @Column(name="dateofstart")
    private Timestamp dateofstart;
    
    @Column(name="dateofend")
    private Timestamp dateofend;
    
    @Column(name="name")
    private String name;
    
    @Column(name="type")
    private int type;
    
    @Column(name="timeleft")
    private int timeleft;

    public int getTimeleft() {
        return timeleft;
    }

    public void setTimeleft(int timeleft) {
        this.timeleft = timeleft;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateofstart() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s =sdf.format(dateofstart);
        return dateofstart;
    }

    public void setDateofstart(Timestamp dateofstart) {
        this.dateofstart = dateofstart;
    }

    public Timestamp getDateofend() {
        return dateofend;
    }

    public void setDateofend(Timestamp dateofend) {
        this.dateofend = dateofend;
    } 
    
    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Date getDateofstart() {
//        return dateofstart;
//    }
//
//    public void setDateofstart(Date dateofstart) {
//        this.dateofstart = dateofstart;
//    }
//
//    public Date getDateofend() {
//        return dateofend;
//    }
//
//    public void setDateofend(Date dateofend) {
//        this.dateofend = dateofend;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public String whichType() {
        if(type==1) return "Test";
        if(type==2) return "Survey";
        return "";
    }
    
    public boolean isTest() {
        return (type==1);
    }
    
    public int pointsPercent(String username,String tsname) {
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        
        String query="select id from users where username='"+username+"'";
        
        int userid=(int)session.createQuery(query).uniqueResult();
        
        session.flush();
        session.clear();
        
        query="select id from testsurvey where name='"+tsname+"'";
        
        int tsid=(int)session.createQuery(query).uniqueResult();
        
        session.flush();
        session.clear();
        
        query="select pointpercent from testsurveyresult where "
                + "user_id='"+userid+"' "+
                    "and testsurvey_id='"+tsid+"'";
        
        int res=0;
        Object obj=session.createQuery(query).uniqueResult();
        if(obj!=null)
            res=(int)obj;
        
        session.getTransaction().commit();
        session.close();
        
        return res;
    }
    
    public boolean checkIfDone(String name,String username) {
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        String query="select id from testsurvey where name='"+name+"'";
        
        int ts_id=(int)session.createQuery(query).uniqueResult();
        
        session.flush();
        session.clear();
        
        query="select id from users where username='"+username+"'";
        
        int us_id=(int)session.createQuery(query).uniqueResult();
        
        session.flush();
        session.clear();
        
        query="select count(*) from testsurveyresult where user_id='"+us_id+"'"+
                    " and testsurvey_id='"+ts_id+"'";
        boolean res=((long)session.createQuery(query).uniqueResult()>0);
        
        session.flush();
        session.clear();
        
        query="select count(*) from useranswer where user_id='"+us_id+"'"+
                    " and testsurvey_id='"+ts_id+"'";
        boolean res2=((long)session.createQuery(query).uniqueResult()>0);
        
        session.getTransaction().commit();
        session.close();
        
        long dateofstarttime=dateofstart.getTime();
        long dateofendtime=dateofend.getTime();
        long currenttime=System.currentTimeMillis();
        
        //boolean res3=!((dateofstarttime<currenttime)&&(dateofendtime>currenttime));
        
        return res||res2;
    }
    
}
