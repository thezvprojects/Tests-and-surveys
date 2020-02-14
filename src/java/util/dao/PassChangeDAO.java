/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;

/**
 *
 * @author vasoj
 */
public class PassChangeDAO {
    
    public static void changePasswordInDatabase(String username,String newpass) throws SQLException {
        Connection con=DB.getInstance().getConnection();
        PreparedStatement ps=null;
        
        try {
            ps=con.prepareStatement("update users set password=? where username=?");
            
            ps.setString(1,newpass);
            ps.setString(2,username);
            
            int rows= ps.executeUpdate();
            
            System.out.println(rows+" rows updated");
            
        } catch (SQLException ex) {
            Logger.getLogger(PassChangeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(ps!=null) ps.close();
            DB.getInstance().putConnection(con);
        }
    }
}
