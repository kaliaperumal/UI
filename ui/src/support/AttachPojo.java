package support;

public class AttachPojo {
	
	String fileName;
	String size;
	String AttachmentType;
	
	public String getAttachmentType() {
		return AttachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		AttachmentType = attachmentType;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

	public AttachPojo(String fileName, String AttachmentType, String size) {
		super();
		this.fileName = fileName;
		this.AttachmentType = AttachmentType;
		this.size = size;		
	}

	//getter and setter methods 
}

