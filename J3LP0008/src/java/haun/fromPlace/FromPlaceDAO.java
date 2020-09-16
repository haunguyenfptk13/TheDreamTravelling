/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.fromPlace;

import haun.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author msi
 */
public class FromPlaceDAO implements Serializable{
    List<FromPlaceDTO> listFromPlace;

    public List<FromPlaceDTO> getListFromPlace() {
        return listFromPlace;
    }
    
    public void getListName()
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Open connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQLs
                String sql = "Select fromPlaceID, fromPlaceName from FromPlace";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. process
                while(rs.next()) {
                    String fromPlaceID = rs.getString("fromPlaceID");
                    String fromPlaceName = rs.getString("fromPlaceName");
                    
                    FromPlaceDTO dto = new FromPlaceDTO(fromPlaceID, fromPlaceName);
                    
                    if(listFromPlace == null){
                        listFromPlace = new ArrayList<>();
                    }
                    
                    listFromPlace.add(dto);
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
    }
}
