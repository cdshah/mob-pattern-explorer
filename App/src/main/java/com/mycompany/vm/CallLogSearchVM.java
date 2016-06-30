package com.mycompany.vm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import vo.CallLogVO;

public class CallLogSearchVM {
	private static String url = "http://localhost:8080/callLog";
	private String phoneNo;
	private Date fromDate;
	private Date toDate;
	private List<CallLogVO> callLogs;
	
	public CallLogSearchVM() {
		Calendar ca = Calendar.getInstance();
		ca.roll(Calendar.MONTH, -1);
		fromDate = ca.getTime();
		toDate = new Date();
	}

	@Command
	@NotifyChange("callLogs")
	public void search() {
		RestTemplate restTemplate = new RestTemplate();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY hh:mm:ss");
		String fromDate1 = sdf.format(fromDate);
		String toDate1 = sdf.format(toDate);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("phoneNo", phoneNo)
				.queryParam("fromDate", fromDate1).queryParam("toDate", toDate1);
		List<LinkedHashMap> response = restTemplate.getForObject(builder.build().encode().toUri(), List.class);
		ObjectMapper mapper = new ObjectMapper();
		callLogs = mapper.convertValue(response, new TypeReference<List<CallLogVO>>() {
		});
		
	}

	public static void main(String e[]) {
		RestTemplate restTemplate = new RestTemplate();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phoneNo", "95382");
		String fromDate1 = "03-JUN-2016 20:57:50";
		map.put("fromDate", fromDate1);
		String toDate1 = "04-JUN-2016 01:30:00";
		map.put("toDate", toDate1);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("phoneNo", "95382")
				.queryParam("fromDate", fromDate1).queryParam("toDate", toDate1);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		List<LinkedHashMap> response = restTemplate.getForObject(builder.build().encode().toUri(), List.class);
		ObjectMapper mapper = new ObjectMapper();
		List<CallLogVO> calls = mapper.convertValue(response, new TypeReference<List<CallLogVO>>() {
		});
		for (CallLogVO callLogVO : calls) {
			System.out.println("call " + callLogVO);
		}

	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		CallLogSearchVM.url = url;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public List<CallLogVO> getCallLogs() {
		return callLogs;
	}

	public void setCallLogs(List<CallLogVO> callLogs) {
		this.callLogs = callLogs;
	}
	
	

}
