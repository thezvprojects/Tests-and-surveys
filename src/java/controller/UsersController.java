/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.User;
import db.NewHibernateUtil;
import entities.Testsurvey;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import util.SessionUtils;
import util.dao.LoginDAO;
import util.dao.PassChangeDAO;
import util.dao.RegisterDAO;
import util.dao.adminDAO;

/**
 *
 * @author vasoj
 */
@ManagedBean
@Named(value="usersController")
@SessionScoped
public class UsersController implements Serializable {
    String username;
    String password;
    
    String oldpassword;
    String newpassword;
    String confirmnewpassword;
    
    String name;
    String surname;
    String passwordconf;
    String dateofbirth;
    String placeofbirth;
    String JMBG;
    String contactphone;
    String email;
    
    int type;
    int testorsurvey;
    
    List<User> users;
    
    List<Testsurvey> testsurveys;
    
    String poruka;
    
    Testsurvey ts;
    
    public UsersController() {
        poruka="";
        
    }

    public Testsurvey getTs() {
        return ts;
    }

    public void setTs(Testsurvey ts) {
        this.ts = ts;
    }

    public int getTestorsurvey() {
        return testorsurvey;
    }

    public void setTestorsurvey(int testorsurvey) {
        this.testorsurvey = testorsurvey;
    }

    
    
    
    

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getConfirmnewpassword() {
        return confirmnewpassword;
    }

    public void setConfirmnewpassword(String confirmnewpassword) {
        this.confirmnewpassword = confirmnewpassword;
    }
    
    

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
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

    public String getPasswordconf() {
        return passwordconf;
    }

    public void setPasswordconf(String passwordconf) {
        this.passwordconf = passwordconf;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getPlaceofbirth() {
        return placeofbirth;
    }

    public void setPlaceofbirth(String placeofbirth) {
        this.placeofbirth = placeofbirth;
    }

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
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
    
    
    
    public String login() throws SQLException {
        User user=LoginDAO.getUserByUserAndPassword(username,password);
        if(user==null) {
            resetController();
            poruka="Neispravni podaci!";
            return "greska";
        }
        
        
        HttpSession session=SessionUtils.getSession();
        session.setAttribute("user", user);
        session.setAttribute("username", user.getUsername());
        session.setAttribute("type",user.getType());
        session.setAttribute("userid",user.getId());
        poruka="Ispravni podaci!";
        if(user.getType()==3) { 
            getUsersInList();
            resetController();
            return "adminpage";
        }
        if(user.getChecked()==0) { 
            poruka="Potrebna je konfirmacija od strane administratora!";
            return "greska";
            
        }
        
        
        return "login";
    }
    
    public String logout() {
        SessionUtils.getSession().invalidate();
        resetController();
        return "index";
    }
    
    public String register() throws SQLException {
        String pass=password;
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        boolean matches = Pattern.matches(regex, pass);
        if(!matches) {
            poruka="Los password, greska u poklapanju sa regexom";
            return "greska";
        }
        boolean check = RegisterDAO.tryInsertNewUser(name,surname,
                username,password,passwordconf,
                dateofbirth,placeofbirth,
                JMBG,contactphone,email,type,0);
        if(!check) {
            poruka="Neispravni podaci za registraciju!";
            return "greska";
        }
        return "registration";
    }
    
    public String adminRegister() throws SQLException {
        boolean check=false;
        String pass=password;
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        boolean matches = Pattern.matches(regex, pass);
        if(!matches) {
            poruka="Los password, greska u poklapanju sa regexom";
            return "greska";
        }
        try{
        check = RegisterDAO.tryInsertNewUser(name,surname,
                username,password,passwordconf,
                dateofbirth,placeofbirth,
                JMBG,contactphone,email,type,1); }
        catch (SQLException e) {
                return "greska";
                }
        if(!check) return "greska";
        return "adminpage";
    }
    
    public void resetController() {
        username="";
        password="";
        poruka="";
    }
    
    public void approveRegistration(User user) throws SQLException {
        user.setChecked(1);
        adminDAO.adminApproveRegistration(user);
    }
    
    public void adminSave(User user) throws ParseException, SQLException {
        adminDAO.adminSave(user);
    }
    
    public void adminDeleteUser(User user) throws SQLException {
        adminDAO.adminDeleteUser(user);
        users.remove(user);
    }
    
    public String changePass() throws SQLException {
        HttpSession session=SessionUtils.getSession();
        String username=SessionUtils.getUserName();
        
        ///poredjenje new sa confirm new
        if(newpassword.compareTo(confirmnewpassword)!=0) {
            poruka="New password and confirmed password do not match";
            return "greska";
        }
        
        ///poredjenje nove sa regex
        String pass=newpassword;
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        boolean matches = pass.matches(regex);
        if(!matches) {
            poruka="Bad password, mistake in matching with regex pattern";
            return "greska";
        }
        
        ///trazenje user i pass u bazi da li postoji
        if(LoginDAO.getUserByUserAndPassword(username,oldpassword)==null){
            poruka="You made a mistake typing the password you are using";
            return "greska";
        }
        
        PassChangeDAO.changePasswordInDatabase(SessionUtils.getUserName(),newpassword);
        
        
        return "index";    
    }

    private void getUsersInList() throws SQLException {
        users=new ArrayList();
        adminDAO.getAllUsers(users);
    }
    
    public String goChangePass() {
        return "passchanging";
    }
    
    public boolean isAdmin() {
        return (type==3);
    }
    
    public boolean isCreator() {
        return ((int)SessionUtils.getSession().getAttribute("type")==2);
    }
    
    public String testsurveyCreationPage() {
        if(testorsurvey==1)
        return "testcreate";
        else if(testorsurvey==2) return "surveycreate";  
        poruka="Greska oko biranja kreacije testa ili ankete";
        return "greska";
    }
    
    public void getTestSurveyById(int id) {
        Session session= db.NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria cr = session.createCriteria(Testsurvey.class);
        
        Testsurvey t =(Testsurvey) cr.add(Restrictions.eq("id",id)).uniqueResult();
        ts=t;
        
        session.getTransaction().commit();
        session.close();
    }
    
    
}
