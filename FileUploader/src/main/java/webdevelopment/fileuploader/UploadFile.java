package webdevelopment.fileuploader;
import java.io.File;
import java.io.IOException;
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
        //Método para obtener (y en caso de que no exista, crear una carpeta donde guardará los archivos) el directorio de archivos principal
        new File(this.getServletContext().getRealPath("/files")).mkdir();
        //Método para obtener el directorio general de archivos
        String path = this.getServletContext().getRealPath("/files/");
        //Función principal que subirá todos los archivos cargados en un formulario (No es necesario colocar names a los inputs) y se subirán todos
        //a la carpeta files creada anteriormente, el sistema solo permite subir archivos de hasta 4GB por archivo (los 900k son de prueba y no influyen directamente)
        //Multipart request ocupa 2 parámetros principales, el primero es el request de donde obtendrá la información de lor archivos a subir y el directorio donde se subirán
        //El tercer parámetro es opcional y define el tamaño máximo por archivo (aunque no estoy seguro, la documentación no es del todo clara), pero está limitado a 1 MB
        MultipartRequest m = new MultipartRequest(request,path,Math.abs(1024 * 1024 * 9000000));
        /*Hasta aquí ya está listo el sistema de subida de archivos, el cosdigo a continuación es para organizar de forma automatizada los archivos en directorios específicos*/
        //Obtenemos el directorio general (files)
        File files = new File(path);
        //Listamos todos los elementos que se encuentren en la carpeta files, tanto directorios como archivos
        String[] filesList = files.list();
        //Recorremos cada elemento de la lista
        for(int i = 0 ; i < filesList.length ; i++){
            //Obtenemos el nombre del archivo en i posición
            String fileName = filesList[i];
            //Obtenemos su ubicación
            String filePath = this.getServletContext().getRealPath("/files/" + fileName);
            //Obtenemos el archivo como tal, anteriormente solo era texto, como un nombre, extensión y demás, éste es el archivo como tal
            File file = new File(filePath);
            //Verificamos que sea un archivo como tal y no una carpeta
            if(fileName.contains(".")){
                String extention = "";
                //Obtenemos la extensión del archivo que vamos a trabajar
                for(int j = fileName.length() - 1 ; fileName.charAt(j) != '.' ; extention += fileName.charAt(j--));
                //Formateo de extensión
                StringBuilder temp = new StringBuilder();
                temp.append(extention);
                temp.reverse();
                extention = temp.toString();
                //Generamos la carpeta donde se guardará el archivo basado en su extensión, o sea sí subimos un JPG, llamado hola.jpg, se cataloga y sí existe la carpeta
                //No se hará nada, pero sí no existe la carpeta, se creará en este momento, de forma que la carpeta se creará en /files/"Nombre de carpeta"
                new File(this.getServletContext().getRealPath("/files/" + extention)).mkdir();
                //Aquí "movemos" (copiamos y pegamos en otra carpeta) el archivo de la carpeta files a la carpeta respectiva, para ordenar todos los archivos en carpetas, en caso de que se haga de manera exitosa
                //Se borra el archivo de la carpeta files para que no se quede basura, sin embargo, existe en su carpeta respectiva
                if(file.renameTo(new File(this.getServletContext().getRealPath("/files/"+extention+"/"+fileName)))) file.delete();
                else response.getWriter().println("Error with "+ fileName);
            }
        }
        //Redireccionamiento, ajustalo a tu página principal o donde desees
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