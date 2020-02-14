/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import beans.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

/**
 *
 * @author vasoj
 */
public class LoginDAO {

    public static User getUserByUserAndPassword(String username, String password) throws SQLException {
        Connection con=DB.getInstance().getConnection();
        PreparedStatement ps=null;
        try {
            ps=con.prepareStatement("select * from users "
                    +"where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()) {
                User user=new User();
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setUsername(rs.getString("username"));
                user.setType(rs.getInt("type"));
                user.setChecked(rs.getInt("checked"));
                
                return user;
                
            } 
        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(ps!=null) ps.close();
            DB.getInstance().putConnection(con);
        }
        return null;
    }
    
}
