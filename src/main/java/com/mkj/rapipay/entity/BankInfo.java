package com.mkj.rapipay.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class BankInfo {
	
	private String bankName;
	private String ifscCode;
	private int mimBalance;
}
