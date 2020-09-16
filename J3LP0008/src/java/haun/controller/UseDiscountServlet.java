/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.controller;

import haun.discount.DiscountDAO;
import haun.discountRegistration.DiscountRegistrationDAO;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author msi
 */
public class UseDiscountServlet extends HttpServlet {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UseDiscountServlet.class);
    private final String VIEW_CART_PAGE = "viewCart.jsp";

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

        String url = VIEW_CART_PAGE;
        String discountCode = request.getParameter("txtDiscount");
        try {
            //get date
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = sdf.format(date);
            //call DAO check code
            DiscountDAO discountDAO = new DiscountDAO();
            boolean isActive = discountDAO.checkDiscountCode(discountCode, dateNow);
            if (isActive) {
                HttpSession session = request.getSession();
                String username = (String) session.getAttribute("USERNAME");
                //call DAO check status of discountCode
                DiscountRegistrationDAO drDao = new DiscountRegistrationDAO();
                boolean isUsed = drDao.checkStatusDiscount(discountCode, username);
                if (!isUsed) {
                    int discountPercent = discountDAO.getValueDiscount(discountCode);
                    session.setAttribute("DISCOUNTPERCENT", discountPercent);
                    session.setAttribute("DISCOUNTID", discountCode);
                    url = VIEW_CART_PAGE;
                } else {
                    String error = "Code is only use once";
                    request.setAttribute("ERROR1", error);
                }
            } else {
                String error = "Code is not exist or expired";
                request.setAttribute("ERROR2", error);
            }
        } catch (NamingException ex) {
            logger.error("UseDiscountServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("UseDiscountServlet_SQL " + ex.getMessage());
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
