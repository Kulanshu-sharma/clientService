package com.voteroid.clientService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voteroid.clientService.entities.ClientTbl;

public interface ClientTblRepository extends JpaRepository<ClientTbl,Integer> {

}
