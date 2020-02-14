/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

/**
 *
 * @author vasoj
 */
public class RegisterDAO {

    public static boolean tryInsertNewUser(String name, String surname, String username,
                String password, String passwordconf,String dateofbirth,String placeofbirth,
                String jmbg, String contactphone,String email, int type,int checked) throws SQLException {
        
        
        
        if(password.compareTo(passwordconf)!=0) return false;
        Connection con=DB.getInstance().getConnection();
        PreparedStatement ps1=null;
        
        /////// PROVERA ZA 2 EMAILA I POKLAPANJE PASSWORDA!
        
        try {
            ps1=con.prepareStatement("select count(*) as total from users "+
                    "where email=?");
            ps1.setString(1, email);
            
            ResultSet rs=ps1.executeQuery();
            
            if(rs.next()) {
                if(rs.getInt("total")>1) return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(ps1!=null) ps1.close();
            DB.getInstance().putConnection(con);
        }
        
        /////////// ACTUAL INSERT
        
        java.util.Date date;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateofbirth);
        } catch (ParseException ex) {
            Logger.getLogger(RegisterDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        java.sql.Date trueDate = new java.sql.Date(date.getTime());
        con=DB.getInstance().getConnection();
        PreparedStatement ps2=null;
        String stmt="insert into users (username,password,name,surname,"
                + "dateOfBirth,placeOfBirth,jmbg,contactPhone,"
                +"email,type,checked) values(?,?,?,?,?,?,?,?,?,?,?)";
        
        ps2=con.prepareStatement(stmt);
        ps2.setString(1, username);
        ps2.setString(2, password);
        ps2.setString(3, name);
        ps2.setString(4, surname);
        ps2.setDate(5, trueDate);
        ps2.setString(6, placeofbirth);
        ps2.setString(7,jmbg);
        ps2.setString(8, contactphone);
        ps2.setString(9, email);
        ps2.setInt(10, type);
        //checking
        ps2.setInt(11, checked);
        
        int rows=ps2.executeUpdate();
        
        System.out.println(rows);
        if(ps2!=null) ps2.close();
        DB.getInstance().putConnection(con);
        
        return true;
    }
    
}
