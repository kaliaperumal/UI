package com.login;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@ManagedBean (name="loginBean")
public class LoginBean implements Serializable
{
   //HttpSession httpsession=null;
   HttpServletRequest request=null;
   HttpSession httpsession=null;
	
   private String username;   
   private String error;
   public String getError() {
	return error;
}

   public LoginBean() {		
	   request=(HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

	   httpsession = request.getSession();
	   
	   
	    //httpsession=Util.getSession();
		this.setError("");
	}
	
   
public void setError(String error) {
	this.error = error;
}



public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

private String password;
   
   public String authenticationCheck()
   {
	   
	   System.out.println("username	:::"+username);
	   System.out.println("password	:::"+password);
	   return "Home.xhtml"; 
	   
	  /* if(username==null ||password==null)
	   {	   
		   return "Home";
	   }
	   else if(username.equals("admin") && password.equals("Technet1"))
	   {   		   
		   httpsession.setAttribute("username", username);
		   //httpsession.setAttribute("password", password);
		   return "Home"; 
	   }
	   else
	   {

          FacesContext.getCurrentInstance().addMessage(
                   null,
                   new FacesMessage(FacesMessage.SEVERITY_WARN,
                   "Invalid Login!",
                   "Please Try Again!"));
           
		   System.out.println("Else Part	:::");
		   this.setError("Invalid username or password");
		   error="Invalid username or password";
		   return "Login";
	   } */  	   
   }
   public String logout()
   {
	   //error="logout";
	   httpsession = request.getSession();
	   System.out.println("httpsession	::: "+httpsession);
	   httpsession.invalidate();
	   return "Login"; 
   }
}
