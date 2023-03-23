package webdevelopment.fileuploader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.oreilly.servlet.MultipartRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Vector;
import static java.nio.file.StandardCopyOption.*;
public class UploadFile extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Part filePart = request.getPart(String.format("file%d",0));
        //String fileName = filePart.getSubmittedFileName();
        new File(this.getServletContext().getRealPath("/files")).mkdir();
        String path = this.getServletContext().getRealPath("/files/");
        MultipartRequest m = new MultipartRequest(request,path,Math.abs(1024 * 1024 * 9000000));
        File files = new File(path);
        String[] filesList = files.list();
        //response.getWriter().println(filesList.length);
        for(int i = 0 ; i < filesList.length ; i++){
            response.getWriter().println(filesList[i] + " ");
            String fileName = filesList[i];
            String filePath = this.getServletContext().getRealPath("/files/" + fileName);
            File file = new File(filePath);
            //response.getWriter().println(file.isFile());
            if(fileName.contains(".")){
                String extention = "";
                response.getWriter().println(fileName);
                for(int j = fileName.length() - 1 ; fileName.charAt(j) != '.' ; extention += fileName.charAt(j--));
                StringBuilder temp = new StringBuilder();
                temp.append(extention);
                temp.reverse();
                extention = temp.toString();
                String dirPath = path + extention.toUpperCase();
                response.getWriter().println(dirPath);
                new File(this.getServletContext().getRealPath("/files/" + extention)).mkdir();
                if(file.renameTo(new File(this.getServletContext().getRealPath("/files/"+extention+"/"+fileName)))) file.delete();
                else response.getWriter().println("Error with "+ fileName);
            }
        }
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/ViewFiles");
        dispatcher.forward(request, response);
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