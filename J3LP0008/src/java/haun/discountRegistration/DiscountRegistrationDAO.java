/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.discountRegistration;

import haun.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author msi
 */
public class DiscountRegistrationDAO implements Serializable{
    public boolean checkStatusDiscount(String discountCode, String username)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select discountID from Discount_Registration "
                        + "where discountID = ? and statusID = 2 and userID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, discountCode);
                stm.setString(2, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean useDiscount(String discountCode, String username)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into Discount_Registration(discountID, userID, statusID) "
                        + "values(?,?,2)";
                stm = con.prepareStatement(sql);
                stm.setString(1, discountCode);
                stm.setString(2, username);
                int row = stm.executeUpdate();
                if(row > 0){
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
