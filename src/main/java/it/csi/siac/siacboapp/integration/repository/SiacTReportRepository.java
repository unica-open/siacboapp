/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacTReport;



public interface SiacTReportRepository extends JpaRepository<SiacTReport, Integer> {

	@Query("SELECT r FROM SiacTReport r " 
			+ " WHERE r.enteProprietario.uid=:enteId "
			+ " AND r.dataCancellazione IS NULL AND r.dataFineValidita IS NULL "
			+ " ORDER BY r.ordinaElenco, r.codice ") // SIAC-7192 ordinamento nuova colonna
	List<SiacTReport> getElencoReport(@Param("enteId") int enteId);

}
