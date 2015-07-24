package bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import support.BeanSupport;

import java.sql.Timestamp;
@ManagedBean(name = "editorController", eager = true)
@ApplicationScoped
public class EditorController implements Serializable {

	private static final long serialVersionUID = 20111020L;
	
	Map<String, Object> sessionMap;	
	  
	FacesContext context = FacesContext.getCurrentInstance();
	HttpServletRequest request;
	HttpSession httpSession;

	private String ref_content;
	private String saveContent;
	public static String filePath;	    
	private static String fullfilePath;    
	
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) 
	{
		this.filePath = filePath;
	}
	
	

	public EditorController() throws IOException 
	{					
		//filePath1="/home/likewise-open/TRANS/tec0105/Project/XMLAuthorProof/WebContent/resources/support/";
		inputFolderPath();
	}
		
	public void inputFolderPath() throws IOException
	{		
		FacesContext ctx = FacesContext.getCurrentInstance();
		fullfilePath=ctx.getExternalContext().getInitParameter("Filepath");
		System.out.println("inputFolderPath ---> "+filePath);
	}	
	
	public String getRef_content() {
		return ref_content;
	}

	public void setRef_content(String ref_content) {
		this.ref_content = ref_content;
	}

	public String getSaveContent() 
	{
		return saveContent;
	}

	public void setSaveContent(String saveContent) {
		this.saveContent = saveContent;
	}	
	
	public void saveMethod() throws IOException
	{
		//Save HTML backup File
		Md5Validator myvalue= new Md5Validator();
		String fullfilePath1 = fullfilePath + myvalue.getTokenjid() +"/"+ myvalue.getTokenaid() +"/"+ myvalue.getTokenjidaid() + "/";
		setFilePath(fullfilePath1);

		System.out.println("Filefullpathhhhhhh :::: " + myvalue.getTokenjid());
		System.out.println("getActorstagedb :::: " + myvalue.getActorstagedb());
		
		BeanSupport obj1=new BeanSupport();
		obj1.saveSupportMethod(filePath);		
	}	
	
	public void saveContent() throws IOException	
	{
		//File Backup timestamp
		try{
			
		saveMethod();
		
		} catch(Exception ex1) { System.out.println("File Backup creation problem "+ex1); }		
		
	    FacesContext context = FacesContext.getCurrentInstance();
	    Map<String, String> params = context.getExternalContext().getRequestParameterMap();   

	    Charset cs = Charset.forName("ASCII");    
	    BeanSupport obj=new BeanSupport();
	    
	    //System.out.println("fileWrite-------->"+params.get("filecontent_param").toString());
	    
	    obj.fileWrite(params.get("filecontent_param").toString(),filePath);	 
	    
	    ref_content=params.get("filecontent_param").toString();    
	}	

}