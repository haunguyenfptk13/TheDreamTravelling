/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haun.controller;

import haun.registration.RegistrationCreateError;
import haun.registration.RegistrationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class LoginServlet extends HttpServlet {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoginServlet.class);
    private final String INVALID_PAGE = "login.jsp";
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

        String url = INVALID_PAGE;
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");

        RegistrationCreateError errors = new RegistrationCreateError();
        boolean foundErr = false;
        try {
            //check username
            if (username.trim().length() < 6 || username.trim().length() > 50) {
                errors.setUsernameLengthErr("Username is required input from 6 to 50 characters");
                foundErr = true;
            }
            //check password
            if (password.trim().length() < 6 || password.trim().length() > 50) {
                errors.setPasswordLengthErr("Password is required input from 6 to 50 characters");
                foundErr = true;
            }
            if (foundErr) {
                request.setAttribute("CREATEERROR", errors);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {
                HttpSession session = request.getSession();
                // call DAO checklogin and get Role
                RegistrationDAO dao = new RegistrationDAO();
                boolean result = dao.checkLogin(username, password);
                int role = dao.getRole(username);
                String fullname  = dao.getFullname(username);
                if (result) {
                    if (role == 1) {
                        url = SEARCH_PAGE;
                        session.setAttribute("USERNAME", username);
                        session.setAttribute("ADMIN", role);
                        session.setAttribute("FULLNAME", fullname);
                    } else {
                        url = SEARCH_PAGE;
                        session.setAttribute("USERNAME", username);
                        session.setAttribute("CUSTOMER", role);
                        session.setAttribute("FULLNAME", fullname);
                    }
                    response.sendRedirect(url);
                } else {
                    // call DAO check is exist
                    boolean isExisted = dao.checkUsernameExist(username);
                    if (isExisted) {
                        errors.setPasswordIncorrect("Password incorrect");
                    } else {
                        errors.setUsernameNotExist("Username is not exist");
                    }
                    request.setAttribute("CREATEERROR", errors);
                    RequestDispatcher rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }
            }
        } catch (NamingException ex) {
            logger.error("LoginServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("LoginServlet_SQL " + ex.getMessage());
        } finally {
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
