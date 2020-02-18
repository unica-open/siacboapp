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

import it.csi.siac.siacboapp.integration.entity.SiacDAmbito;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface SiacDAmbitoRepository extends JpaRepository<SiacDAmbito, Integer> {
	@Query("FROM SiacDAmbito a WHERE a.enteProprietario.uid=:enteId "
			+ " AND a.dataInizioValidita <= CURRENT_TIMESTAMP "
			+ " AND ( a.dataFineValidita > CURRENT_TIMESTAMP OR a.dataFineValidita IS NULL) "
			+ " AND a.dataCancellazione IS NULL")
	List<SiacDAmbito> getElencoAmbiti(@Param("enteId") int enteId);

	@Query("FROM SiacDAmbito a WHERE a.codice=:codiceAmbito "
			+ " AND a.enteProprietario.uid=:enteId " + " AND a.dataInizioValidita <= CURRENT_TIMESTAMP "
			+ " AND ( a.dataFineValidita > CURRENT_TIMESTAMP OR a.dataFineValidita IS NULL) "
			+ " AND a.dataCancellazione IS NULL")
	SiacDAmbito getAmbito(@Param("codiceAmbito") String codiceAmbito,
			@Param("enteId") int enteId);

}
