/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.Answer;
import entities.Percents;
import entities.Question;
import entities.SurveyResult;
import entities.TSQRelation;
import entities.Testsurvey;
import entities.TestsurveyResult;
import entities.User;
import entities.UserAnswer;
import entities.UserResult;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.Format;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import util.SessionUtils;

/**
 *
 * @author vasoj
 */
@ManagedBean
@Named(value="testsurveyController")
@SessionScoped
public class TestsurveyController implements Serializable {
    String description;
    int creator_id;
    Date dateofstart;
    Date dateofend;
    String name;
    int type;
    int timeleft;
    
    String poruka;
    
    boolean dateofstartasc;
    boolean dateofendasc;
    boolean nameasc;
    
    List<Testsurvey> testsurveys;
    List<Question> questions;
    List<Answer> answers;
    List<TSQRelation> tsqrelations;
    List<UserAnswer> useranswers;
    List<List<UserAnswer>> uans;
    List<UserResult> list;
    List<SurveyResult> listsurvey;
    List<Percents> percentslist;
    
    Testsurvey testsurvey;
    Question question;
    
    String questionstring;
    int questiontype;
    int numberOfAnswers;
    
    int possibleanswers;
    int trueanswers;
    String bruh;
    String[] bruhs;
    int user_id;
    boolean tscontrollerend;
    boolean littletime;

    public List<Percents> getPercentslist() {
        return percentslist;
    }

    public void setPercentslist(List<Percents> percentslist) {
        this.percentslist = percentslist;
    }

    
    
    

    public boolean isLittletime() {
        return littletime;
    }

    public void setLittletime(boolean littletime) {
        this.littletime = littletime;
    }
    
    

    public List<SurveyResult> getListsurvey() {
        return listsurvey;
    }

    public void setListsurvey(List<SurveyResult> listsurvey) {
        this.listsurvey = listsurvey;
    }
    
    

    public List<UserResult> getList() {
        return list;
    }

    public void setList(List<UserResult> list) {
        this.list = list;
    }
    

    public boolean isDateofstartasc() {
        return dateofstartasc;
    }

    public void setDateofstartasc(boolean dateofstartasc) {
        this.dateofstartasc = dateofstartasc;
    }

    public boolean isDateofendasc() {
        return dateofendasc;
    }

    public void setDateofendasc(boolean dateofendasc) {
        this.dateofendasc = dateofendasc;
    }

    public boolean isNameasc() {
        return nameasc;
    }

    public void setNameasc(boolean nameasc) {
        this.nameasc = nameasc;
    }

    public List<UserAnswer> getUseranswers() {
        return useranswers;
    }

    public void setUseranswers(List<UserAnswer> useranswers) {
        this.useranswers = useranswers;
    }

    public List<List<UserAnswer>> getUans() {
        return uans;
    }

