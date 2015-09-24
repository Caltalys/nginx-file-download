package com.example.domain;

import java.util.Date;

import org.joda.time.DateTime;

public class DownloadTask extends Base {

	private Long id;
	private Long productionId;
	private Long fileId;
	private String clientIp;
	private Date expiredAt;
	
	public void reset() {
		super.reset();
		DateTime dateTime = new DateTime(getCreatedAt());
		DateTime expiredAt = dateTime.plusDays(7);
		setExpiredAt(expiredAt.toDate());
	}

	public Long getProductionId() {
		return productionId;
	}

	public void setProductionId(Long productionId) {
		this.productionId = productionId;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Date getExpiredAt() {
		return expiredAt;
	}

	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
