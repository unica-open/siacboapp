/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacDRuolo;



public interface SiacDRuoloRepository extends JpaRepository<SiacDRuolo, Integer> {

	@Query("FROM SiacDRuolo s WHERE s.enteProprietario.uid = :enteId  AND s.dataCancellazione IS NULL")
	List<SiacDRuolo> getElencoRuoli(@Param("enteId") int enteId);

}
