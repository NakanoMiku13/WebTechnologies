/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webdevelopment.cramersolution;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author miku
 */
public class TableCreator extends HttpServlet {

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
        String option = request.getParameter("option"),op1="autoFill";
        int size = Integer.parseInt(request.getParameter("size"));
        String input = "<div class='form-floating'><input type='number' value='%d' class='form-control' placeholder='%c%d' id='%c%d' name='%c%d' required/><label for='%c%d'>%c<sub>%d</sub></label></div>";
        String input2 = "<div class='form-floating'><input type='number' class='form-control' placeholder='%c%d' id='%c%d' name='%c%d' required/><label for='%c%d'>%c<sub>%d</sub></label></div>";
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>TableCreator</title>");            
            out.println("<link rel='stylesheet' href='css/bootstrap.css'/>");
            out.println("</head>");
            out.println("<body class='container'>");
            out.println("<center><h1>Table</h1></center>");
            out.println("<form method='POST' action='CramerSolver'>");
            char let = 'a';
            out.println("<div class='row'>");
            out.println("<div class='col-8'>");
            for(int i = 0 ; i < size ; i++, let++){
                out.println("<div class='row'>");
                for(int j = 0 ; j < size ; j++){
                    int ran = (int)Math.floor(Math.random()*(100 - 1 + 1) + 1);
                    out.println("<div class='col'>");
                    if(option.equals(op1)) out.println(String.format(input,ran,let,j+1,let,j+1,let,j+1,let,j+1,let,j+1));
                    else out.println(String.format(input2,let,j+1,let,j+1,let,j+1,let,j+1,let,j+1));
                    out.println("</div>");
                }
                out.println("</div>");
            }
            out.println("</div>");
            out.println("<div class='col'>");
            char let2 = 'z';
            for(int i = 0 ; i < size ; i++){
                out.println("<div class='row'>");
                int ran = (int)Math.floor(Math.random()*(100 - 1 + 1) + 1);
                out.println("<div class='col'>");
                if(option.equals(op1)) out.println(String.format(input,ran,let2,i+1,let2,i+1,let2,i+1,let2,i+1,let2,i+1));
                else out.println(String.format(input2,let2,i+1,let2,i+1,let2,i+1,let2,i+1,let2,i+1));
                out.println("</div></div>");
            }
            out.println("</div>");
            out.println("</div>");
            out.println(String.format("<input type='number' name='size' value='%d' class='form-control' />",size));
            out.println("<div class='btn-group space'><button type='submit' class='btn btn-outline-success'>Subir</button><button type='reset' class='btn btn-outline-danger'>Limpiar</button></div>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
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
