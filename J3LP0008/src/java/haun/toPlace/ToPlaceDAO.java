/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.toPlace;

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
public class ToPlaceDAO implements Serializable{
    private List<ToPlaceDTO> listToPlace;

    public List<ToPlaceDTO> getListToPlace() {
        return listToPlace;
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
                String sql = "Select toPlaceID, toPlaceName from ToPlace";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. process
                while(rs.next()) {
                    String toPlaceID = rs.getString("toPlaceID");
                    String toPlaceName = rs.getString("toPlaceName");
                    
                    ToPlaceDTO dto = new ToPlaceDTO(toPlaceID, toPlaceName);
                    
                    if(listToPlace == null){
                        listToPlace = new ArrayList<>();
                    }
                    
                    listToPlace.add(dto);
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
