package com.example.persist.rdbms;

import com.example.domain.DownloadTask;

public interface DownloadTaskWMapper {
	
	public void insert(DownloadTask e);
	
	public void updateTimeCostMillis(DownloadTask e);
	
	public void updateLastDldedAt(DownloadTask e);

}
