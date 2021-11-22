package com.mkj.rapipay.execptions;

import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExceptionTemplate {
	
	private LocalTime currentTime;
	private String exceptionMsg;
	private final String customerCarePhone = "1800-150-150-20";
	
	
}
