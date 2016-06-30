package com.mycompany.dbRepository;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.mycompany.dbEntity.PersonInfo;

public class CallLogRepositoryImpl implements CallLogRepositoryCustom{

	@Autowired private EntityManager em;
	
	@Override
	public List<CallSummaryVO> getSummaryDetailsOfCallsPerDay(String firstPartyPhoneNo, String secondPartyPhoneNo,
			Calendar startTime, Calendar endTime) {
		
		String myQuery ="select distinct p.phone_no as firstPartyPhoneNo,p.name as name,c.party_phone_no as secondPartyPhoneNo,"
				+ "CAST(c.start_time as DATE) as dateOfCall,"
				+ " count(c.id) as count from call_log c,person p where c.id = p.id and"
				+ " p.phone_no = :phoneNo and c.party_phone_no = :secondPartyPhoneNo and c.start_time >= :startTime and c.end_time <= :endTime"
				+ " group by p.phone_no,p.name,c.party_phone_no,CAST(c.start_time as DATE)";
				
		
		List<CallSummaryVO> results = em.createNativeQuery(myQuery, "CallSummaryResultMapping")
	    .setParameter("phoneNo", firstPartyPhoneNo)
		.setParameter("secondPartyPhoneNo", secondPartyPhoneNo)
		.setParameter("startTime", startTime)
		.setParameter("endTime", endTime)
		.getResultList();
		System.out.println("values "+results);
		return results;
	}

}
