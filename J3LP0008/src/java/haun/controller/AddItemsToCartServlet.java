/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.controller;

import haun.cart.CartObjects;
import haun.tour.TourDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author msi
 */
public class AddItemsToCartServlet extends HttpServlet {

    private final String SEARCH_PAGE = "InitResourceSearchServlet";
    private final String LOGIN_PAGE = "login.jsp";

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
        String tourID = request.getParameter("txtTourID");
        String tourName = request.getParameter("txtTourName");
        String price = request.getParameter("txtPrice");
        String fromDate = request.getParameter("txtFromDate");
        String toDate = request.getParameter("txtToDate");
        String fromPlaceID = request.getParameter("txtFromPlaceID");
        String toPlaceID = request.getParameter("txtToPlaceID");
        String image = request.getParameter("txtImage");
        try {
            //1.Users goes to carts place
            HttpSession session = request.getSession();
            //check login
            String username = (String) session.getAttribute("USERNAME");
            if (username == null) {
                url = LOGIN_PAGE;
            } else {
                //2. User take carts
                CartObjects cart = (CartObjects) session.getAttribute("CART");
                if (cart == null) {
                    cart = new CartObjects();
                }
                //3. User take items
                TourDTO dto = new TourDTO(tourID, tourName, Float.parseFloat(price),
                        fromDate, toDate, Integer.parseInt(fromPlaceID), Integer.parseInt(toPlaceID), image);
                //4. User drop items to cart
                cart.addItemToCart(dto);
                //5. Update cart
                int totalAmount = cart.totalAmountCart();
                session.setAttribute("TOTALAMOUNT", totalAmount);

                float totalCarts = cart.calculateTotalCarts();
                session.setAttribute("TOTALCARTS", totalCarts);

                session.setAttribute("CART", cart);
            }
        } finally {
            response.sendRedirect(url);
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
