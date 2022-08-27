package com.voteroid.clientService.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.voteroid.clientService.entities.PasswordResetTokenTbl;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetTokenTbl,Long> {

	public PasswordResetTokenTbl findByToken(String token);
}
