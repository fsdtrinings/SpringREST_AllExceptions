package com.mkj.rapipay.service;

import com.mkj.rapipay.dto.ClientBankInfoDTO;
import com.mkj.rapipay.dto.RapipayClientDTOUser;
import com.mkj.rapipay.entity.RapipayClient;

public interface IRapipayClientService {
	
	public RapipayClientDTOUser registerClient(RapipayClient client);
	public RapipayClientDTOUser getCLientBasedOnId(int id);
	public RapipayClientDTOUser getCLientBasedOnClientName(String clientName);
	
	
	

}
