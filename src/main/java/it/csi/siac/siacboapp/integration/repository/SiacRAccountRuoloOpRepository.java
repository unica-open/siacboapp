/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacRAccountRuoloOp;



public interface SiacRAccountRuoloOpRepository extends JpaRepository<SiacRAccountRuoloOp, Integer> {
	@Modifying
	@Query("DELETE FROM SiacRAccountRuoloOp ar " + " WHERE ar.account.uid=:idAccount")
	void deleteByAccount(@Param("idAccount") Integer idAccount);

}
