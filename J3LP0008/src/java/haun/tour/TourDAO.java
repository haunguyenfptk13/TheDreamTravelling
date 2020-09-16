/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.tour;

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
public class TourDAO implements Serializable {

    public int getSizeResultSearch(String fromPlace, String dateFrom, String dateTo, float priceFrom, float priceTo, String toPlace)
            throws NamingException, SQLException {
        List<TourDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Open connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQLs
                String sql = "declare @fromPlaceName nvarchar(50), @dateFrom Date, @dateTo Date, @priceFrom float, @priceTo float, @toPlaceName nvarchar(50);\n"
                        + "set @fromPlaceName = ?;\n"
                        + "set @dateFrom = ?;\n"
                        + "set @dateTo = ?;\n"
                        + "set @priceFrom = ?;\n"
                        + "set @priceTo = ?;\n"
                        + "set @toPlaceName = ?;\n"
                        + "select COUNT(tourID) AS QuantityRows \n"
                        + "from Tour, FromPlace, ToPlace where \n"
                        + "(Tour.fromPlaceID = FromPlace.fromPlaceID) and \n"
                        + "(@fromPlaceName = '' OR fromPlaceName = @fromPlaceName) and \n"
                        + "(@dateFrom = '' OR fromDate >= @dateFrom) and \n"
                        + "(@dateTo = '' OR fromDate <= @dateTo) and \n"
                        + "(@priceFrom = -1 OR price >= @priceFrom) and \n"
                        + "(@priceTo = -1 OR price <= @priceTo) and\n"
                        + "(Tour.toPlaceID = ToPlace.toPlaceID) and \n"
                        + "(@toPlaceName = '' OR toPlaceName = @toPlaceName)";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, fromPlace);
                stm.setString(2, dateFrom);
                stm.setString(3, dateTo);
                stm.setFloat(4, priceFrom);
                stm.setFloat(5, priceTo);
                stm.setString(6, toPlace);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. process
                while (rs.next()) {
                    int quantityRows = rs.getInt("QuantityRows");
                    return quantityRows;
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

    public List<TourDTO> getPageDataTour(String fromPlace, String dateFrom,
            String dateTo, float priceFrom, float priceTo, String toPlace, int offset, int next)
            throws NamingException, SQLException {
        List<TourDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Open connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQLs
                String sql = "declare @fromPlaceName nvarchar(50), @dateFrom Date, @dateTo Date, @priceFrom float, @priceTo float, @toPlaceName nvarchar(50);\n"
                        + "set @fromPlaceName = ?;\n"
                        + "set @dateFrom = ?;\n"
                        + "set @dateTo = ?;\n"
                        + "set @priceFrom = ?;\n"
                        + "set @priceTo = ?;\n"
                        + "set @toPlaceName = ?;\n"
                        + "select tourID, tourName, price, fromDate, toDate, Tour.fromPlaceID, Tour.toPlaceID, image\n"
                        + "from Tour, FromPlace, ToPlace where \n"
                        + "(Tour.fromPlaceID = FromPlace.fromPlaceID) and \n"
                        + "(@fromPlaceName = '' OR fromPlaceName = @fromPlaceName) and \n"
                        + "(@dateFrom = '' OR fromDate >= @dateFrom) and \n"
                        + "(@dateTo = '' OR fromDate <= @dateTo) and \n"
                        + "(@priceFrom = -1 OR price >= @priceFrom) and \n"
                        + "(@priceTo = -1 OR price <= @priceTo) and\n"
                        + "(Tour.toPlaceID = ToPlace.toPlaceID) and \n"
                        + "(@toPlaceName = '' OR toPlaceName = @toPlaceName)\n"
                        + "ORDER BY price \n"
                        + "OFFSET ? ROWS \n"
                        + "FETCH NEXT ? ROWS ONLY";
                //3. Create Statement
                stm = con.prepareStatement(sql);
                stm.setString(1, fromPlace);
                stm.setString(2, dateFrom);
                stm.setString(3, dateTo);
                stm.setFloat(4, priceFrom);
                stm.setFloat(5, priceTo);
                stm.setString(6, toPlace);
                stm.setInt(7, offset);
                stm.setInt(8, next);
                //4. Excute Query
                rs = stm.executeQuery();
                //5. process
                while (rs.next()) {
                    String tourID = rs.getString("tourID");
                    String tourName = rs.getString("tourName");
                    float price = rs.getFloat("price");
                    String fromDate = rs.getString("fromDate");
                    String toDate = rs.getString("toDate");
                    int fromPlaceID = rs.getInt("fromPlaceID");
                    int toPlaceID = rs.getInt("toPlaceID");
                    String image = rs.getString("image");

                    TourDTO dto = new TourDTO(tourID, tourName, price, fromDate, toDate, fromPlaceID, toPlaceID, image);
                    list.add(dto);
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
        return list;
    }

    public boolean insertTour(String tourID, String tourName, float price, String fromDate, 
            int fromPlaceID, String image, int statusID, int toPlaceID, String toDate, 
            int quota, String dateImport) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into Tour(tourID, tourName, price, fromDate, "
                        + "fromPlaceID, image, statusID, toPlaceID, toDate, quota, dateImport) "
                        + "values(?,?,?,?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, tourID);
                stm.setString(2, tourName);
                stm.setFloat(3, price);
                stm.setString(4, fromDate);
                stm.setInt(5, fromPlaceID);
                stm.setString(6, image);
                stm.setInt(7, statusID);
                stm.setInt(8, toPlaceID);
                stm.setString(9, toDate);
                stm.setInt(10, quota);
                stm.setString(11, dateImport);
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
    
    public boolean checkStock(String tourID, int amount)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select tourID from Tour where tourID = ? and quota >= ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, tourID);
                stm.setInt(2, amount);
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
}
