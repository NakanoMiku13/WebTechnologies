/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webdevelopment.fileuploader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
@WebServlet(name = "FileUploader", urlPatterns = {"/fileuploader" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 50,
        maxFileSize = 1024 * 1024 * 10000,
        maxRequestSize = 1024 * 1024 * 10000
)
public class FileUploader extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String extention = "";        
        for(int i = fileName.length() - 1 ; fileName.charAt(i) != '.' ; extention += fileName.charAt(i), i--);
        new File(this.getServletContext().getRealPath("/files/" + extention + "/")).mkdir();
        String filePath = this.getServletContext().getRealPath("/files/" + extention + "/");
        for(Part part : request.getParts()){
            part.write(filePath + fileName);
        }
        response.getWriter().print("Uploaded");
    }
}
