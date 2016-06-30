package com.mycompany.dbRepository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycompany.dbEntity.CallLogInfo;
import com.mycompany.dbEntity.PersonInfo;
@Repository

public interface CallLogRepository extends JpaRepository<CallLogInfo, Long>,CallLogRepositoryCustom {
	
	@Query("select c from CallLogInfo c where c.personInfo = :person and c.callStartDateTime >= :startTime and c.callEndDateTime <= :endTime order by c.callStartDateTime desc")
	List<CallLogInfo> findCallLogs(@Param("person")PersonInfo person,@Param("startTime")Calendar startTime,@Param("endTime")Calendar endTime);
	
	@Query("select c from CallLogInfo c where c.personInfo = :person and c.partyPhoneNumber = :partyPhoneNumber and c.callStartDateTime >= :startTime and c.callEndDateTime <= :endTime order by c.callStartDateTime asc")
	List<CallLogInfo> findCallBetweenTwoPerson(@Param("person")PersonInfo person,@Param("partyPhoneNumber")String partyPhoneNumber,@Param("startTime")Calendar startTime,@Param("endTime")Calendar endTime);
	
	@Query("select c from CallLogInfo c where c.personInfo = :person and c.callStartDateTime >= :startTime and c.callEndDateTime <= :endTime order by c.callStartDateTime asc")
	List<CallLogInfo> findLocationData(@Param("person")PersonInfo person,@Param("startTime")Calendar startTime,@Param("endTime")Calendar endTime);

}
