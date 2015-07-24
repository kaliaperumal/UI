package support;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.Md5Validator;

public class BeanSupport 
{
	    //File backup
		public static boolean copyFileUsingChannel(File source, File dest) throws IOException {
			boolean copy=false;
	        FileChannel sourceChannel = null;
	        FileChannel destChannel = null;
	        try {
	        	
	        	sourceChannel = new FileInputStream(source).getChannel();
	            destChannel = new FileOutputStream(dest).getChannel();
	            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
	            copy=true;
	           
	        }finally{
	               sourceChannel.close();
	               destChannel.close();
	        }
	        return copy;
	    }

		public void fileWrite_old(String HTMLContent, String filePath) throws IOException 
		{			
			 
		     FileOutputStream out = null;
		     Md5Validator fileobj = new Md5Validator();

		      try {		         
		         //out = new FileOutputStream(filePath+ "main.html");	 	 
		         out = new FileOutputStream(filePath + fileobj.getTokenjidaid() + ".html1");
		         
		         if(HTMLContent.length()!=0)  {
		        	 System.out.println("FileOutputStream Write	::: ");
		            out.write(HTMLContent.getBytes(Charset.forName("UTF-8")));
		         }
		      }finally {		        
		         if (out != null) {
		            out.close();
		         }
		      }	
			
			
		}
		
		public void fileWrite(String HTMLContent, String filePath) throws IOException 
		{
			
			Md5Validator fileobj = new Md5Validator();
			File file = new File(filePath + fileobj.getTokenjidaid() + ".html");
		    FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				BeanSupport obj1 = new BeanSupport();
				try {
					HTMLContent = obj1.convertNonASCIIToDecimal(HTMLContent);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				bw.write(HTMLContent);
				bw.close();
	        	//System.out.println("FileWriter New	::: " + HTMLContent);
		}
		
		
		public String convertNonASCIIToDecimal(String ref) throws Exception
		{
			Pattern pat = Pattern.compile("[^\\p{ASCII}]");
			Matcher matcher = pat.matcher(ref);
			String temp;
			String tempSrc = ref;
			int charValue = 0;
			while (matcher.find())
			{
			    temp=matcher.group();
			    charValue = (int)temp.charAt(0);
			    tempSrc=tempSrc.replaceAll(temp, "&#" + charValue + ";");
			}
			return tempSrc;
		}
		
		public void saveSupportMethod(String filePath) throws IOException 
		{
			Date d1=new Date();
		     Md5Validator fileobj = new Md5Validator();

			System.out.println("Before Date----------->"+new Timestamp(d1.getTime()));
			String date2=new Timestamp(d1.getTime()).toString();
			date2=date2.replaceAll("(\\:|\\.|\\-|\\s*)","");
			
			System.out.println("After Date----------->"+date2);	
			System.out.println("filePath----------->"+filePath);
			
			//File source=new File(filePath+"main.html");
			File source=new File(filePath+ fileobj.getTokenjidaid() + ".html");
			File dest=new File(filePath+"main_"+date2+".bak");		
			
			boolean copy_result=copyFileUsingChannel(source,dest);		
			if(!copy_result)			
				System.out.println("File Backup error");			
		}
}
