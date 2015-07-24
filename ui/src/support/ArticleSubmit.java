package support;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

import pojo.Article;
import pojo.Automailer;
import pojo.Users;

import com.database.HibernateUtil;

import bean.Md5Validator;

@ManagedBean(name="ArticleSubmit")
@ApplicationScoped

public class ArticleSubmit implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private static int stagenumber; 


	public int getStagenumber() {
		return stagenumber;
	}

	public void setStagenumber(int stagenumber) {
		this.stagenumber = stagenumber;
	}

	public boolean checkrender(){
		boolean article_status=false;	
		
		Md5Validator obj1 = new Md5Validator();

		
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			String q1 = "From Article a where a.aid = :aid AND a.jid= :jid";
			Query query=session.createQuery(q1);
				  query.setParameter("aid", obj1.getTokenaid());
				  query.setParameter("jid", obj1.getTokenjid());
			List<Article> obj=query.list();
			
			for(Article articleobj: obj) {				
				if((articleobj.getStatusId() == 2) && (obj1.getActorstagedb().equals("author"))) {
					article_status = true;
				} else if((articleobj.getStatusId() == 4) && (obj1.getActorstagedb().equals("publisher"))) {
					article_status = true;
				} else if(articleobj.getStatusId() == 7 || articleobj.getStatusId() == 6 || articleobj.getStatusId() == 8) {
					article_status = false;
				}
				setStagenumber(articleobj.getStatusId());		
			}
		} catch(Exception e) {
			article_status = false;
			System.out.println("Exception in Author Submission Checking getActorstagedb::->"+obj1.getActorstagedb() +" :token=>"+obj1.getTokenvalue()+ " :==Excep==> "+e);
			
		} finally {
			session.close();
		}
		return article_status;
	}
	
	public void updatestatus(){
		
		Md5Validator obj1 = new Md5Validator();
		System.out.println("===================== Article-Submit-Start =========================");
		boolean stagestatus = checkrender();
		if(stagestatus == true && obj1.getActorstagedb().equals("author")) {
			updateauthorcomplete(obj1.getTokenaid(), obj1.getTokenjid(), 4);
		}else if(stagestatus == true && obj1.getActorstagedb().equals("publisher")) {
			updateauthorcomplete(obj1.getTokenaid(), obj1.getTokenjid(), 6);
		}else{
			System.out.println("Article Already submitted.........");
		}
		System.out.println("===================== Article-Submit-END =========================");
		
	}
	
	public boolean updateauthorcomplete(String aid, String jid, int stage) {
		Session session=null;
		Transaction tx=null;
		try{
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			String query1 = "UPDATE Article set statusid='"+stage+"' where aid ='"+ aid +"' AND jid='"+jid+"'";
			Query query=session.createQuery(query1);
			int result=query.executeUpdate();
			tx.commit();
			
			if(result == 1){
				System.out.println("Author submisson updated successfully");
				sendmailerdetail(aid, jid);
			}else{
				System.out.println("Author submisson Not updated successfully");
			}
			
		}catch(Exception e){
			System.out.println("Exception in Author Submission update Status");
		}finally {
			session.clear();
			session.close();
		}
		return true;
	}
	
	public void sendmailerdetail(String articleId, String journalId){
		
		Session session=null;
		Transaction tx=null;
		
		try {
			session=HibernateUtil.getSessionFactory().openSession();
			tx=session.beginTransaction();
			Automailer mailerobj=new Automailer();
			mailerobj.setArticleId(articleId);
			mailerobj.setJournalId(journalId);
			mailerobj.setStage("AU");
			session.save(mailerobj);		
			tx.commit();
		
		}catch(Exception e){
			System.out.println("Exception error insert sendmailerdetails"+ e);
		} finally {
			session.clear();
			session.close();
		}
	}

}
