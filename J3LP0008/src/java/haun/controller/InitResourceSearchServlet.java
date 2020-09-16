/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.controller;

import haun.fromPlace.FromPlaceDAO;
import haun.fromPlace.FromPlaceDTO;
import haun.priceLevel.PriceLevelDAO;
import haun.priceLevel.PriceLevelDTO;
import haun.toPlace.ToPlaceDAO;
import haun.toPlace.ToPlaceDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
public class InitResourceSearchServlet extends HttpServlet {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InitResourceSearchServlet.class);
    private final String SEARCH_PAGE = "search.jsp";

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

        String url = SEARCH_PAGE;
        try {
            HttpSession session = request.getSession();
            //call DAO get list name of fromPlace
            FromPlaceDAO daoFrom = new FromPlaceDAO();
            daoFrom.getListName();
            List<FromPlaceDTO> listFromPlace = daoFrom.getListFromPlace();
            //call DAO get list name of toPlace
            ToPlaceDAO daoTo = new ToPlaceDAO();
            daoTo.getListName();
            List<ToPlaceDTO> listToPlace = daoTo.getListToPlace();

            //get List prices level;
            PriceLevelDAO daoPrice = new PriceLevelDAO();
            daoPrice.getListName();
            List<PriceLevelDTO> listPrice = daoPrice.getListPriceLevel();
            // set list into session
            session.setAttribute("FROMPLACELIST", listFromPlace);
            session.setAttribute("PRICELIST", listPrice);
            session.setAttribute("TOPLACELIST", listToPlace);
        } catch (NamingException ex) {
            logger.error("InitResourceSearchServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("InitResourceSearchServlet_SQL " + ex.getMessage());
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
