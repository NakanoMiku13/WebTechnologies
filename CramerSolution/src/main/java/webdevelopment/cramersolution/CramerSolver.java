/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webdevelopment.cramersolution;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.lang.Integer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author miku
 */
class Pair<First,Second>{
    public First first;
    public Second second;
    public Pair(First first,Second second){
        this.first = first;
        this.second = second;
    }
}
public class CramerSolver extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Vector<Vector<Integer>> getMatrix(Vector<Vector<Integer>> matrix, int x, int y){
        Vector<Vector<Integer>> retMatrix = new Vector<Vector<Integer>>();
        int size = matrix.size();
        for(int i = 0 ; i < size ; i++){
            Vector<Integer> row = new Vector<Integer>(), temp = matrix.elementAt(i);
            if(y == i) continue;
            for(int j = 0 ; j < size ; j++){
                if(j == x) continue;
                else row.add(temp.elementAt(j));
            }
            retMatrix.add(row);
        }
        return retMatrix;
    }
    private int getDeterminant(Vector<Vector<Integer>> matrix){
        if(matrix.size() == 1) return matrix.elementAt(0).elementAt(0);
        if(matrix.size() == 2){
            return matrix.elementAt(0).elementAt(0)*matrix.elementAt(1).elementAt(1) - matrix.elementAt(0).elementAt(1)*matrix.elementAt(1).elementAt(0);
        }else{
            int value = 0;
            for(int i = 0, p = 1 ; i < matrix.size() ; i++, p++){
                int val = matrix.elementAt(0).elementAt(i);
                value += (int)Math.pow((double)(-1), (double)(p+1))*val*getDeterminant(getMatrix(matrix,i,0));
            }
            return value;
        }
    }
    private Vector<Vector<Integer>> replaceColMatrix(Vector<Vector<Integer>> original, Vector<Integer> newCol, int colNumber){
        Vector<Vector<Integer>> matrix = new Vector<Vector<Integer>>();
        int size = original.size();
        for(int i = 0, k = 0 ; i < size ; i++){
            Vector<Integer> row = new Vector<Integer>();
            for(int j = 0 ; j < size ; j++){
                log(String.format("%d", k));
                if(j == colNumber){ row.add(newCol.elementAt(k)); k++;}
                else row.add(original.elementAt(i).elementAt(j));
            }
            matrix.add(row);
        }
        return matrix;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Vector<Vector<Integer>> matrix = new Vector<Vector<Integer>>();
        Vector<Integer> results = new Vector<Integer>();
        int size = Integer.parseInt(request.getParameter("size"));
        char let = 'a';
        String str;
        for(int i = 0; i < size ; i++, let++){
            Vector<Integer> row = new Vector<Integer>();
            for(int k = 0, j = 1 ; k < size ; k++,j++){
                str = request.getParameter(String.format("%c%d", let,j));
                row.add(Integer.parseInt(str));            
            }
            
            matrix.add(row);
        }
        int systemDeterminant = getDeterminant(matrix) ;
        for(int i = 0 ; i < size ; i++, results.add(Integer.parseInt(request.getParameter(String.format("z%d", i)))));
        Vector<Pair<String,Integer>> coefficients = new Vector<Pair<String,Integer>>();
        let = 'a';
        for(int i = 0 ; i < size ; i++, coefficients.add(new Pair<String,Integer>(String.format("%c",let),(int)(getDeterminant(replaceColMatrix(matrix,results,i))/systemDeterminant))),let++);
        if(systemDeterminant != 0){
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Cramer solver</title>");
                out.println("<link rel='stylesheet' href='css/bootstrap.css'/>");
                out.println("</head>");
                out.println("<body class='container'>");
                out.println("<h1>Crammer solver</h1>");
                out.println("<div class='row'>");
                out.println("<div class='col'>");
                out.println("<h2>System determinant: "+String.format("%d", systemDeterminant)+"</h2>");
                out.println("</div>");
                for(int i = 0 ; i < size ; i++){
                    Pair<String,Integer> coefficient = coefficients.elementAt(i);
                    out.println("<div class='col-2'>");
                    out.println("<h2>"+coefficient.first+" = "+coefficient.second+"</h2>");
                    out.println("</div>");
                }
                out.println("</div>");
                out.println("<h2>Original system</h2>");
                out.println("<div class='row'>");
                let = 'a';
                out.println("<div class='col-8'>");
                out.println("<div class='row'>");
                for(int i = 0 ; i < size ; i++,let++) out.println("<div class='col card bg-secondary' align='center'><h3>"+let+"</h3></div>");
                out.println("</div>");
                out.println("</div>");
                out.println("<div class='col card bg-secondary' align='center'><h3>Results</h3></div>");
                out.println("</div>");
                out.println("<div class='row'>");
                out.println("<div class='col-8'>");
                for(int i = 0 ; i < size ; i++){
                    
                    out.println("<div class='row'>");
                    for(int j = 0 ; j < size ; j++){
                        if(j % 2 == 0) out.println("<div class='col card bg-success' align='center'>");
                        else out.println("<div class='col card bg-warning' align='center'>");
                        out.println("<h3>"+matrix.elementAt(i).elementAt(j)+"</h3>");
                        out.println("</div>");
                    }
                    out.println("</div>");
                }
                out.println("</div><div class='col'>");
                for(int j = 0 ; j < size ; j++) out.println("<div class='col card bg-info' align='center'><h3>"+results.elementAt(j)+"</h3></div>");
                out.println("</div></div>");
                
                out.println("</body>");
                out.println("</html>");
            }
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
