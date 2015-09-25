package com.example.domain;

import java.util.Date;

public class Base {
	
	static final String EMPTY_STRING = "";

	private Date createdAt;
	private Date updatedAt;
	private Boolean enabled;
	
	public Base() {
	}
	
	public void resetBy(Base another) {
		setCreatedAt(another.getCreatedAt());
		setUpdatedAt(another.getUpdatedAt());
		setEnabled(another.getEnabled());
	}
	
	public void reset() {
		final Date date = new Date();
		setCreatedAt(date);
		setUpdatedAt(date);
		setEnabled(true);
	}

	public final Date getCreatedAt() {
		return createdAt;
	}

	public final void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public final void resetCreatedAt() {
		setCreatedAt(new Date());
	}

	public final Date getUpdatedAt() {
		return updatedAt;
	}

	public final void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public final void resetUpdatedAt() {
		setUpdatedAt(new Date());
	}

	public final Boolean getEnabled() {
		return enabled;
	}

	public final void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}
