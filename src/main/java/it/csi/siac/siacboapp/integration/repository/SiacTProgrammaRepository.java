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

import it.csi.siac.siacboapp.integration.entity.SiacTProgramma;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacTProgrammaRepository extends JpaRepository<SiacTProgramma, Integer> {
	@Query("SELECT DISTINCT p FROM SiacTProgramma p " +
			" INNER JOIN FETCH p.cronoprogrammi c " +
			" INNER JOIN p.attributi pa " +
			" INNER JOIN pa.attributo a " +
			" LEFT OUTER JOIN p.movimentiGestioneProgramma m WITH m.enteProprietario.uid=:enteId AND m.dataCancellazione IS NULL "
			+ " WHERE p.enteProprietario.uid=:enteId AND p.dataCancellazione IS NULL " 
			+ "  AND c.enteProprietario.uid=:enteId AND c.dataCancellazione IS NULL and c.bilancio.uid=:bilancioId "
			+ "  AND pa.enteProprietario.uid=:enteId AND pa.dataCancellazione IS NULL"
			+ "  AND a.enteProprietario.uid=:enteId AND a.dataCancellazione IS NULL " +
			" AND a.codice='FlagRilevanteFPV'"
			)
	List<SiacTProgramma> getElencoProgrammi(
			@Param("enteId") int enteId,
			@Param("bilancioId") int bilancioId
	);

	
}