    public void setUans(List<List<UserAnswer>> uans) {
        this.uans = uans;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isTscontrollerend() {
        return tscontrollerend;
    }

    public void setTscontrollerend(boolean tscontrollerend) {
        this.tscontrollerend = tscontrollerend;
    }
    
    

    public String[] getBruhs() {
        return bruhs;
    }

    public void setBruhs(String[] bruhs) {
        this.bruhs = bruhs;
    }
    

    public String getBruh() {
        return bruh;
    }

    public void setBruh(String bruh) {
        this.bruh = bruh;
    }
    
    

    public TestsurveyController() {
        testsurveyFillingList();
        questionFillingList();
        dateofstartasc=true;
        dateofendasc=true;
        nameasc=true;
    }

    public List<TSQRelation> getTsqrelations() {
        return tsqrelations;
    }

    public void setTsqrelations(List<TSQRelation> tsqrelations) {
        this.tsqrelations = tsqrelations;
    }
    

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getPossibleanswers() {
        return possibleanswers;
    }

    public void setPossibleanswers(int possibleanswers) {
        this.possibleanswers = possibleanswers;
    }

    public int getTrueanswers() {
        return trueanswers;
    }

    public void setTrueanswers(int trueanswers) {
        this.trueanswers = trueanswers;
    }
    
    

    public int getNumberOfAnswers() {
        return numberOfAnswers;
    }

    public void setNumberOfAnswers(int numberOfAnswers) {
        this.numberOfAnswers = numberOfAnswers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    
    

    public Testsurvey getTestsurvey() {
        return testsurvey;
    }

    public void setTestsurvey(Testsurvey testsurvey) {
        this.testsurvey = testsurvey;
    }

    public String getQuestionstring() {
        return questionstring;
    }

    public void setQuestionstring(String questionstring) {
        this.questionstring = questionstring;
    }

    public int getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(int questiontype) {
        this.questiontype = questiontype;
    }
    
    

    public List<Testsurvey> getTestsurveys() {
        return testsurveys;
    }

    public void setTestsurveys(List<Testsurvey> testsurveys) {
        this.testsurveys = testsurveys;
    }
    
    

    public int getTimeleft() {
        return timeleft;
    }

    public void setTimeleft(int timeleft) {
        this.timeleft = timeleft;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
    
    

    public boolean isCreator() {
        return ((int)SessionUtils.getSession().getAttribute("type")==2);
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    

    public Date getDateofend() {
        return dateofend;
    }

    public void setDateofend(Date dateofend) {
        this.dateofend = dateofend;
    }

    public Date getDateofstart() {
        return dateofstart;
    }

    public void setDateofstart(Date dateofstart) {
        this.dateofstart = dateofstart;
    }

    

    


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
    
    public boolean isTest() {
        return type==1;
    }
    
    
    
    public void testsurveyFillingList() {
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Criteria cr=session.createCriteria(Testsurvey.class);
        
        testsurveys=cr.list();
        
        session.getTransaction().commit();
        session.close();
        
        
    }
    
    
    public String testsurveyCreationPage(String username) {
        
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Testsurvey ts=new Testsurvey();
        String query="select id from users u where u.username= '"+
                username + "'";
        int cr_id=(int)session.createQuery(query).uniqueResult();
        ts.setCreator_id(cr_id);
        
        session.flush();
        session.clear();
        
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(dateofstart.getTime());
        ts.setDateofstart(sqlTimestamp);
        sqlTimestamp = new java.sql.Timestamp(dateofend.getTime());
        ts.setDateofend(sqlTimestamp);
        ts.setDescription(description);
        ts.setName(name);
        ts.setType(type);
        ts.setTimeleft(timeleft);
        
        testsurvey=ts;
        questiontype=1;
        
        session.save(ts);
        
        session.getTransaction().commit();
        session.close();
        questionFillingList();
        
        return "questions";
    }
    
    public void sortByName() {
        Collections.sort(testsurveys, new Comparator<Testsurvey>() {
            public int compare(Testsurvey item1, Testsurvey item2) {
                return item1.getName().compareTo(item2.getName());
            }
        });
        if(!nameasc) {
            Collections.reverse(testsurveys);
            nameasc=true;
        } else nameasc=false;
    }
    
    public void sortByDateOfStart() {
        Collections.sort(testsurveys, new Comparator<Testsurvey>() {
            @Override
            public int compare(Testsurvey item1, Testsurvey item2) {
                Timestamp date1 = item1.getDateofstart();
                Timestamp date2 = item2.getDateofstart();
                return date1.compareTo(date2);
            }
});
        if(!dateofstartasc) {
            Collections.reverse(testsurveys);
            dateofstartasc=true;
        } else dateofstartasc=false;
    }
    
    public void sortByDateOfEnd() {
        Collections.sort(testsurveys, new Comparator<Testsurvey>() {
            @Override
            public int compare(Testsurvey item1, Testsurvey item2) {
                Timestamp date1 = item1.getDateofend();
                Timestamp date2 = item2.getDateofend();
                return date1.compareTo(date2);
            }
});
        if(!dateofendasc) {
            Collections.reverse(testsurveys);
            dateofendasc=true;
        } else dateofendasc=false;
    }
    
    public String testsurveyQuestion(Testsurvey ts,String username) {
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        String query="select id from users u where u.username= '"+
                username + "'";
        int cr_id=(int)session.createQuery(query).uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        
        testsurvey=ts;
        
         HttpSession s=SessionUtils.getSession();
        
        if(ts.getCreator_id()==cr_id&&ts.getType()==2){
            surveyResultsFiller(ts);
            return "surveyresults";
        } else
        if(ts.getCreator_id()==cr_id&&ts.getType()==1) {
            testResultsFiller(ts);
            return "testresults";
        } else {
            testsurveyQuestionFiller(ts,cr_id);
            return "testsurveying";
        }
    }
    
    public String addQuestion(String tsname) {
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        String query="select id from testsurvey ts where ts.name= '"
                +tsname+ "'";
        int ts_id=(int)session.createQuery(query).uniqueResult();
        
        session.flush();
        session.clear();
        
        Question qt=new Question();
        
        qt.setQuestiontext(questionstring);
        if(numberOfAnswers==0) numberOfAnswers=1;
        qt.setNumberofanswers(numberOfAnswers);
        qt.setQuestiontype(questiontype);
        
        question=qt;
        
        session.save(qt);
        
        session.flush();
        session.clear();
        
        String query2="select id from testsurveyquestion tsq where tsq.questiontext= '"
                +qt.getQuestiontext()+ "'";
        int tsq_id=(int)session.createQuery(query2).uniqueResult();
        
        TSQRelation tsqr=new TSQRelation();
        
        tsqr.setTestsurvey_id(ts_id);
        tsqr.setTestsurveyquestion_id(tsq_id);
        
        session.save(tsqr);
        
        if(testsurvey.getType()==1||questiontype==4||questiontype==5||questiontype==6) {
        
        Answer ans=new Answer();
        
        for(int i=0;i<numberOfAnswers;i++) {
            
            session.flush();
            session.clear();
            
            ans.setAnswertext("");
            ans.setAnswernumber(i+1);
            ans.setTestquestion_id(tsq_id);
            
            session.save(ans);
            
        }
        
        session.flush();
        session.clear();
        
        Criteria cr=session.createCriteria(Answer.class);
        
        answers=cr.add(Restrictions.eq("testquestion_id",tsq_id)).list();
        
        }
        
        session.getTransaction().commit();
        session.close();
        
        if(testsurvey.getType()==1||questiontype==4||questiontype==5||questiontype==6)
        return "addanswers";
        
        return "questions";
        
        
    }

    private void questionFillingList() {
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Criteria cr=session.createCriteria(Question.class);
        
        questions=cr.list();
        
        session.getTransaction().commit();
        session.close();
    }
    
    public void addQuestionToTest(Question qt,Testsurvey tsurvey) {
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        String query="select id from testsurvey ts where ts.name= '"
                +tsurvey.getName()+ "'";
        int ts_id=(int)session.createQuery(query).uniqueResult();
        
        session.flush();
        session.clear();
        
        query="select id from testsurveyquestion tsq where tsq.questiontext= '"
                +qt.getQuestiontext()+ "'";
        int tsq_id=(int)session.createQuery(query).uniqueResult();
        
        session.flush();
        session.clear();
        
        TSQRelation tsqr=new TSQRelation();
        
        tsqr.setTestsurvey_id(ts_id);
        tsqr.setTestsurveyquestion_id(tsq_id);
        
        session.save(tsqr);
        
        session.getTransaction().commit();
        session.close();
    }
    
    public boolean isMultipleChoice() {
        return ((questiontype==4||questiontype==5||questiontype==6)&&type==1);
    }
    
    public boolean isCheckbox() {
        return (questiontype==6&&type==1);
    }
    // vazi za sve sa surveya, los naziv
    public boolean isSurveyNumAndSh() {
        return (type==2&&questiontype!=3);
    }
    
    public String updateAnswers() {
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        String query;
        
        String query2="select id from testsurveyquestion tsq where tsq.questiontext= '"
                +question.getQuestiontext()+ "'";
        int tsq_id=(int)session.createQuery(query2).uniqueResult();
        
        
        for(int i=1;i<numberOfAnswers+1;i++) {
            session.flush();
            session.clear();
            
            query="from testsurveyanswer where "
                    +"answernumber = '" + i + "' "
                    +"and testquestion_id = '"+tsq_id+"'";
            Answer a=(Answer)session.createQuery(query).uniqueResult();
            a.setAnswertext(answers.get(i-1).getAnswertext());
            a.setIstrue(answers.get(i-1).getIstrue());
            
            session.update(a);
        }
        
        
        session.getTransaction().commit();
        session.close();
        return "questions";
    }
    
    public void singleAnswerCheckBoxUpdate(int ist,int anumber) {
        Answer a =answers.get(anumber-1);
        a.setIstrue(ist);
        answers.set(anumber-1,a);
    }

    public void testsurveyQuestionFiller(Testsurvey ts,int userid) {
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Criteria cr=session.createCriteria(TSQRelation.class);
        
        tsqrelations=cr.add(Restrictions.eq("testsurvey_id", ts.getId())).list();
        
        questions=new ArrayList();
        
        for(TSQRelation tsqr: tsqrelations) {
            session.flush();
            session.clear();
            
            cr=session.createCriteria(Question.class);
            
            
            
            Question q=(Question) cr.add(Restrictions.eq("id", tsqr.getTestsurveyquestion_id())).uniqueResult();
            
            session.flush();
            session.clear();
                
            
//            if(ts.getType()==1)    {
                cr=session.createCriteria(Answer.class);
                List ansss=cr.add(Restrictions.eq("testquestion_id",q.getId())).list();
                Collections.shuffle(ansss);
                q.setAnswers(ansss);
                
                session.flush();
                session.clear();
//            }
//            else {
//                ArrayList<Answer> anslist=new ArrayList(q.getNumberofanswers());
//                for(int j=0;j<q.getNumberofanswers();j++) {
//                    Answer an=new Answer();
//                    an.setTestquestion_id(q.getId());
//                    anslist.add(an);
//                    
//                    session.save(an);
//                    
//                    session.flush();
//                    session.clear();
//                    
//                }
//                cr=session.createCriteria(Answer.class);
//                q.setAnswers(cr.add(Restrictions.eq("testquestion_id",q.getId())).list());
//                
//                session.flush();
//                session.clear();
//            }
//            useranswers=new ArrayList<>();
//                for(int j=0;j<q.getNumberofanswers();j++) {
//                    UserAnswer useran=new UserAnswer();
//                    useran.setTestsurveyquestion_id(q.getId());
//                    useran.setTestsurveyanswer_id(q.getAnswers().get(j).getId());
//                    useran.setUser_id(userid);
//                    
//                    
//
//                    session.save(useran);
//                    useranswers.add(useran);
//                    
//                    session.flush();
//                    session.clear();
//                    
//                }            
//            q.setUseranswers(useranswers);
                
            
            q.setShow(false);
            q.setTruewrong(false);
            testsurvey=ts;
            questions.add(q);
            
            
            
            
    }
        Collections.shuffle(questions);
        int i=1;
        
        String qry="select timeleft from testsurvey where id='"+testsurvey.getId()+"'";
        timeleft=(int)session.createQuery(qry).uniqueResult();
        tscontrollerend=true;
        
        for(Question q:questions)
            q.setLocalnum(i++);
        session.getTransaction().commit();
        session.close();
    }
    
    public void submitAnswers(List<Question> qsts,String username) {
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        long numberoftrue=0;
        
        
        
        if(testsurvey.getType()==1) {
            for(Question q:qsts) {
                Criteria cr=session.createCriteria(Answer.class);
                Answer ans=(Answer)cr.add(Restrictions.eq("testquestion_id", q.getId())).add(Restrictions.eq("istrue", 1)).uniqueResult();
                if(ans!=null&&q.getSingleuseranswertext().equals(ans.getAnswertext())) {
                    numberoftrue++;
                    q.setTruewrong(true);
                    
                } else q.setTruewrong(false);
                q.setShow(true);
                session.flush();
                session.clear();
            }
            String query="select count(testsurvey_id) from testsurveyquestionrelation where testsurvey_id='"+testsurvey.getId()+"'";
            
            long totalquestions=(long)session.createQuery(query).uniqueResult();
            
            session.flush();
            session.clear();
            
            query="select id from users where username='"+username+"'";
            
            int u_id=(int)session.createQuery(query).uniqueResult();
            
            session.flush();
            session.clear();
            
            query="select count(*) from testsurveyresult where user_id='"+u_id+"'"+
                    " and testsurvey_id='"+testsurvey.getId()+"'";
            if((long)session.createQuery(query).uniqueResult()==0) {
            
            
            session.flush();
            session.clear();
            
            TestsurveyResult tsr=new TestsurveyResult();
            tsr.setPointpercent((int) (numberoftrue*100/totalquestions));
            tsr.setTestsurvey_id(testsurvey.getId());
            tsr.setUser_id(u_id);
            session.save(tsr); 
            
            }
        } else {
            for(Question q:qsts) {
                String anstext=q.getSingleuseranswertext();
                
                String query="select id from users where username='"+username+"'";
            
                int u_id=(int)session.createQuery(query).uniqueResult();

                session.flush();
                session.clear();
                
                int ts_id=testsurvey.getId();
                
                query="select id from testsurveyquestion where questiontext='"+q.getQuestiontext()+"'";
            
                int q_id=(int)session.createQuery(query).uniqueResult();

                session.flush();
                session.clear();
                
                query="select count(*) from useranswer where testsurveyquestion_id='"+q_id+"'"+
                        " and user_id='"+u_id+"'"+" and testsurvey_id='"+ts_id+"'";
                
                if((long)session.createQuery(query).uniqueResult()==0) {
                
                    UserAnswer ua=new UserAnswer();
                    ua.setTestsurvey_id(ts_id);
                    ua.setTestsurveyanswertext(anstext);
                    ua.setTestsurveyquestion_id(q_id);
                    ua.setUser_id(u_id);
                    session.save(ua);
                }
            }
        }
        
        tscontrollerend=true;
        questions=qsts;
        
        
        
        session.getTransaction().commit();
        session.close();
    }
    
    public String submitEnd(List<Question> qsts,String username) {
        for(Question q:questions) {
            q.setShow(false);
        }
        submitAnswers(qsts, username);
        tscontrollerend=false;
        return "login";
    }
    
    public void testResultsFiller(Testsurvey ts) {
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        String query="select user_id from testsurveyresult where testsurvey_id='"+ts.getId()+"'";
        
        List<Integer> lst=session.createQuery(query).list();
        session.flush();
        session.clear();
        list=new ArrayList();
        
        for(int i:lst) {
            UserResult us=new UserResult();
            query="select name from users where id='"+i+"'";
            String name=(String)session.createQuery(query).uniqueResult();
            
            session.flush();
            session.clear();
            
            query="select surname from users where id='"+i+"'";
            String surname=(String)session.createQuery(query).uniqueResult();
            
            session.flush();
            session.clear();
            
            query="select pointpercent from testsurveyresult where user_id='"+i+"' and testsurvey_id='"+ts.getId()+"'";
            int points=(int)session.createQuery(query).uniqueResult();
            
            session.flush();
            session.clear();
            
            us.setName(name);
            us.setSurname(surname);
            us.setPoints(points);
            list.add(us);
        }
        
       // percentsFiller();
        
        session.getTransaction().commit();
        session.close();
    }
    
    public void decrement(List<Question> qsts,String username) {
        if(timeleft==1) {
            submitAnswers(qsts,username);
            tscontrollerend=false;
        }
        if(timeleft==0) {
            return;
        }
        if(timeleft<10) littletime=true;
        else littletime=false;
        timeleft--;
    }
    
    public void surveyResultsFiller(Testsurvey ts) {
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        String query="select distinct testsurveyquestion_id from useranswer where testsurvey_id='"+ts.getId()+"'";
        
        List<Integer> qlst=session.createQuery(query).list();
        
        session.flush();
        session.clear();
        listsurvey=new ArrayList();
        
        for(int i:qlst) {
            
            query="select questiontext from testsurveyquestion where id='"+i+"'";
            
            session.flush();
            session.clear();
            
            String qtext=(String)session.createQuery(query).uniqueResult();
            
            query="select distinct testsurveyanswertext from useranswer where testsurveyquestion_id='"+i+"'"
                    + " and testsurvey_id='"+ts.getId()+"'";
            
            session.flush();
            session.clear();
            
            List<String> anstextlist=session.createQuery(query).list();
            
            for(String anstext:anstextlist) {
                query="select count(*) from useranswer where testsurveyanswertext='"+anstext+"' "
                        +"and testsurveyquestion_id='"+i+"' and testsurvey_id='"+ts.getId()+"'";
                
                session.flush();
                session.clear();
                long numapps=(long)session.createQuery(query).uniqueResult();
                
                query="select count(*) from useranswer where testsurveyquestion_id='"+i+"'"
                        + " and testsurvey_id='"+ts.getId()+"'";
                
                session.flush();
                session.clear();
                long total=(long)session.createQuery(query).uniqueResult();
                SurveyResult sr=new SurveyResult();
                sr.setAnswertext(anstext);
                sr.setNumberofapps((int)numapps);
                sr.setQuestion(qtext);
                sr.setPercent((int) (numapps*100/total));
                sr.setTsname(ts.getName());
                listsurvey.add(sr);
            }
        }
        
        
        session.getTransaction().commit();
        session.close();
    }
    
    public void percentsFiller() {
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int i=1;
        percentslist=new ArrayList();
        String query="";
        while(i<100) {
            Percents pc=new Percents();
            pc.setPerclow(i);
            int j=i+9;
            pc.setPerchigh(j);
            
            query="select count(*) from testsurveyresult where pointpercent between '"+i+"' and '"+j+"'";
            
            session.flush();
            session.clear();
            pc.setValue((int)session.createQuery(query).uniqueResult());
        }
        
        session.getTransaction().commit();
        session.close();
    }
}
