package pojo;

// Generated 20 May, 2015 12:29:45 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Datasetstatus generated by hbm2java
 */
public class Datasetstatus implements java.io.Serializable {

	private Integer datasetStatusId;
	private int supplierId;
	private String datasetId;
	private String journalId;
	private String articleId;
	private String articleTitle;
	private String status;
	private String metaXmlname;
	private Date downloadPickUpTime;
	private Date startDate;
	private Date endDate;
	private Set datasethistories = new HashSet(0);

	public Datasetstatus() {
	}

	public Datasetstatus(int supplierId, String datasetId, String journalId,
			String articleId, String status, String metaXmlname) {
		this.supplierId = supplierId;
		this.datasetId = datasetId;
		this.journalId = journalId;
		this.articleId = articleId;
		this.status = status;
		this.metaXmlname = metaXmlname;
	}

	public Datasetstatus(int supplierId, String datasetId, String journalId,
			String articleId, String articleTitle, String status,
			String metaXmlname, Date downloadPickUpTime, Date startDate,
			Date endDate, Set datasethistories) {
		this.supplierId = supplierId;
		this.datasetId = datasetId;
		this.journalId = journalId;
		this.articleId = articleId;
		this.articleTitle = articleTitle;
		this.status = status;
		this.metaXmlname = metaXmlname;
		this.downloadPickUpTime = downloadPickUpTime;
		this.startDate = startDate;
		this.endDate = endDate;
		this.datasethistories = datasethistories;
	}

	public Integer getDatasetStatusId() {
		return this.datasetStatusId;
	}

	public void setDatasetStatusId(Integer datasetStatusId) {
		this.datasetStatusId = datasetStatusId;
	}

	public int getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getDatasetId() {
		return this.datasetId;
	}

	public void setDatasetId(String datasetId) {
		this.datasetId = datasetId;
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

	public String getArticleTitle() {
		return this.articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMetaXmlname() {
		return this.metaXmlname;
	}

	public void setMetaXmlname(String metaXmlname) {
		this.metaXmlname = metaXmlname;
	}

	public Date getDownloadPickUpTime() {
		return this.downloadPickUpTime;
	}

	public void setDownloadPickUpTime(Date downloadPickUpTime) {
		this.downloadPickUpTime = downloadPickUpTime;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set getDatasethistories() {
		return this.datasethistories;
	}

	public void setDatasethistories(Set datasethistories) {
		this.datasethistories = datasethistories;
	}

}
