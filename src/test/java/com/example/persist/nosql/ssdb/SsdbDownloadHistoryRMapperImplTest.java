package com.example.persist.nosql.ssdb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.common.ConsoleTool;
import com.example.domain.DownloadHistory;
import com.example.persist.nosql.ssdb.SsdbDownloadHistoryRMapper;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class SsdbDownloadHistoryRMapperImplTest {

	@Autowired
	private SsdbDownloadHistoryRMapper mapper;

//	@Test
	public void testSelectById() {
		List<DownloadHistory> c1 = mapper.selectByTaskId(1L);
		ConsoleTool.printCollection(c1);
		List<DownloadHistory> c2 = mapper.selectByTaskId(1L);
		ConsoleTool.printCollection(c2);
	}
	
//	@Test
	public void testSelectAll() {
		List<DownloadHistory> c1 = mapper.selectAll();
		ConsoleTool.printCollection(c1);
		List<DownloadHistory> c2 = mapper.selectAll();
		ConsoleTool.printCollection(c2);
	}

}
