/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.controller;

import haun.priceLevel.PriceLevelDTO;
import haun.tour.TourDAO;
import haun.tour.TourDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SearchServlet extends HttpServlet {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SearchServlet.class);
    private final String SEARCH_PAGE = "InitResourceSearchServlet";

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
        String fromPlace = request.getParameter("cboFromPlace");
        String toPlace = request.getParameter("cboToPlace");
        String dateFrom = request.getParameter("txtDateFrom");
        String dateTo = request.getParameter("txtDateTo");
        String cboPriceLevel = request.getParameter("cboPriceLevel");
        int pageNo = Integer.parseInt(request.getParameter("txtPageNo"));
        try {
            if (!fromPlace.equals("") || !dateFrom.equals("") || !dateTo.equals("") || !cboPriceLevel.equals("") || !toPlace.equals("")) {
                // call session get priceFrom & priceTo selected
                float priceFromValue, priceToValue;
                if (!cboPriceLevel.equals("")) {
                    HttpSession session = request.getSession();
                    List<PriceLevelDTO> priceList = (List<PriceLevelDTO>) session.getAttribute("PRICELIST");
                    PriceLevelDTO dto = priceList.get(Integer.parseInt(cboPriceLevel) - 1);
                    priceFromValue = dto.getPriceFrom();
                    priceToValue = dto.getPriceTo();
                } else {
                    priceFromValue = -1;
                    priceToValue = -1;
                }
                // call DAO for get size of result and setup attribute for paging
                TourDAO daoTour = new TourDAO();
                int recordTotal = daoTour.getSizeResultSearch(fromPlace, dateFrom, dateTo, priceFromValue, priceToValue, toPlace);
                int recordPage = 5;
                int pageNumber = recordTotal / recordPage;
                if (recordTotal > (pageNumber * recordPage)) {
                    pageNumber += 1;
                }
                int recordOffset = (pageNo - 1) * recordPage;
                // call DAO fot paging
                List<TourDTO> listTourPage = daoTour.getPageDataTour(fromPlace, dateFrom, dateTo, priceFromValue, priceToValue, toPlace, recordOffset, recordPage);
                request.setAttribute("LISTTOURPAGE", listTourPage);
                request.setAttribute("PAGENUMBER", pageNumber);
            }
        } catch (NamingException ex) {
           logger.error("SearchServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("SearchServlet_SQL " + ex.getMessage());
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
