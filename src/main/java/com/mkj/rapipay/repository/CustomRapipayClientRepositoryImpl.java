package com.mkj.rapipay.repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaUpdate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.mkj.rapipay.entity.RapipayClient;

public class CustomRapipayClientRepositoryImpl implements CustomRapipayClientRepository {

	@Autowired
	EntityManager entityManager;  // as a Session in Hibernate
	
	@Override
	public RapipayClient getClientBasedOnClientName(String searchedClientName)throws javax.persistence.NoResultException
	{
		
		Session session = entityManager.unwrap(Session.class);
		
		String queryString = "from RapipayClient where clientName=:token";
		Query<RapipayClient> query = session.createQuery(queryString);
		query.setString("token", searchedClientName);
		
		RapipayClient savedClientInfo = query.getSingleResult();
		
		// code to fetch data from DB using JPQL
		
		if(savedClientInfo != null)
		{
			return savedClientInfo;
		}
		else
		{
			throw new javax.persistence.NoResultException("Client Name not found");
		}
		
	}

}
