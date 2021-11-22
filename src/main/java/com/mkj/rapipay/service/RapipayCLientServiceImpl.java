package com.mkj.rapipay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkj.rapipay.dto.ClientBankInfoDTO;
import com.mkj.rapipay.dto.RapipayClientDTOUser;
import com.mkj.rapipay.entity.RapipayClient;
import com.mkj.rapipay.repository.RapipayClientRepository;

@Service
public class RapipayCLientServiceImpl implements IRapipayClientService {

	@Autowired
	private RapipayClientRepository jpaRepository;   // Actual name should be : rapipayClientRepository
	
	@Override
	public RapipayClientDTOUser registerClient(RapipayClient client) {
		
		RapipayClient savedClient =   jpaRepository.save(client);
		
		System.out.println("=====>> After Save inside CLient Servive Register "+savedClient);
		// convertor to convert to DTO Object
		RapipayClientDTOUser clientDTO = entityToDTOConvertor(savedClient);
		
		System.out.println("=====>> After DTO inside CLient Servive  "+clientDTO);
		
		return clientDTO;
	}

	@Override
	public RapipayClientDTOUser getCLientBasedOnId(int searchId) {
		RapipayClient rapipayCLient =jpaRepository.findById(searchId).get();
		// convert client to DTO
		
		RapipayClientDTOUser clientDTO = entityToDTOConvertor(rapipayCLient);
		return clientDTO;
		
	}



	@Override
	public RapipayClientDTOUser getCLientBasedOnClientName(String clientName) throws javax.persistence.NoResultException
	{
		
		return entityToDTOConvertor(
				jpaRepository.getClientBasedOnClientName(clientName));
	}
	
	
	
	
	
	// ==================   Convertor =====================
	
public RapipayClientDTOUser entityToDTOConvertor(RapipayClient savedClient)
{
	RapipayClientDTOUser clientDTO = new RapipayClientDTOUser();
	clientDTO.setAccountNumber(savedClient.getClientId());
	clientDTO.setClientName(savedClient.getClientName());
	clientDTO.setBalance(savedClient.getBalance());
	clientDTO.setBankName(savedClient.getBankInfo().getBankName());
	return clientDTO;
}
	
	
	
	
	
	
	
	
}// end of service 
