package com.mycompany.dbEntity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.mycompany.dbRepository.CallSummaryVO;

import enums.CallType;


@SqlResultSetMapping(name="CallSummaryResultMapping", classes = {
	    @ConstructorResult(targetClass = CallSummaryVO.class, 
	    columns = {@ColumnResult(name="firstPartyPhoneNo",type=String.class), @ColumnResult(name="name",type=String.class),@ColumnResult(name="secondPartyPhoneNo",type=String.class), 
	    		@ColumnResult(name="dateOfCall",type=Date.class),@ColumnResult(name="count",type=Integer.class)})
	})

@Entity
@Table(name="call_log")
public class CallLogInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CALL_LOG_SEQ_GEN")
    @SequenceGenerator(name="CALL_LOG_SEQ_GEN", sequenceName="call_log_seq", allocationSize=1)
	@Column(name="call_log_id")
	private long callLogId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	private PersonInfo personInfo;
	
	@Column(name="start_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar callStartDateTime;
	
	@Column(name="end_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar callEndDateTime;
	
	@Column(name="party_phone_no")
	private String partyPhoneNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name="call_type")
	private CallType callType;
	
	@Column(name="location")
	private String location;

	public CallLogInfo()
	{
		
	}
	public long getCallLogId() {
		return callLogId;
	}

	public void setCallLogId(long callLogId) {
		this.callLogId = callLogId;
	}

	public PersonInfo getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}

	public Calendar getCallStartDateTime() {
		return callStartDateTime;
	}

	public void setCallStartDateTime(Calendar callStartDateTime) {
		this.callStartDateTime = callStartDateTime;
	}

	public Calendar getCallEndDateTime() {
		return callEndDateTime;
	}

	public void setCallEndDateTime(Calendar callEndDateTime) {
		this.callEndDateTime = callEndDateTime;
	}

	public String getPartyPhoneNumber() {
		return partyPhoneNumber;
	}

	public void setPartyPhoneNumber(String partyPhoneNumber) {
		this.partyPhoneNumber = partyPhoneNumber;
	}

	public CallType getCallType() {
		return callType;
	}

	public void setCallType(CallType callType) {
		this.callType = callType;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "CallLogInfo [callLogId=" + callLogId + ", callStartDateTime=" + callStartDateTime + ", callEndDateTime="
				+ callEndDateTime + ", partyPhoneNumber=" + partyPhoneNumber + ", callType=" + callType + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (callLogId ^ (callLogId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CallLogInfo other = (CallLogInfo) obj;
		if (callLogId != other.callLogId)
			return false;
		return true;
	}
	
	
	
	

}
