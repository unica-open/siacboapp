/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacRRuoloOpBil;



public interface SiacRRuoloOpBilRepository extends JpaRepository<SiacRRuoloOpBil, Integer> {
	
	@Modifying
	@Query("DELETE FROM SiacRRuoloOpBil rb WHERE rb.ruoloOp.uid=:idRuoloOp")
	void deleteByRuoloOp(@Param("idRuoloOp") Integer idRuoloOp);

	@Modifying
	@Query("DELETE FROM SiacRRuoloOpBil rb WHERE rb.bilancio.uid=:idBilancio")
	void deleteByBilancio(@Param("idBilancio") Integer idBilancio);
}
