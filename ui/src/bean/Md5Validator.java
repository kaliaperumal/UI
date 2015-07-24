package bean;

import java.io.IOException;
import java.io.Serializable;
import java.security.PrivateKey;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pojo.Articlestage;
import pojo.Users;

import com.database.HibernateUtil;

@ManagedBean(name = "md5validator", eager = true)
@ApplicationScoped
@RequestScoped
@SessionScoped


public class Md5Validator implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private static String tokenvalue;
	private static String rootpath;
	private static String tokenjid;
	private static String tokenaid;
	private static String tokenjidaid;
	private static String articlekey;
	private static String actorstage;
	private static String actorstagedb;
	
	public String getActorstagedb() {
		return actorstagedb;
	}

	public void setActorstagedb(String actorstagedb) {
		this.actorstagedb = actorstagedb;
	}

	public String getActorstage() {
		return actorstage;
	}

	public void setActorstage(String actorstage) {
		this.actorstage = actorstage;
	}

	public String getArticlekey() {
		return articlekey;
	}

	public void setArticlekey(String articlekey) {
		this.articlekey = articlekey;
	}

	public String getTokenjidaid() {
		return tokenjidaid;
	}

	public void setTokenjidaid(String tokenjidaid) {
		this.tokenjidaid = tokenjidaid;
	}

	public String getTokenjid() {
		return tokenjid;
	}

	public void setTokenjid(String tokenjid) {
		this.tokenjid = tokenjid;
	}

		
	public String getTokenaid() {
		return tokenaid;
	}

	public void setTokenaid(String tokenaid) {
		this.tokenaid = tokenaid;
	}

/*	public Md5Validator() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		setTokenvalue(facesContext.getExternalContext()
				.getRequestParameterMap().get("token"));
		setActorstage(facesContext.getExternalContext()
				.getRequestParameterMap().get("type"));


		//setRootpath("resources/support/main.html");
		//System.out.println("constructor Html Root Path ====>" + rootpath);
		//getArticleDetails();
	}
*/	
	
	private void getArticleDetails() {

		Session session=null;
		Transaction tx=null;
		session=HibernateUtil.getSessionFactory().openSession();
		tx=session.beginTransaction();
		String hql="FROM Users u where u.token = :token";
		Query query=session.createQuery(hql).setParameter("token", this.getTokenvalue());
		List<Users> obj=(List<Users>)query.list();
		
		if(obj.size() != 1){
			setRootpath("");		
			}

		if(obj.size() == 1){
				System.out.println("Token Found" + obj.size());
				for(Users obj1 :obj) {
					setTokenjid(obj1.getJid());
					setTokenaid(obj1.getAid());
					setTokenjidaid(obj1.getJid()+obj1.getAid());
					String mainhtmlpath = "resources/Proofs/" + obj1.getJid() +"/" + obj1.getAid() + "/" + obj1.getJid() + obj1.getAid() +"/" + obj1.getJid() + obj1.getAid() +".html";
					setRootpath(mainhtmlpath);
				}
		}
	
	}


	public String getRootpath() {
		return rootpath;
	}

	public void setRootpath(String rootpath) {
		this.rootpath = rootpath;
	}

	public String getTokenvalue() {
		return tokenvalue;
	}

	public void setTokenvalue(String tokenvalue) {
		this.tokenvalue = tokenvalue;
	}

	@PostConstruct
	public void editAction() throws IOException
	{	
		FacesContext facesContext = FacesContext.getCurrentInstance();
		setTokenvalue(facesContext.getExternalContext().getRequestParameterMap().get("token"));
		setActorstage(facesContext.getExternalContext().getRequestParameterMap().get("type"));
		
		Md5Validator obj=new Md5Validator();
		obj.getArticleDetails();
		obj.getactorstagedetail();
	}
	
	public void getactorstagedetail() {

		Session session=null;
		Transaction tx=null;
		session=HibernateUtil.getSessionFactory().openSession();
		tx=session.beginTransaction();
		String hql="FROM Articlestage a where a.url = :actortype";
		Query query=session.createQuery(hql).setParameter("actortype", this.getActorstage());
		System.out.println("url param==>"+this.getActorstage());
		List<Articlestage> obj=(List<Articlestage>)query.list();

		if(obj.size() != 1){
			setActorstagedb("empty");		
			}
		
		if(obj.size() == 1){
			for(Articlestage obj1 :obj) {
				System.out.println("db Actor::==>" + obj1.getValue());
				setActorstagedb(obj1.getValue());
			}
		}
			
	}

}
