package com.mkj.rapipay.repository;

import com.mkj.rapipay.entity.RapipayClient;

public interface CustomRapipayClientRepository {

	public RapipayClient getClientBasedOnClientName(String clientname);
}
