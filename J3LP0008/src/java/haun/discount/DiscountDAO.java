/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.discount;

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
public class DiscountDAO implements Serializable {

    public boolean checkDiscountCode(String discountCode, String dateNow)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select discountID from Discount where discountID = ? and expiryDate >= ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, discountCode);
                stm.setString(2, dateNow);
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

    public int getValueDiscount(String discountCode)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select discountPercent from Discount where discountID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, discountCode);
                rs = stm.executeQuery();
                if (rs.next()) {
                    int discountPercent = rs.getInt("discountPercent");
                    return discountPercent;
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
        return 0;
    }
}
