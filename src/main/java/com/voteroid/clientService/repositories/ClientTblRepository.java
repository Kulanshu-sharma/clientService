package com.voteroid.clientService.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.voteroid.clientService.entities.ClientTbl;

public interface ClientTblRepository extends CrudRepository<ClientTbl,Integer> {
		public ClientTbl findByClientId(int clientId);
		
		public ClientTbl findByDomainName(String domainName);
		
}
