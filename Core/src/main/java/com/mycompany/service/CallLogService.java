package com.mycompany.service;
import static java.time.temporal.ChronoUnit.SECONDS;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.dbEntity.CallLogInfo;
import com.mycompany.dbEntity.PersonInfo;
import com.mycompany.dbRepository.CallLogRepository;
import com.mycompany.dbRepository.PersonRepository;

import vo.CallLogVO;
@Service
public class CallLogService {

	@Autowired
	private CallLogRepository callLogRepository;
	@Autowired
	private PersonRepository personRepository;

	public List<CallLogVO> getCallLogsOfANumber(String requestedPhoneNo, long startDate, long endDate) {

		List<CallLogVO> callLogVOs = Collections.emptyList();
		
		PersonInfo person = personRepository.findByPhoneNumber(requestedPhoneNo); 
		if(person != null) {		
		Calendar st = Calendar.getInstance();
		st.setTimeInMillis(startDate);
		Calendar end = Calendar.getInstance();
		end.setTimeInMillis(endDate);
		end.get(Calendar.MINUTE);
		List<CallLogInfo> callLogs = callLogRepository.findCallLogs(person, st, end);
		SimpleDateFormat dateParser = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		callLogVOs = callLogs.stream().map(new Function<CallLogInfo, CallLogVO>() {
			@Override
			public CallLogVO apply(CallLogInfo callLogInfo) {
				CallLogVO callLogVO = null;
				Calendar callStartDateTime = callLogInfo.getCallStartDateTime();
				long start = callStartDateTime.getTimeInMillis();
				long end = callLogInfo.getCallEndDateTime().getTimeInMillis();
				long durationInSecs = TimeUnit.MILLISECONDS.toSeconds(end-start);
				Duration d = Duration.of(durationInSecs,SECONDS);
				long hours = d.toHours(); 
				long minutes = d.minusHours(hours).toMinutes(); //15
				String callDuration = hours +":"+minutes; 
			    String startDateStr = dateParser.format(callStartDateTime.getTime());
			    callLogVO = new CallLogVO(callLogInfo.getPersonInfo().getPhoneNumber(),
			    		callLogInfo.getPartyPhoneNumber(),startDateStr,callDuration,callLogInfo.getLocation(),callLogInfo.getCallType());
				return callLogVO;
			}

		}).collect(Collectors.toList());
		}
		return callLogVOs;
	}
	
	
	
	public List<CallLogVO> getCallLogsBwTwoNumber(String firstPhoneNo, String secondPhoneNo,long startDate, long endDate) {

		List<CallLogVO> callLogVOs = Collections.emptyList();
		
		PersonInfo person = personRepository.findByPhoneNumber(firstPhoneNo); 
		if(person != null) {		
		Calendar st = Calendar.getInstance();
		st.setTimeInMillis(startDate);
		Calendar end = Calendar.getInstance();
		end.setTimeInMillis(endDate);
		List<CallLogInfo> callLogs = callLogRepository.findCallBetweenTwoPerson(person, secondPhoneNo, st, end);
		SimpleDateFormat dateParser = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		callLogVOs = callLogs.stream().map(new Function<CallLogInfo, CallLogVO>() {
			@Override
			public CallLogVO apply(CallLogInfo callLogInfo) {
				CallLogVO callLogVO = null;
				Calendar callStartDateTime = callLogInfo.getCallStartDateTime();
				long start = callStartDateTime.getTimeInMillis();
				long end = callLogInfo.getCallEndDateTime().getTimeInMillis();
				long durationInSecs = TimeUnit.MILLISECONDS.toSeconds(end-start);
				Duration d = Duration.of(durationInSecs,SECONDS);
				long hours = d.toHours(); 
				long minutes = d.minusHours(hours).toMinutes(); //15
				String callDuration = hours +":"+minutes; 
			    String startDateStr = dateParser.format(callStartDateTime.getTime());
			    callLogVO = new CallLogVO(callLogInfo.getPersonInfo().getPhoneNumber(),
			    		callLogInfo.getPartyPhoneNumber(),startDateStr,callDuration,callLogInfo.getLocation(),callLogInfo.getCallType());
				return callLogVO;
			}

		}).collect(Collectors.toList());
		}
		return callLogVOs;
	}

}
