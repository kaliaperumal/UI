package pojo;

// Generated 2 Jun, 2015 4:36:59 PM by Hibernate Tools 3.4.0.CR1
// Created By - kaliaperumal

/**
 * Automailer generated by hbm2java
 */
public class Automailer implements java.io.Serializable {

	private Integer sno;
	private String journalId;
	private String articleId;
	private String zipfileName;
	private String stage;

	public Automailer() {
	}

	public Automailer(String stage) {
		this.stage = stage;
	}

	public Automailer(String journalId, String articleId, String zipfileName,
			String stage) {
		this.journalId = journalId;
		this.articleId = articleId;
		this.zipfileName = zipfileName;
		this.stage = stage;
	}

	public Integer getSno() {
		return this.sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getJournalId() {
		return this.journalId;
	}

	public void setJournalId(String journalId) {
		this.journalId = journalId;
	}

	public String getArticleId() {
		return this.articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getZipfileName() {
		return this.zipfileName;
	}

	public void setZipfileName(String zipfileName) {
		this.zipfileName = zipfileName;
	}

	public String getStage() {
		return this.stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

}
