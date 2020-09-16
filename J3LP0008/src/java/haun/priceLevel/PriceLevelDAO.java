/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.priceLevel;

import haun.fromPlace.FromPlaceDTO;
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
public class PriceLevelDAO implements Serializable{
    List<PriceLevelDTO> listPriceLevel;

    public List<PriceLevelDTO> getListPriceLevel() {
        return listPriceLevel;
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
                String sql = "Select priceLevel, priceLevelName, priceFrom, priceTo from PriceLevel";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. process
                while(rs.next()) {
                    int priceLevel = rs.getInt("priceLevel");
                    String priceLevelName = rs.getString("priceLevelName");
                    float priceFrom = rs.getFloat("priceFrom");
                    float priceTo = rs.getFloat("priceTo");
                    
                    PriceLevelDTO dto = new PriceLevelDTO(priceLevel, priceLevelName, priceFrom, priceTo);
                    
                    if(listPriceLevel == null){
                        listPriceLevel = new ArrayList<>();
                    }
                    
                    listPriceLevel.add(dto);
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
