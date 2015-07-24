package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.Md5Validator;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DATA_DIRECTORY = "data";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check that we have a file upload request
        Md5Validator obj1 = new Md5Validator();
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        response.setContentType("text/html");  

        if (!isMultipart) {
            return;
        }

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Sets the size threshold beyond which files are written directly to
        // disk.
        factory.setSizeThreshold(MAX_MEMORY_SIZE);

        // Sets the directory used to temporarily store files that are larger
        // than the configured size threshold. We use temporary directory for
        // java
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        // constructs the folder where uploaded file will be stored
        //String uploadFolder = getServletContext().getRealPath("")
                //+ File.separator + DATA_DIRECTORY;
        
        String uploadFolder = "/home/likewise-open/TRANS/tec0157/workspace/ETAPS/WebContent/resources/Proofs/epl/17031/epl17031/attachment/";

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(MAX_REQUEST_SIZE);

        try {
            // Parse the request
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadFolder + File.separator + fileName;
                    File uploadedFile = new File(filePath);
                    System.out.println(filePath);
                    // saves the file to upload directory
                    item.write(uploadedFile);
                }
            }


            String dirPath = uploadFolder;
            File dir = new File(dirPath);
            File[] files = dir.listFiles();
            for (File aFile : files) {
                response.getWriter().println("<div style='color:red;'>"
                		+ "<a href='resources/Proofs/"+obj1.getTokenjid()+"/"+obj1.getTokenaid()+"/"+obj1.getTokenjidaid()
                		+"/attachment/"+aFile.getName()+"'>"+aFile.getName()+"</a></div>");
            };
            // displays done.jsp page after upload finished
            
            response.getWriter().println("Jid & Aid ===> " + obj1.getTokenjidaid());
            
          getServletContext().getRequestDispatcher("/done.jsp").include(
                    request, response);

        } catch (FileUploadException ex) {
            throw new ServletException(ex);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

    }

}