package support;

import java.io.File;

public class Test {
	public static void main(String args[]) {
	 
	 String destPath="/home/likewise-open/TRANS/tec0105/Project/ETAPS/WebContent/Proofs/Attachment/";
		File f1=new File(destPath);
    	File[] files=f1.listFiles();
    	for (File file : files) {
    	    if (file.isFile()) {
    	        
    	    	String fileName=file.getName();
    	    	String fileExtension=getFileExtension(file);
    	    	String fileSize=humanReadableByteCount(file.length(),true);    	    	
    	    	
    	    	System.out.println("Already Attached fileName	::: "+fileName);
    	        System.out.println("File Extension	::: "+fileExtension);
    	        System.out.println("File Szie	:::	"+fileSize);     	        
    	    }
    	}	
    }
	

	private static String getFileExtension(File file) {
		// TODO Auto-generated method stub
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
