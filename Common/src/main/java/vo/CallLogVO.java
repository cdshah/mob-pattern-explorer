package vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import enums.CallType;

public class CallLogVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String firstNumber;
	private String secondNumber;
	private String startTime;
	private String duration;
	private String location;
	private CallType callType;
	
	public CallLogVO() {
	}
	
	public CallLogVO(String firstNumber, String secondNumber, String startTime, String duration, String location,
			CallType callType2) {
		super();
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.startTime = startTime;
		this.duration = duration;
		this.location = location;
		this.callType = callType2;
	}
	public String getFirstNumber() {
		return firstNumber;
	}
	public void setFirstNumber(String firstNumber) {
		this.firstNumber = firstNumber;
	}
	public String getSecondNumber() {
		return secondNumber;
	}
	public void setSecondNumber(String secondNumber) {
		this.secondNumber = secondNumber;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public CallType getCallType() {
		return callType;
	}
	public void setCallType(CallType callType) {
		this.callType = callType;
	}

	@Override
	public String toString() {
		return "CallLogVO [firstNumber=" + firstNumber + ", secondNumber=" + secondNumber + ", startTime=" + startTime
				+ ", duration=" + duration + ", location=" + location + ", callType=" + callType + "]";
	}
	
	

}
