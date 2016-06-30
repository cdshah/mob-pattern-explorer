package com.mycompany.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.service.CallLogService;

import vo.CallLogVO;

@RestController
public class CallLogController {

	@Autowired
	private CallLogService callLogService;

	@RequestMapping("/callLog")
	public List<CallLogVO> getCallLogs(@RequestParam(value = "phoneNo") String phoneNo,
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate)
			throws Exception {
		SimpleDateFormat dateParser = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date parsedStDate = dateParser.parse(fromDate);
		Date parsedEndDt = dateParser.parse(toDate);
		return callLogService.getCallLogsOfANumber(phoneNo, parsedStDate.getTime(), parsedEndDt.getTime());

	}

	@RequestMapping("/biPartyCalls")
	public List<CallLogVO> getBiPartyCalls(@RequestParam(value = "firstPhoneNo") String firstPhoneNo,
			@RequestParam(value = "secondPhoneNo") String secondPhoneNo,
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate)
			throws Exception {
		SimpleDateFormat dateParser = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		Date parsedStDate = dateParser.parse(fromDate);
		Date parsedEndDt = dateParser.parse(toDate);
		return callLogService.getCallLogsBwTwoNumber(firstPhoneNo, secondPhoneNo, parsedStDate.getTime(),
				parsedEndDt.getTime());

	}
}
