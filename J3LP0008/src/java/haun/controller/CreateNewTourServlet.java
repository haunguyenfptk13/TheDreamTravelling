/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.controller;

import haun.tour.TourCreateError;
import haun.tour.TourDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author msi
 */
public class CreateNewTourServlet extends HttpServlet {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CreateNewTourServlet.class);
    private final String SEARCH_PAGE = "InitResourceSearchServlet";
    private final String CREATE_NEW_TOUR_PAGE = "createNewTour.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String tourID = request.getParameter("txtTourID");
        String tourName = request.getParameter("txtTourName");
        String fromPlaceID = request.getParameter("cboFromPlace");
        String toPlaceID = request.getParameter("cboToPlace");
        String fromDate = request.getParameter("txtFromDate");
        String toDate = request.getParameter("txtToDate");
        String price = request.getParameter("txtPrice");
        String quota = request.getParameter("txtQuota");
        String image = request.getParameter("txtImage");

        String url = CREATE_NEW_TOUR_PAGE;
        TourCreateError errors = new TourCreateError();
        boolean foundErr = false;
        try {
            if (tourID.trim().isEmpty()) {
                foundErr = true;
                errors.setTourIDLengthErr("Tour ID not be blank");
            }
            if (tourName.trim().isEmpty()) {
                foundErr = true;
                errors.setTourNameLengthErr("Tour Name not be blank");
            }
            if (fromDate.trim().isEmpty()) {
                foundErr = true;
                errors.setFromDateLengthErr("From Date not be blank");
            }
            if (toDate.trim().isEmpty()) {
                foundErr = true;
                errors.setToDateLengthErr("To Date not be blank");
            }
            if (!price.matches("\\d+")) {
                foundErr = true;
                errors.setPriceFormatErr("Price must be a number and greater than 0");
            }
            if (!quota.matches("\\d+")) {
                foundErr = true;
                errors.setQuotaFormatErr("Quota must be a number and greater than 0");
            }
            if (foundErr) {
                request.setAttribute("CREATEERROR", errors);
            } else {
                //get date
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateNow = sdf.format(date);
                //call DAO
                TourDAO dao = new TourDAO();
                boolean result = dao.insertTour(tourID, tourName, Float.parseFloat( price), 
                        fromDate, Integer.parseInt(fromPlaceID), "images/" + image, 1, Integer.parseInt(toPlaceID), 
                        toDate, Integer.parseInt(quota), dateNow);
                if(result){
                    url = SEARCH_PAGE;
                }
            }
        } catch (NamingException ex) {
            logger.error("CreateNewTourServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            logger.error("CreateNewTourServlet_SQL " + ex.getMessage());
            if(msg.contains("duplicate")){
                errors.setTourIDIsExisted(tourID + " is existed");
                request.setAttribute("CREATEERROR", errors);
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
