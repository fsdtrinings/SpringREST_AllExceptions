package com.mkj.rapipay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mkj.rapipay.entity.RapipayClient;

@Repository
public interface RapipayClientRepository extends
                         JpaRepository<RapipayClient,Integer>, 
                         CustomRapipayClientRepository
                         
{
   // JPA include CURD Repository
	// include 
	// save , saveAndFlush
	// findByID
	// findALL
	
}
