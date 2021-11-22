package com.mkj.rapipay.web;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mkj.rapipay.dto.RapipayClientDTOUser;
import com.mkj.rapipay.entity.Product;
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
		System.out.println("constructor called //");
	}

	@RequestMapping("/hello")
	public String helloEndPoint()
	{
		return "Rapipay client Hello";
	}
	
	
	@PostMapping("/login")
	public String doLogin(@RequestParam String username,
			               @RequestParam String password,
			               HttpServletRequest request)
	{
		String msg = "";
		// ... code how to login
		// from DB using repository class
		if(username.equals("mike")&&password.equals("123"))
		{
			// Cart
			List<Product> userCart = new ArrayList<>();
			
			System.out.println("Session Created ");
			HttpSession session = request.getSession(true); // always create new session
			session.setMaxInactiveInterval(30);
			session.setAttribute("username",username);
			session.setAttribute("loginTime",session.getCreationTime());
			session.setAttribute("cashback",10);
			
			msg = "Session Created for "+username+" time "+session.getCreationTime();
			// session.setAttribute("cart",userCart);
			
		}
		
		
		
		return msg;
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
	public RapipayClientDTOUser getClientBasedOnName(
			@PathVariable String clientName,
			HttpServletRequest request)throws javax.persistence.NoResultException
	
	{
		HttpSession session = request.getSession(false); // return already created session :- JSessionID
		if(session!=null)
		{
			String username = (String)session.getAttribute("username");
			System.out.println("===>> INFO Session Output 101 :- "+username);

			
			RapipayClientDTOUser clientDTO = rapipayClientService.getCLientBasedOnClientName(clientName);
			
			
			return clientDTO;
		}
		else return null;
		
		
	}
	
	
	
	@GetMapping("/logout/{username}")
	public String logout(@PathVariable String username,HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session != null)
		{
			// optional code
			session.removeAttribute("username");
			session.removeAttribute("loginTime");
			session.removeAttribute("cashback");
			
			session.invalidate(); // logout
			return "Logout , Pls. login again to continue "+LocalTime.now();
		}
		else return "Login through Valid user";
	}
	
	
	
	
	
	
	
}//end class
