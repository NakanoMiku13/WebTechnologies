package webdevelopment.fileuploader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class FileForm extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InterruptedException {
        response.setContentType("text/html;charset=UTF-8");
        String input = "<input type='file' name='file' class='form-control' multiple/>";
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Upload form</title>");            
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
            out.println("<a class='nav-link' aria-current='page' href='/FileUploader/'>Home</a>");
            out.println("</li>");
            out.println("<li class='nav-item'>");
            out.println("<a class='nav-link active' href='/FileUploader/FileForm'>Upload File</a>");
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
            out.println("<h1>Upload your files</h1>");
            out.println("<form method='POST' action='UploadFile' enctype='multipart/form-data'>");
            out.println("<div class='row'>");
            out.println("<div class='col'>");
            out.println("<label>Upload your files</label>");
            out.println(input);
            out.println("</div>");
            out.println("</div>");
            out.println("<button type='submit' class='btn btn-outline-success'>Upload file (s)</button>");
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
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(FileForm.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
