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

import it.csi.siac.siacboapp.integration.entity.SiacTCassaEcon;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacTCassaEconRepository extends JpaRepository<SiacTCassaEcon, Integer> {
	@Query("FROM SiacTCassaEcon ce "
			+ " WHERE ce.enteProprietario.uid = :enteId "
			+ " AND EXISTS "
			+ " 	(SELECT 1 FROM SiacRCassaEconBil ceb "
			+ " 	WHERE ceb.cassaEconomale=ce "
			+ " 	AND ceb.bilancio.uid=:bilancioId "
			+ " 	AND ceb.dataCancellazione IS NULL"
			+ " 	AND ceb.dataInizioValidita<=CURRENT_TIMESTAMP "
			+ " 	AND (ceb.dataFineValidita IS NULL OR ceb.dataFineValidita>CURRENT_TIMESTAMP)) "
			+ " AND ce.dataCancellazione IS NULL"
			+ " AND ce.dataInizioValidita<=CURRENT_TIMESTAMP "
			+ " AND (ce.dataFineValidita IS NULL OR ce.dataFineValidita>CURRENT_TIMESTAMP) "
			+ " ORDER BY ce.codice"			
	)
	List<SiacTCassaEcon> getElencoCasseEconomali(
			@Param("enteId") int enteId,
			@Param("bilancioId") int bilancioId
	);

}
