package com.voteroid.clientService.repositories;

import org.springframework.data.repository.CrudRepository;

import com.voteroid.clientService.entities.ClientTbl;

public interface ClientTblRepository extends CrudRepository<ClientTbl,Integer> {
		public ClientTbl findByClientIdAndPassword(int clientId,String password);
		public ClientTbl findByDomainName(String domainName);
}
