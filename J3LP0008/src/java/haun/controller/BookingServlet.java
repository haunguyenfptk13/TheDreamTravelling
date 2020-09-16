/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.controller;

import haun.cart.CartObjects;
import haun.discountRegistration.DiscountRegistrationDAO;
import haun.order.OrderDAO;
import haun.tour.TourDAO;
import haun.tour.TourDTO;
import haun.tourOrder.TourOrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
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
public class BookingServlet extends HttpServlet {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BookingServlet.class);
    private final String ERROR_PAGE = "viewCart.jsp";
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

        String url = ERROR_PAGE;

        float totalPay = Float.parseFloat(request.getParameter("txtTotalPay"));
        try {
            HttpSession session = request.getSession();
            //call DAO check tour is out of stock
            CartObjects cart = (CartObjects) session.getAttribute("CART");
            boolean result = true;
            if (cart != null) {
                Set<TourDTO> set = cart.getItems().keySet();
                for (TourDTO key : set) {
                    int amount = cart.getItems().get(key);
                    String tourID = key.getTourID();
                    TourDAO tDao = new TourDAO();
                    boolean temp = tDao.checkStock(tourID, amount);
                    if (!temp) { // if check fail
                        String errorStock = (String) request.getAttribute("ERRORSTOCK");
                        if (errorStock != null) {
                            errorStock = tourID + ", " + errorStock;
                            request.setAttribute("ERRORSTOCK", errorStock);
                        } else {
                            String error = tourID + " is out of stock";
                            request.setAttribute("ERRORSTOCK", error);
                        }
                        result = false;
                    }
                }
            }
            if (result) {// if check stock success ->>
                //get username
                String username = (String) session.getAttribute("USERNAME");
                //get Date
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateNow = sdf.format(date);
                //call DAO booking
                OrderDAO dao = new OrderDAO();
                String discountID = (String) session.getAttribute("DISCOUNTID");
                boolean result1 = dao.checkoutOrder(totalPay, username, dateNow, discountID);
                if (result1) {// if booking success
                    //call DAO store discountCode info for user
                    DiscountRegistrationDAO drDao = new DiscountRegistrationDAO();
                    boolean result2 = drDao.useDiscount(discountID, username);
                    //call DAO booking detail
                    //get last id from Order
                    int lastOrderID = dao.getLastOrderID();
                    //store order detail to database
                    boolean result3 = true;
                    Set<TourDTO> set = cart.getItems().keySet();
                    for (TourDTO key : set) {
                        TourOrderDAO toDao = new TourOrderDAO();
                        int amount = cart.getItems().get(key);
                        float total = amount * key.getPrice();
                        boolean insert = toDao.insertOrderDetail(lastOrderID, key.getTourID(), key.getPrice(), amount, total);
                        if (!insert) {// if insert fail
                            result3 = false;
                        }
                    }
                    //process
                    if (result2 && result3) {// if update status discount of customer success and store order detail success
                        int totalAmount = 0;
                        session.setAttribute("TOTALAMOUNT", totalAmount);
                        session.removeAttribute("CART");
                        session.removeAttribute("TOTALCARTS");
                        session.removeAttribute("DISCOUNTPERCENT");
                        session.removeAttribute("DISCOUNTID");
                        url = SEARCH_PAGE;
                    }
                }
            }

        } catch (NamingException ex) {
            logger.error("BookingServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("BookingServlet_SQL + " + ex.getMessage());
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
