package support;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import bean.Md5Validator;
@ManagedBean(name="ProofDownload")
@SessionScoped

public class ProofDownload implements Serializable {
	public void downloadFile() {

	    //File file = new File("/home/likewise-open/TRANS/tec0157/Desktop/sample.pdf");
		Md5Validator obj1=new Md5Validator();
		
		FacesContext ctx = FacesContext.getCurrentInstance();
		String fullfilePath = ctx.getExternalContext().getInitParameter("Filepath");

		File file = new File(fullfilePath + obj1.getTokenjid()+"/"+obj1.getTokenaid()+"/"+obj1.getTokenjidaid() +"/"+ obj1.getTokenjidaid()+".pdf");
	    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  

	    response.setHeader("Content-Disposition", "attachment;filename=" + obj1.getTokenjidaid()+"_pageproof.pdf");  
	    response.setContentLength((int) file.length());  
	    ServletOutputStream out = null;  
	    try {  
	        FileInputStream input = new FileInputStream(file);  
	        byte[] buffer = new byte[1024];  
	        out = response.getOutputStream();  
	        int i = 0;  
	        while ((i = input.read(buffer)) != -1) {  
	            out.write(buffer);  
	            out.flush();  
	        }  
	        FacesContext.getCurrentInstance().getResponseComplete();  
	    } catch (IOException err) {  
	        err.printStackTrace();  
	    } finally {  
	        try {  
	            if (out != null) {  
	                out.close();  
	            }  
	        } catch (IOException err) {  
	            err.printStackTrace();  
	        }  
	    }  

	}
}
