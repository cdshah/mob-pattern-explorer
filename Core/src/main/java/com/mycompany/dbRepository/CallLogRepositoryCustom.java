package com.mycompany.dbRepository;

import java.util.Calendar;
import java.util.List;

import com.mycompany.dbEntity.PersonInfo;

public interface CallLogRepositoryCustom {
	
public abstract List<CallSummaryVO> getSummaryDetailsOfCallsPerDay(String firstParty,String secondPartyPhoneNo,Calendar startTime,Calendar endTime);

}
