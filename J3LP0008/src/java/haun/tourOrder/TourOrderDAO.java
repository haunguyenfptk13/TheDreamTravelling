/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.tourOrder;

import haun.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author msi
 */
public class TourOrderDAO implements Serializable{
    public boolean insertOrderDetail(int orderID, String tourID, float price, int amount, float total)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Insert into Tour_Order(orderID, tourID, price, amount, total) "
                        + "values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, orderID);
                stm.setString(2, tourID);
                stm.setFloat(3, price);
                stm.setInt(4, amount);
                stm.setFloat(5, total);
                int row = stm.executeUpdate();

                if (row > 0) {
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
