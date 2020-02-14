/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import beans.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

/**
 *
 * @author vasoj
 */
public class adminDAO {

    public static void getAllUsers(List<User> users) throws SQLException {
        Connection con=DB.getInstance().getConnection();
        PreparedStatement ps=null;
        
        try {
            ps=con.prepareStatement("select * from users");
            
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()) {
                User user=new User();
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setChecked(0);
                user.setContactphone(rs.getString("contactPhone"));
                Date date=rs.getDate("dateOfBirth");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                user.setDateofbirth(dateFormat.format(date));
                user.setEmail(rs.getString("email"));
                user.setJMBG(rs.getString("jmbg"));
                user.setPlaceofbirth(rs.getString("placeOfBirth"));
                user.setPassword(rs.getString("password"));
                user.setType(rs.getInt("type"));
                user.setChecked(rs.getInt("checked"));
                user.setId(rs.getInt("id"));
                
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(adminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(ps!=null) ps.close();
            DB.getInstance().putConnection(con);
        }
    }
    
    public static void adminDeleteUser(User user) throws SQLException {
        Connection con=DB.getInstance().getConnection();
        PreparedStatement ps=null;
        
        try {
            ps=con.prepareStatement("delete from users where id=?");
            ps.setInt(1, user.getId());
            
            int rows=ps.executeUpdate();
            
            System.out.println(rows+" rows affected");
        } catch (SQLException ex) {
            Logger.getLogger(adminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(ps!=null) ps.close();
            DB.getInstance().putConnection(con);
        }
        
    }
    
    public static void adminSave(User user) throws ParseException, SQLException {
        Connection con=DB.getInstance().getConnection();
        PreparedStatement ps=null;
        
        String str="update users set username=?,password=?,name=?,surname=?,dateOfBirth=?,placeOfBirth=?,"+
                "jmbg=?,contactPhone=?,email=?,type=?,checked=? where id=?";
        
        try {
            ps=con.prepareStatement(str);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            java.util.Date date;
            date = new SimpleDateFormat("yyyy-MM-dd").parse(user.getDateofbirth());
            java.sql.Date trueDate = new java.sql.Date(date.getTime());
            ps.setDate(5, trueDate);
            ps.setString(6, user.getPlaceofbirth());
            ps.setString(7,user.getJMBG());
            ps.setString(8, user.getContactphone());
            ps.setString(9, user.getEmail());
            ps.setInt(10, user.getType());
            ps.setInt(11, user.getChecked());
            ps.setInt(12, user.getId());
            
            int rows=ps.executeUpdate();
            
            System.out.println(rows+" rows affected");
            
        } catch (SQLException ex) {
            Logger.getLogger(adminDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   finally {
                if(ps!=null) ps.close();
                DB.getInstance().putConnection(con);
        }
    }
    
    public static void adminApproveRegistration(User user) throws SQLException {
        Connection con=DB.getInstance().getConnection();
        PreparedStatement ps=null;
        
        try {
            ps=con.prepareStatement("update users set checked=1 where id=?");
            
            ps.setInt(1, user.getId());
            
            int rows=ps.executeUpdate();
            
            System.out.println(rows+" rows affected");
            
        } catch (SQLException ex) {
            Logger.getLogger(adminDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(ps!=null) ps.close();
            DB.getInstance().putConnection(con);
        }
    }
}
