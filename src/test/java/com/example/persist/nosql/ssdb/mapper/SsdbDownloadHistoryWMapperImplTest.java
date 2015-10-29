package com.example.persist.nosql.ssdb.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.JsonTool;
import com.example.domain.DownloadHistory;
import com.example.persist.nosql.ssdb.mapper.SsdbDownloadHistoryRMapperImpl;
import com.example.persist.nosql.ssdb.mapper.SsdbDownloadHistoryWMapperImpl;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class SsdbDownloadHistoryWMapperImplTest {
	
	@Autowired
	private SsdbDownloadHistoryWMapperImpl mapper;

//	@Test
	public void insert() {
		DownloadHistory e = new DownloadHistory();
		e.reset();
		e.setTaskId(1L);
		mapper.insert(e);
	}
	
	public void testKeyFor() {
		DownloadHistory e = new DownloadHistory();
		e.reset();
		e.setTaskId(1L);
		String key = SsdbDownloadHistoryRMapperImpl.idGenKeyFor(e);
		System.out.println(key);
		System.out.println(JsonTool.toJson(e));
	}

}
