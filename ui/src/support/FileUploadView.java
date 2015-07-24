package support;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
 
@ManagedBean(name="fileUploadView")
@SessionScoped
public class FileUploadView implements Serializable {
	
	
	/*
	public ArrayList list=new ArrayList();
	
	private static AttachPojo[]  attach_list= new AttachPojo[] {	
	        		new AttachPojo("Sample.png", "png", "10kB")  		
	        	};
	   
	private DataModel<AttachPojo> AttachPojo = new ArrayDataModel<AttachPojo>(attach_list);
	 
	public DataModel<AttachPojo> getAttachList() 
	{ 
		return AttachPojo; 
	}
	*/
    
	FacesContext ctx=FacesContext.getCurrentInstance();
    private String destPath=ctx.getExternalContext().getInitParameter("AttachmentPath");

    
    public FileUploadView()
    {
    	checkExisitingFiles();
    	
    	/*
    	for(int i=0;i<list.size();i++)
    	{
    		String list_con=list.get(i).toString();
    		String fileName=list_con.substring(0,list_con.indexOf(","));
    		String fileExtension=list_con.substring(list_con.indexOf(","),list_con.indexOf(","));
    		String fileSize=list_con.substring(list_con.lastIndexOf(","),list_con.length());

    		System.out.println("Already Attached fileName	::: "+fileName);
	        System.out.println("File Extension	::: "+fileExtension);
	        System.out.println("File Size	:::	"+fileSize);    
	    			
    	   //attach_list= new AttachPojo[] {new AttachPojo(fileName, fileExtension, fileSize) 	};
    	}
    	System.out.println("attach_list size	:::"+attach_list.length);
    	*/
    	
    	
    	
    }

    private void checkExisitingFiles() {
    	
    	File f1=new File(destPath);
    	File[] files=f1.listFiles();
    	for (File file : files) {
    	    if (file.isFile()) {
    	        
    	    	String fileName=file.getName();
    	    	String fileExtension=getFileExtension(file);
    	    	String fileSize=humanReadableByteCount(file.length(),true);    	    	
    	    	
    	    	//System.out.println("Already Attached fileName	::: "+fileName);
    	        //System.out.println("File Extension	::: "+fileExtension);
    	        //System.out.println("File Szie	:::	"+fileSize);    
    	        
    	        //list.add(fileName+","+fileExtension+","+fileSize);
    	        
    	      
    	        
    	    }
    	}
		
	}

	public void handleFileUpload(FileUploadEvent event) {
    	
    	System.out.println("FileNames	::: "+event.getFile().getFileName());
    	
    	
    	FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }   
        
    }
    
    public void copyFile(String fileName, InputStream in) {
        try {	
             OutputStream out = new FileOutputStream(new File(destPath + fileName));           
             int read = 0;
             byte[] bytes = new byte[1024];
             
             while ((read = in.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
             }           
             in.close();
             out.flush();
             out.close();           
             System.out.println("New file created!");
             } catch (IOException e) {
             System.out.println(e.getMessage());
             }
 } 
   private static String getFileExtension(File file) {
		String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
	}
	
	public static String humanReadableByteCount(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
}