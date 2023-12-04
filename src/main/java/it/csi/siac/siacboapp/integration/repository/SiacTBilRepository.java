/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacTBil;



public interface SiacTBilRepository extends JpaRepository<SiacTBil, Integer> {
	
	@Query("FROM SiacTBil b WHERE b.enteProprietario.uid = :enteId  AND b.dataCancellazione IS NULL")
	List<SiacTBil> getElencoBilanci(@Param("enteId") int enteId);

}
