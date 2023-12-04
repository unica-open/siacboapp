/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.csi.siac.siacboapp.integration.entity.SiacTConfIndicatoriSint;



public interface SiacTConfIndicatoriSintRepository extends JpaRepository<SiacTConfIndicatoriSint, Integer> {

	
	@Query("SELECT i FROM SiacTConfIndicatoriSint i JOIN FETCH i.voce "
			+ " WHERE i.enteProprietario.uid=:idEnte "
			+ " AND i.bilancio.uid=:idBilancio "
			+ " AND i.dataCancellazione IS NULL "
			+ " AND i.dataInizioValidita<=CURRENT_TIMESTAMP "
			+ " AND (i.dataFineValidita IS NULL OR i.dataFineValidita>CURRENT_TIMESTAMP) "
			+ " ORDER BY i.voce.tipo, i.voce.codice")
	List<SiacTConfIndicatoriSint> readElencoConfIndicatoriSintetici(
				@Param("idEnte") Integer idEnte, 
				@Param("idBilancio") Integer idBilancio
			);

}
