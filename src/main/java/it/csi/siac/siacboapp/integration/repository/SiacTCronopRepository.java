/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacTCronop;



public interface SiacTCronopRepository extends JpaRepository<SiacTCronop, Integer> {
	@Query("SELECT  c FROM SiacTCronop c " 
			+ " WHERE c.programma.uid=:programmaId AND c.dataCancellazione IS NULL and c.bilancio.uid=:bilancioId ")
	List<SiacTCronop> getElencoCronoprogrammi(
			@Param("programmaId") int programmaId,
			@Param("bilancioId") int bilancioId
	);
}
