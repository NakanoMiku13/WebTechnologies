package webdevelopment.fileuploader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@WebServlet(name = "FileUploader", urlPatterns = {"/fileuploader" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 50000,
        maxFileSize = 1024 * 1024 * 100000,
        maxRequestSize = 1024 * 1024 * 100000
)
public class UploadFile extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Uploaded files</title>");            
            out.println("<link rel='stylesheet' href='css/bootstrap.css'/>");
            out.println("</head>");
            out.println("<body class='container'>");
            int filesNumber = Integer.parseInt(request.getParameter("fileNumber"));
            out.println(filesNumber);
            new File(this.getServletContext().getRealPath("/files/")).mkdir();
            for(int i = 0; i < filesNumber ; i++){
                Part filePart = request.getPart(String.format("file%d",i));
                String fileName = filePart.getSubmittedFileName();
                out.println(fileName);
                String extention = "";        
                for(int j = fileName.length() - 1 ; fileName.charAt(j) != '.' ; extention += fileName.charAt(j), j--);
                StringBuilder temp = new StringBuilder();
                temp.append(extention);
                temp.reverse();
                extention = temp.toString();
                new File(this.getServletContext().getRealPath("/files/" + extention + "/")).mkdir();
                String filePath = this.getServletContext().getRealPath("/files/" + extention + "/");
                out.println(filePath);
                out.println("<div id='pBar'></div>");
                for(Part part : request.getParts()){
                    part.write(filePath + fileName);
                }
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