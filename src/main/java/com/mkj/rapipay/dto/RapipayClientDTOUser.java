package com.mkj.rapipay.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RapipayClientDTOUser {

	private int accountNumber;
	private String clientName;
	private int balance;
	private String bankName;
}
