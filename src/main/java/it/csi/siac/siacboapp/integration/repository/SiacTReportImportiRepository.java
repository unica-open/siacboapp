/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacTReportImporti;



public interface SiacTReportImportiRepository extends JpaRepository<SiacTReportImporti, Integer> {
	@Query("SELECT i FROM SiacTReportImporti i" + " JOIN i.report ir "
			+ " WHERE i.enteProprietario.uid=:enteId AND i.bilancio.uid=:bilancioId "
			+ "  AND i.dataCancellazione IS NULL AND i.dataFineValidita IS NULL "
			+ " AND ir.enteProprietario.uid=:enteId "
			+ "  AND ir.dataCancellazione IS NULL AND ir.dataFineValidita IS NULL "
			+ " AND ir.report.uid=:reportId "
			+ " ORDER BY ir.posizioneStampa, i.riga")
	List<SiacTReportImporti> getElencoImportiReport(@Param("reportId") Integer reportId,
			@Param("enteId") int enteId, @Param("bilancioId") int bilancioId);

}
