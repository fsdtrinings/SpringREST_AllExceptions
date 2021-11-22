package com.mkj.rapipay.execptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinBalanceException extends Exception {
	
	private int balance;
	private final int minBalance = 5000;
	private String username;
	private String bankName;
	
		
	public String exceptionMsg()
	{
		return "Exception Raised on ClientName : "+username+" For "+bankName+" min balance limit is "+minBalance;
	}

}
