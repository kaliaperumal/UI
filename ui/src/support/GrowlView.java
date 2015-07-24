package support;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name="growlView")
@SessionScoped
public class GrowlView implements Serializable
{
	public void insertAction(ActionEvent actionEvent) 
	{
        addMessage("Insert Style !!");
    }
	public void instructionAction(ActionEvent actionEvent) 
	{
        addMessage("Instruction Style !!");
    }
	public void boldAction(ActionEvent actionEvent) 
	{
        addMessage("Bold Style !!");
    }	
	public void superscriptAction(ActionEvent actionEvent) 
	{
        addMessage("SuperScript style !!");
    }
	public void deleteAction(ActionEvent actionEvent)
	{
		addMessage("Delete Style !!");
	}
	public void italicAction(ActionEvent actionEvent) 
	{
        addMessage("Italic Style !!");
    }
	public void subscriptAction(ActionEvent actionEvent) 
	{
        addMessage("Sub Script Style......");
    }
	public void saveAction(ActionEvent actionEvent) 
	{
        addMessage("File Saved !!");
    }
	public void undoAction(ActionEvent actionEvent) 
	{
        addMessage("Undo !!");
    }
    public void addMessage(String summary) 
    {
    	System.out.println("addMessage Method Inside");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }	
}
