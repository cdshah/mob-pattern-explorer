package com.mycompany.dbRepository;

 import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.aspectj.weaver.ast.Not;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.config.SpringCfg;
import com.mycompany.config.SpringTestCfg;
import com.mycompany.dbEntity.CallLogInfo;
import com.mycompany.dbEntity.PersonInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringTestCfg.class})
@ActiveProfiles("test")
public class CallLogRepositoryTest {

	@Autowired CallLogRepository callLogRepository;
	@Autowired private PersonRepository  personRepository;
	@Rule
    public ErrorCollector collector= new ErrorCollector();
	private static final Logger log = LoggerFactory.getLogger(SpringCfg.class);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		
		
	}
	@After
	public void after() throws Exception {
		
	}
	
	@Test
	@Sql(
		    scripts = "classpath:sqls/CallLogRepository_callsBwDates.sql",
		    		executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
		    config = @SqlConfig(commentPrefix = "`", separator = "@@")
		)
	@Sql(
		    scripts = "classpath:sqls/CallLogRepository_callsBwDates_del.sql",
		    executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
		    config = @SqlConfig(commentPrefix = "`", separator = "@@",transactionMode= TransactionMode.ISOLATED)
		    
		)
	public void testFindCallLogs() throws Exception {
		log.info("calls "+callLogRepository.count());
		Calendar startTime = Calendar.getInstance();
		//2016-06-03 20:57:50.448
		//2016-06-04 01:33:03.814
		startTime.set(2016, 5, 3, 0, 0, 0);
		startTime.set(Calendar.MILLISECOND, 0);
		Calendar endTime = Calendar.getInstance();
		endTime.set(2016, 5, 4, 2, 0, 0);
		
		log.info("stat time"+ new Date(startTime.getTimeInMillis()) + "end time"+ new Date(endTime.getTimeInMillis()));
		PersonInfo p = personRepository.findOne(12L);
		List<CallLogInfo> callsLogs = callLogRepository.findCallLogs(p, startTime, endTime);
		
		collector.checkThat(callsLogs.size(), is(3));
		for (CallLogInfo callLogInfo : callsLogs) {
			CallLogInfo findOne = callLogRepository.findOne(callLogInfo.getCallLogId());
			collector.checkThat(true, is(findOne.getCallStartDateTime().getTimeInMillis() >= (startTime.getTimeInMillis())));
			collector.checkThat(true, is(findOne.getCallStartDateTime().getTimeInMillis() <= (endTime.getTimeInMillis())));
			collector.checkThat(findOne.getCallLogId(), anyOf(is(109L),is(110L),is(111L)));
		}
		
		
	}
	
	
	@Test
	@Sql(
		    scripts = "classpath:sqls/CallLogRepository_callsBwDates2.sql",
		     executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
		    config = @SqlConfig(commentPrefix = "`", separator = "@@")
		)
	@Sql(
		    scripts = "classpath:sqls/CallLogRepository_callsBwDates_del.sql",
		    executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
		    config = @SqlConfig(commentPrefix = "`", separator = "@@",transactionMode= TransactionMode.ISOLATED)
		    
		)
	public void testSummaryCallLogs() {
		Calendar startTime = Calendar.getInstance();
		//2016-06-03 20:57:50.448
		//2016-06-04 01:33:03.814
		startTime.set(2016, 5, 3, 0, 0, 0);
		startTime.set(Calendar.MILLISECOND, 0);
		Calendar endTime = Calendar.getInstance();
		endTime.set(2016, 5, 4, 2, 0, 0);
		log.info("stat time"+ new Date(startTime.getTimeInMillis()) + "end time"+ new Date(endTime.getTimeInMillis()));
		List<CallSummaryVO> callsLogs = callLogRepository.getSummaryDetailsOfCallsPerDay("95382", "15", startTime, endTime);
		
		collector.checkThat(callsLogs.size(), is(2));
		for (CallSummaryVO callLogInfo : callsLogs) {
			collector.checkThat(callLogInfo.getCount(), anyOf(is(1),is(2)));
		}
	}
	
	@Test
	@Sql(
		    scripts = "classpath:sqls/CallLogRepository_callsBwDates2.sql",
		    		executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
		    config = @SqlConfig(commentPrefix = "`", separator = "@@")
		)
	@Sql(
		    scripts = "classpath:sqls/CallLogRepository_callsBwDates_del.sql",
		    executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
		    config = @SqlConfig(commentPrefix = "`", separator = "@@",transactionMode= TransactionMode.ISOLATED)
		    
		)
	public void shouldReturnCallLogBw2Phones() throws Exception {
		Calendar startTime = Calendar.getInstance();
		//2016-06-03 20:57:50.448
		//2016-06-04 01:33:03.814
		startTime.set(2016, 5, 3, 0, 0, 0);
		startTime.set(Calendar.MILLISECOND, 0);
		Calendar endTime = Calendar.getInstance();
		endTime.set(2016, 5, 4, 1, 30, 0);
		endTime.set(Calendar.MILLISECOND, 0);
		
		log.info("stat time"+ new Date(startTime.getTimeInMillis()) + "end time"+ new Date(endTime.getTimeInMillis()));
		PersonInfo p = personRepository.findOne(12L);
		List<CallLogInfo> callsLogs = callLogRepository.findCallBetweenTwoPerson(p,"15", startTime, endTime);
		
		collector.checkThat(callsLogs.size(), is(3));
		for (CallLogInfo callLogInfo : callsLogs) {
			CallLogInfo findOne = callLogRepository.findOne(callLogInfo.getCallLogId());
			collector.checkThat(true, is(findOne.getCallStartDateTime().getTimeInMillis() >= (startTime.getTimeInMillis())));
			collector.checkThat(true, is(findOne.getCallStartDateTime().getTimeInMillis() <= (endTime.getTimeInMillis())));
			collector.checkThat(findOne.getCallLogId(), anyOf(is(109L),is(113L),is(114L)));
		}
		
		
	}
	
	@Test
	@Sql(
		    scripts = "classpath:sqls/CallLogRepository_callsBwDates2.sql",
		    		executionPhase = ExecutionPhase.BEFORE_TEST_METHOD,
		    config = @SqlConfig(commentPrefix = "`", separator = "@@")
		)
	@Sql(
		    scripts = "classpath:sqls/CallLogRepository_callsBwDates_del.sql",
		    executionPhase = ExecutionPhase.AFTER_TEST_METHOD,
		    config = @SqlConfig(commentPrefix = "`", separator = "@@",transactionMode= TransactionMode.ISOLATED)
		    
		)
	public void shouldReturnLocationOfFirstParty() throws Exception {
		Calendar startTime = Calendar.getInstance();
		//2016-06-03 20:57:50.448
		//2016-06-04 01:33:03.814
		startTime.set(2016, 5, 3, 0, 0, 0);
		startTime.set(Calendar.MILLISECOND, 0);
		Calendar endTime = Calendar.getInstance();
		endTime.set(2016, 5, 4, 1, 30, 0);
		endTime.set(Calendar.MILLISECOND, 0);
		
		log.info("stat time"+ new Date(startTime.getTimeInMillis()) + "end time"+ new Date(endTime.getTimeInMillis()));
		PersonInfo p = personRepository.findOne(12L);
		List<CallLogInfo> callsLogs = callLogRepository.findLocationData(p, startTime, endTime);
		
		collector.checkThat(callsLogs.size(), is(5));
		for (CallLogInfo callLogInfo : callsLogs) {
			CallLogInfo findOne = callLogRepository.findOne(callLogInfo.getCallLogId());
			collector.checkThat(true, is(findOne.getCallStartDateTime().getTimeInMillis() >= (startTime.getTimeInMillis())));
			collector.checkThat(true, is(findOne.getCallStartDateTime().getTimeInMillis() <= (endTime.getTimeInMillis())));
			collector.checkThat(findOne.getCallLogId(), anyOf(is(109L),is(113L),is(114L),is(111L),is(110L)));
			collector.checkThat(findOne.getCallLogId(), is(not(112L)));
		}
		
		
	}

}
