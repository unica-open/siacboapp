/*
*SPDX-FileCopyrightText: Copyright 2020 | CSI Piemonte
*SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.siac.siacboapp.integration.repository;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import it.csi.siac.siacboapp.integration.entity.SiacTConfIndicatoriEntrata;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacTConfIndicatoriEntrataRepository extends JpaRepository<SiacTConfIndicatoriEntrata, Integer> {

	
	@Query("SELECT i FROM SiacTConfIndicatoriEntrata i JOIN FETCH i.titolo JOIN FETCH i.tipologia "
			+ " WHERE i.enteProprietario.uid=:idEnte "
			+ " AND i.bilancio.uid=:idBilancio "
			+ " AND i.dataCancellazione IS NULL "
			+ " AND i.dataInizioValidita<=CURRENT_TIMESTAMP "
			+ " AND (i.dataFineValidita IS NULL OR i.dataFineValidita>CURRENT_TIMESTAMP) "
			+ " ORDER BY i.titolo.codice, i.tipologia.codice")
	List<SiacTConfIndicatoriEntrata> readElencoConfIndicatoriEntrata(
				@Param("idEnte") Integer idEnte, 
				@Param("idBilancio") Integer idBilancio
			);

}
