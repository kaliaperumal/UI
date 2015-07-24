package support;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="Getactorstate")
@ApplicationScoped

public class Getactorstate {

	
	public Getactorstate(){
		actortype();
	}
	
	public void actortype() {
		System.out.println("start actor");
		
	FacesContext facesContext = FacesContext.getCurrentInstance();
	System.out.println(facesContext.getExternalContext()
			.getRequestParameterMap().get("type"));


	}
}