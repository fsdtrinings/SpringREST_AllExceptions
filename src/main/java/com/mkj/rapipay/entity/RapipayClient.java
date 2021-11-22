package com.mkj.rapipay.entity;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class RapipayClient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int clientId;
	
	@NotNull(message = "Client Name cannot be null")
	private String clientName;
	private String password;
	
	@Min(value = 500,message = "min balance cannot be less than 500")
	private int balance;
	private int cashback;
	@Embedded
	private BankInfo bankInfo;
}
