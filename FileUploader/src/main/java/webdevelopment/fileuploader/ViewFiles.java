/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webdevelopment.fileuploader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author miku
 */

public class ViewFiles extends HttpServlet {
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewFiles</title>");            
            out.println("<link rel='stylesheet' href='css/bootstrap.css'/>");
            out.println("<nav class='navbar navbar-expand-lg bg-body-tertiary'>");
            out.println("<div class='container-fluid'>");
            out.println("<a class='navbar-brand' href='/FileUploader/'>OurBox</a>");
            out.println("<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarSupportedContent' aria-controls='navbarSupportedContent' aria-expanded='false' aria-label='Toggle navigation'>");
            out.println("<span class='navbar-toggler-icon'></span>");
            out.println("</button>");
            out.println("<div class='collapse navbar-collapse' id='navbarSupportedContent'>");
            out.println("<ul class='navbar-nav me-auto mb-2 mb-lg-0'>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link active' aria-current='page' href='/FileUploader/'>Home</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link' href='/FileUploader/FileForm'>Upload File</a>");
            out.println("</li>");
            out.println("<li class='nav-item dropdown'>");
            out.println("<a class='nav-link dropdown-toggle' href='#' role='button' data-bs-toggle='dropdown' aria-expanded='false'>Categories");
            out.println("</a>");
            out.println("<ul class='dropdown-menu'>");
            out.println("<li><a class='dropdown-item' href='#'>Action</a></li>");
            out.println("<li><a class='dropdown-item' href='#'>Another action</a></li>");
            out.println("<li><hr class='dropdown-divider'></li>");
            out.println("<li><a class='dropdown-item' href='#'>Something else here</a></li>");
            out.println("</ul>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link disabled'>Disabled</a>");
            out.println("</li>");
            out.println("</ul>");
            out.println("<form class='d-flex' role='search'>");
            out.println("<input class='form-control me-2' type='search' placeholder='Search' aria-label='Search'>");
            out.println("<button class='btn btn-outline-success' type='submit'>Search</button>");
            out.println("</form>");
            out.println("</div>");
            out.println("</div></nav>");
            out.println("</head>");
            out.println("<body class='container'>");
            out.println("<h1>Servlet ViewFiles at " + request.getContextPath() + "</h1>");
            String filter = request.getParameter("filter");
            if(filter != null);
            File folders = new File(this.getServletContext().getRealPath("/files/"));
            String[] foldersList = folders.list();
            for(int i = 0 ; i < foldersList.length ; i ++){
                File files = new File(this.getServletContext().getRealPath(String.format("/files/%s/",foldersList[i])));
                String[] filesList = files.list();
                out.println("<div class='row'>");
                out.println("<h2>"+foldersList[i].toUpperCase()+"</h2>");
                for(int j = 0 ; j < filesList.length ; j++){
                    out.println("<div class='col'>");
                    String fileName = filesList[j], name = "", extention = foldersList[i];
                    int k = 0;
                    while(fileName.charAt(k) != '.'){
                        name += fileName.charAt(k++);
                    }
                    String path = "files/"+foldersList[i]+"/"+fileName;
                    out.println("<form method='POST' action='RemoveFile'>");
                    if(extention.equals("png") || extention.equals("jpg")  || extention.equals("jpeg") || extention.equals("raw")){
                        out.println(String.format("<div class='card' style='width: 18rem;'><input type='text' value='%s' name='file' style='display:none;'/><img src='%s ' class='img-thumbnail rounded card-img-top img-fluid mx-auto' alt='%s image'><div class='card-body'><h5 class='card-title'>%s</h5><input type='text' name='fileName' value='%s' style='display: none;'/><div class='btn-group'><a href='%s' class='btn btn-outline-primary' download>Download</a><button type='submit' class='btn btn-outline-warning'>Eliminar</button></div></div></div>",fileName,path,name,name,fileName,path));
                    }else{
                        out.println(String.format("<div class='card' style='width: 18rem;'><input type='text' value='%s' name='file' style='display:none;'/><img src='resources/img/file.png' class='img-thumbnail rounded card-img-top img-fluid mx-auto' alt='%s image'><div class='card-body'><h5 class='card-title'>%s</h5><div class='btn-group'><a href='%s' class='btn btn-outline-primary' download>Download</a><button type='submit' class='btn btn-outline-warning'>Eliminar</button></div></div></div>",fileName,name,name,path));
                    }
                    out.println("</form>");
                    out.println("</div>");
                }
                out.println("</div>");
            }
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
