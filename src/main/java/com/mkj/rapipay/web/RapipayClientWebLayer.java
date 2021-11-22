package com.mkj.rapipay.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mkj.rapipay.dto.RapipayClientDTOUser;
import com.mkj.rapipay.entity.RapipayClient;
import com.mkj.rapipay.execptions.MinBalanceException;
import com.mkj.rapipay.service.IRapipayClientService;

@RestController
@RequestMapping("/client")
@Validated
public class RapipayClientWebLayer {

	@Autowired
	private IRapipayClientService rapipayClientService;
	
	public RapipayClientWebLayer() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("constructor called //");
	}

	@RequestMapping("/hello")
	public String helloEndPoint()
	{
		return "Rapipay client Hello";
	}
	
	
	
	@PostMapping("/registration")
	public ResponseEntity<RapipayClientDTOUser> addClient(@RequestBody @Valid  RapipayClient client)
	 throws MinBalanceException
	{
		System.out.println(" inside registration "+client);
		if(client.getBalance()<client.getBankInfo().getMimBalance())
		{
			throw new MinBalanceException(client.getBalance(), client.getClientName(), client.getBankInfo().getBankName());
		}
		else
		{
			System.out.println("=======>> Inside Web Controller "+client);
			// 1. code to call client service to add the client
			RapipayClientDTOUser clientDTO = rapipayClientService.registerClient(client);
			
				
			// generate return statement (ResponseEntity)
			return new ResponseEntity<RapipayClientDTOUser>(clientDTO, HttpStatus.CREATED);
		}
		
		
	}
	
	@GetMapping("/{id}")  // ....localhost:8080/client/101
	public RapipayClientDTOUser getClientBasedOnID(@PathVariable int id)
	{
		// ---- 
		// code to call service wrt to get CLient based on ID
		
		RapipayClientDTOUser clientDTO = rapipayClientService.getCLientBasedOnId(id);
		
		
		return clientDTO;
	}
	
	
	
	@GetMapping("/clientname/{clientName}")  // ....localhost:8080/client/clientname/Amit
	public RapipayClientDTOUser getClientBasedOnName(@PathVariable String clientName)throws javax.persistence.NoResultException
	{
		// ---- 
		// code to call service wrt to get CLient based on ID
		
		RapipayClientDTOUser clientDTO = rapipayClientService.getCLientBasedOnClientName(clientName);
		
		
		return clientDTO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}//end class
